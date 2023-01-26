package b100.input.controllers.sdl;

import static org.libsdl.SDL.*;

import b100.input.controllers.Button;
import b100.input.controllers.Controller;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;

public class ControllerSDL extends Controller {

	private ControllerLibrarySDL library;
	
	private int index;
	private long pointer;
	
	public ControllerSDL(ControllerLibrarySDL library, ControllerInputHandler controllerInputHandler, int index) {
		super(controllerInputHandler);
		
		this.library = library;
		this.index = index;
		
		addButton(new ButtonSDL(BUTTON_A, this, SDL_CONTROLLER_BUTTON_A));
		addButton(new ButtonSDL(BUTTON_B, this, SDL_CONTROLLER_BUTTON_B));
		
		addButton(new ButtonSDL(BUTTON_X, this, SDL_CONTROLLER_BUTTON_X));
		addButton(new ButtonSDL(BUTTON_Y, this, SDL_CONTROLLER_BUTTON_Y));
		
		addButton(new ButtonSDL(BUTTON_START, this, SDL_CONTROLLER_BUTTON_START));
		addButton(new ButtonSDL(BUTTON_SELECT, this, SDL_CONTROLLER_BUTTON_BACK));
		addButton(new ButtonSDL(BUTTON_HOME, this, SDL_CONTROLLER_BUTTON_GUIDE));

		addButton(new ButtonSDL(BUTTON_D_UP, this, SDL_CONTROLLER_BUTTON_DPAD_UP));
		addButton(new ButtonSDL(BUTTON_D_DOWN, this, SDL_CONTROLLER_BUTTON_DPAD_DOWN));
		addButton(new ButtonSDL(BUTTON_D_LEFT, this, SDL_CONTROLLER_BUTTON_DPAD_LEFT));
		addButton(new ButtonSDL(BUTTON_D_RIGHT, this, SDL_CONTROLLER_BUTTON_DPAD_RIGHT));
		
		addButton(new ButtonSDL(BUTTON_L1, this, SDL_CONTROLLER_BUTTON_LEFTSHOULDER));
		addButton(new ButtonSDL(BUTTON_R1, this, SDL_CONTROLLER_BUTTON_RIGHTSHOULDER));
		
		Button buttonStickLeft = addButton(new ButtonSDL(STICK_LEFT, this, SDL_CONTROLLER_BUTTON_LEFTSTICK));
		Button buttonStickRight = addButton(new ButtonSDL(STICK_RIGHT, this, SDL_CONTROLLER_BUTTON_RIGHTSTICK));
		
		addJoystick(new JoystickSDL(STICK_LEFT, buttonStickLeft, this, SDL_CONTROLLER_AXIS_LEFTX, SDL_CONTROLLER_AXIS_LEFTY));
		addJoystick(new JoystickSDL(STICK_RIGHT, buttonStickRight, this, SDL_CONTROLLER_AXIS_RIGHTX, SDL_CONTROLLER_AXIS_RIGHTY));
		
		addButton(new TriggerButtonSDL(BUTTON_L2, this, SDL_CONTROLLER_AXIS_TRIGGERLEFT));
		addButton(new TriggerButtonSDL(BUTTON_R2, this, SDL_CONTROLLER_AXIS_TRIGGERRIGHT));
	}
	
	@Override
	public void update() throws ControllerDisconnectException {
		if(!SDL_GameControllerGetAttached(pointer)) {
			pointer = 0;
			
			throw new ControllerDisconnectException();
		}
		
		super.update();
	}
	
	@Override
	public String getName() {
		if(pointer != 0) {
			return SDL_GameControllerName(pointer);
		}else {
			return null;
		}
	}

	@Override
	public ControllerLibrary getLibrary() {
		return library;
	}

	@Override
	public boolean isConnected() {
		return pointer != 0;
	}
	
	public int getIndex() {
		return index;
	}
	
	public long getPointer() {
		return pointer;
	}
	
	public boolean open() {
		if(pointer != 0) {
			throw new RuntimeException();
		}
		
		this.pointer = SDL_GameControllerOpen(index);
		
		return pointer != 0;
	}

}
