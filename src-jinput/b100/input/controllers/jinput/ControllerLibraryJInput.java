package b100.input.controllers.jinput;

import net.java.games.input.Controller.Type;
import b100.input.controllers.ControllerDisconnectException;
import b100.input.controllers.ControllerInputHandler;
import b100.input.controllers.ControllerLibrary;
import net.java.games.input.ControllerEnvironment;

public class ControllerLibraryJInput extends ControllerLibrary {
	
	private ControllerEnvironment controllerEnvironment;

	@Override
	public void create(ControllerInputHandler controllerInputHandler) {
		controllerEnvironment = ControllerEnvironment.getDefaultEnvironment();
		
		net.java.games.input.Controller[] devices = controllerEnvironment.getControllers();
		
		for(net.java.games.input.Controller device : devices) {
			Type type = device.getType();
			
			if(type == Type.GAMEPAD) {
				controllers.add(new ControllerJinput(this, controllerInputHandler, device));
			}
		}
	}
	
	@Override
	public void update() {
		for(int i=0; i < controllers.size(); i++) {
			try {
				controllers.get(i).update();
			}catch (ControllerDisconnectException e) {
				controllers.remove(i--);
			}
		}
	}

}
