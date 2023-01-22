package b100.input.controllers;

public interface ControllerInputHandler {
	
	public void buttonEvent(Controller controller, Button button);
	
	public void joystickEvent(Controller controller, Joystick joystick);
	
}
