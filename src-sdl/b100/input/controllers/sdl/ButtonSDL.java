package b100.input.controllers.sdl;

import static org.libsdl.SDL.*;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;

public class ButtonSDL extends Button {

	private ControllerSDL controller;
	private int button;
	
	public ButtonSDL(int id, ControllerSDL controller, int button) {
		super(id);
		
		this.controller = controller;
		this.button = button;
	}

	@Override
	protected boolean pollState() throws ControllerDisconnectException {
		return SDL_GameControllerGetButton(controller.getPointer(), button) != 0;
	}

}
