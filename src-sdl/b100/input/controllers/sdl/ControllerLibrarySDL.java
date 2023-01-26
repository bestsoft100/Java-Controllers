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
		System.out.println("Init SDL");
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
				System.out.println("Open Controller: "+controller.getIndex());
				disconnectedControllers.remove(i--);
				
				controllers.add(controller);
				if(initialized) {
					onControllerConnected(controller);
				}
			}
		}
		
		for(int i=0; i < controllers.size(); i++) {
			ControllerSDL controller = (ControllerSDL) controllers.get(i);
			
			try{
				controller.update();
			}catch (ControllerDisconnectException e) {
				controllers.remove(i--);
				disconnectedControllers.add(controller);
				
				if(initialized) {
					onControllerDisconnected(controller);
				}
			}
		}
	}

	@Override
	public String getName() {
		return "sdl2gdx";
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

}
