package b100.input.controllers;

import java.util.ArrayList;
import java.util.List;

public abstract class ControllerLibrary {
	
	protected List<ControllerListener> controllerListeners = new ArrayList<>();
	
	public final List<Controller> controllers = new ArrayList<>();
	
	public abstract void create(ControllerInputHandler controllerInputHandler);
	
	public abstract void update();
	
	public abstract String getName();
	
	public abstract boolean isInitialized();
	
	protected void onControllerConnected(Controller controller) {
		for(int i=0; i < controllerListeners.size(); i++) {
			controllerListeners.get(i).controllerConnected(controller);
		}
	}
	
	protected void onControllerDisconnected(Controller controller) {
		for(int i=0; i < controllerListeners.size(); i++) {
			controllerListeners.get(i).controllerDisconnected(controller);
		}
	}
	
	public void addControllerListener(ControllerListener controllerListener) {
		this.controllerListeners.add(controllerListener);
	}
	
	public boolean removeControllerListener(ControllerListener controllerListener) {
		return this.controllerListeners.remove(controllerListener);
	}
	
	public void removeAllControllerListeners() {
		controllerListeners.clear();
	}

}
