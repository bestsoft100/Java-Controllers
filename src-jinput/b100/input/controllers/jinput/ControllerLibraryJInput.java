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
	
	@Override
	public void create(ControllerInputHandler controllerInputHandler) {
		controllerEnvironment = ControllerEnvironment.getDefaultEnvironment();
		
		Controller[] devices = controllerEnvironment.getControllers();
		
		for(Controller device : devices) {
			Type type = device.getType();
			
			if(type == Type.GAMEPAD) {
				controllers.add(new ControllerJinput(this, controllerInputHandler, device));
			}
		}
		
		initialized = true;
	}
	
	@Override
	public void update() {
		for(int i=0; i < controllers.size(); i++) {
			b100.input.controllers.Controller controller = controllers.get(i);
			try {
				controller.update();
			}catch (ControllerDisconnectException e) {
				controllers.remove(i--);
				
				onControllerDisconnected(controller);
			}
		}
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
