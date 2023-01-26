package b100.input.controllers.sdl;

import static org.libsdl.SDL.*;

import java.util.ArrayList;
import java.util.List;

import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;

public class ControllerLibrarySDL extends ControllerLibrary {
	
	private final int count;
	
	private boolean initialized = false;
	
	private List<ControllerSDL> disconnectedControllers = new ArrayList<>();
	
	public ControllerLibrarySDL(int count) {
		this.count = count;
	}
	
	@Override
	public void create(ControllerInputHandler controllerInputHandler) {
		initialized = false;
				
		if(SDL_Init(SDL_INIT_GAMECONTROLLER | SDL_INIT_JOYSTICK | SDL_INIT_EVENTS) != 0) {
			throw new RuntimeException("Could not initialize SDL: " + SDL_GetError());
		}
		
		for(int i=0; i < count; i++) {
			this.disconnectedControllers.add(new ControllerSDL(this, controllerInputHandler, i));
		}
		
		update();
		
		initialized = true;
	}

	@Override
	public void update() {
		for(int i=0; i < disconnectedControllers.size(); i++) {
			ControllerSDL controller = disconnectedControllers.get(i);
			
			if(controller.open()) {
				disconnectedControllers.remove(i--);
				
				controllers.add(controller);
				if(initialized) {
					onControllerConnected(controller);
				}
			}
		}
		
		if(!initialized) {
			return;
		}
		
		for(int i=0; i < controllers.size(); i++) {
			ControllerSDL controller = (ControllerSDL) controllers.get(i);
			
			try{
				controller.update();
			}catch (ControllerDisconnectException e) {
				controllers.remove(i--);
				disconnectedControllers.add(controller);
				onControllerDisconnected(controller);
			}
		}
	}

	@Override
	public void destroy() {
		for(int i=0; i < controllers.size(); i++) {
			ControllerSDL controller = (ControllerSDL) controllers.get(i);
			
			SDL_GameControllerClose(controller.getPointer());
		}
		controllers.clear();
		disconnectedControllers.clear();
		
		SDL_QuitSubSystem(SDL_INIT_GAMECONTROLLER | SDL_INIT_JOYSTICK | SDL_INIT_EVENTS);
	}

	@Override
	public String getName() {
		return "sdl2gdx";
	}

	@Override
	public boolean supportsReconnect() {
		return true;
	}

	@Override
	public boolean supportsRumble() {
		return false;
	}
}
