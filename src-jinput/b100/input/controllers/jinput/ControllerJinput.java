package b100.input.controllers.jinput;

import b100.input.controllers.Button;
import b100.input.controllers.Controller;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;
import net.java.games.input.Component;

public class ControllerJinput extends Controller {
	
	private ControllerLibraryJInput library;
	
	private net.java.games.input.Controller device;
	private Component[] components;
	
	public ControllerJinput(ControllerLibraryJInput library, ControllerInputHandler controllerInputHandler, net.java.games.input.Controller device) {
		super(controllerInputHandler);
		this.library = library;
		this.device = device;
		this.components = device.getComponents();
		
		setupButtons();
	}
	
	public void setupButtons() {
		ComponentListener defaultListener = ComponentListener.defaultListener;
		
		addButton(new ButtonJInput(BUTTON_A, comp("0"), defaultListener));
		addButton(new ButtonJInput(BUTTON_B, comp("1"), defaultListener));
		
		addButton(new ButtonJInput(BUTTON_X, comp("2"), defaultListener));
		addButton(new ButtonJInput(BUTTON_Y, comp("3"), defaultListener));

		addButton(new ButtonJInput(BUTTON_START, comp("7"), defaultListener));
		addButton(new ButtonJInput(BUTTON_SELECT, comp("6"), defaultListener));

		addButton(new ButtonJInput(BUTTON_L1, comp("4"), defaultListener));
		addButton(new ButtonJInput(BUTTON_R1, comp("5"), defaultListener));

		addButton(new ButtonJInput(BUTTON_L2, comp("z"), (comp) -> comp.getPollData() > 0.5f));
		addButton(new ButtonJInput(BUTTON_R2, comp("z"), (comp) -> comp.getPollData() < -0.5f));
		
		Component dpad = comp("pov");
		addButton(new ButtonJInput(BUTTON_D_UP, dpad, (comp) -> comp.getPollData() > 0.1 && comp.getPollData() < 0.4));
		addButton(new ButtonJInput(BUTTON_D_RIGHT, dpad, (comp) -> comp.getPollData() > 0.3 && comp.getPollData() < 0.7));
		addButton(new ButtonJInput(BUTTON_D_DOWN, dpad, (comp) -> comp.getPollData() > 0.6 && comp.getPollData() < 0.9));
		addButton(new ButtonJInput(BUTTON_D_LEFT, dpad, (comp) -> (comp.getPollData() > 0.8 || comp.getPollData() < 0.2) && comp.getPollData() > 0.0));
		
		Button joyLeftButton = addButton(new ButtonJInput(BUTTON_LEFT_STICK, comp("8"), defaultListener));
		Button joyRightButton = addButton(new ButtonJInput(BUTTON_RIGHT_STICK, comp("9"), defaultListener));
		
		addJoystick(new JoystickJinput(STICK_LEFT, comp("x"), comp("y"), joyLeftButton));
		addJoystick(new JoystickJinput(STICK_RIGHT, comp("rx"), comp("ry"), joyRightButton));
	}
	
	@Override
	public void update() throws ControllerDisconnectException {
		if(!device.poll()) {
			throw new ControllerDisconnectException();
		}
		
		super.update();
	}
	
	private Component comp(String id) {
		try {
			for(Component component : components) {
				if(component.getIdentifier().toString().equalsIgnoreCase(id)) {
					return component;
				}
			}
		}catch (Exception e) {}
		
		return null;
	}
	
	@Override
	public String getName() {
		return device.getName();
	}
	
	public net.java.games.input.Controller getDevice() {
		return device;
	}
	
	@Override
	public ControllerLibrary getLibrary() {
		return library;
	}

	@Override
	public boolean isConnected() {
		return true;
	}

}
