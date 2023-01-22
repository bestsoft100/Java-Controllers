package b100.input.controllers;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller {
	
	public static final int BUTTON_A = 0;
	public static final int BUTTON_B = 1;
	public static final int BUTTON_X = 2;
	public static final int BUTTON_Y = 3;
	
	public static final int BUTTON_L1 = 4;
	public static final int BUTTON_R1 = 5;
	
	public static final int BUTTON_L2 = 6;
	public static final int BUTTON_R2 = 7;
	
	public static final int BUTTON_D_UP = 7;
	public static final int BUTTON_D_LEFT = 8;
	public static final int BUTTON_D_DOWN = 9;
	public static final int BUTTON_D_RIGHT = 10;
	
	public static final int BUTTON_START = 11;
	public static final int BUTTON_SELECT = 12;
	public static final int BUTTON_HOME = 13;
	
	public static final int BUTTON_JOY_LEFT = 14;
	public static final int BUTTON_JOY_RIGHT = 15;
	
	public static final int JOY_LEFT = 16;
	public static final int JOY_RIGHT = 17;
	
	///////////////////////////////////////////////
	
	protected List<Button> buttons = new ArrayList<>();
	protected List<Joystick> joysticks = new ArrayList<>();
	
	private ControllerInputHandler controllerInputHandler;
	
	public Controller(ControllerInputHandler controllerInputHandler) {
		this.controllerInputHandler = controllerInputHandler;
	}
	
	protected Button addButton(Button button) {
		this.buttons.add(button);
		return button;
	}
	
	protected Joystick addJoystick(Joystick joystick) {
		this.joysticks.add(joystick);
		return joystick;
	}
	
	public void update() throws ControllerDisconnectException {
		for(int i=0; i < buttons.size(); i++) {
			buttons.get(i).update();
		}
		for(int i=0; i < joysticks.size(); i++) {
			joysticks.get(i).update();
		}
		for(int i=0; i < buttons.size(); i++) {
			Button button = buttons.get(i);
			if(button.isUpdated()) {
				controllerInputHandler.buttonEvent(this, button);
			}
		}
		for(int i=0; i < joysticks.size(); i++) {
			Joystick joystick = joysticks.get(i);
			if(joystick.isMovementChanged()) {
				controllerInputHandler.joystickEvent(this, joystick);
			}
			joysticks.get(i).update();
		}
	}
	
	public abstract String getName();
	
	public abstract ControllerLibrary getLibrary();
	
	@Override
	public String toString() {
		return "Controller: "+getName();
	}
	
}
