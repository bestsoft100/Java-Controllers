package b100.input.controllers.jinput;

import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;

public class ControllerLibraryJInput extends ControllerLibrary {
	
	private ControllerEnvironment controllerEnvironment;
	
	private boolean initialized = false;
	
	private ControllerInputHandler controllerInputHandler;
	
	@Override
	public void create(ControllerInputHandler controllerInputHandler) {
		controllerEnvironment = ControllerEnvironment.getDefaultEnvironment();
		
		this.controllerInputHandler = controllerInputHandler;
		
		Controller[] devices = controllerEnvironment.getControllers();
		for(Controller device : devices) {
			if(device.getType() == Type.GAMEPAD) {
				connect(device);
			}
		}
		
		initialized = true;
	}
	
	@Override
	public void update() {
		for(int i=0; i < controllers.size(); i++) {
			ControllerJInput controller = (ControllerJInput) controllers.get(i);
			try {
				controller.update();
			}catch (ControllerDisconnectException e) {
				if(disconnect(controller)) {
					i--;
				}
			}
		}
	}
	
	private void connect(Controller device) {
		ControllerJInput controller = new ControllerJInput(this, controllerInputHandler, device);
		controllers.add(controller);
		
		if(initialized) {
			onControllerConnected(controller);
		}
	}
	
	private boolean disconnect(ControllerJInput controller) {
		controllers.remove(controller);
		
		onControllerDisconnected(controller);
		
		return true;
	}
	
	public ControllerJInput getControllerForDevice(Controller device) {
		for(int i=0; i < controllers.size(); i++) {
			ControllerJInput controller = (ControllerJInput) controllers.get(i);
			
			if(controller.getDevice() == device) {
				return controller;
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return "JInput";
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

}
