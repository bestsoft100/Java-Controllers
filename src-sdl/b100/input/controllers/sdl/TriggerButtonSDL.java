package b100.input.controllers.sdl;

import static org.libsdl.SDL.*;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;

public class TriggerButtonSDL extends Button {

	private ControllerSDL controller;
	private int button;
	
	private float deadZone = 0.5f;
	
	public TriggerButtonSDL(int id, ControllerSDL controller, int button) {
		super(id);
		
		this.controller = controller;
		this.button = button;
	}

	@Override
	protected boolean pollState() throws ControllerDisconnectException {
		float v = SDL_GameControllerGetAxis(controller.getPointer(), button) / 32767.0f;
		
		return v > deadZone;
	}
	
	public void setDeadZone(float deadZone) {
		this.deadZone = deadZone;
	}
	
	public float getDeadZone() {
		return deadZone;
	}

}
