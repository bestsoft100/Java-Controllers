package b100.input.controllers;

import java.util.ArrayList;
import java.util.List;

public abstract class ControllerLibrary {
	
	public final List<Controller> controllers = new ArrayList<>();
	
	public abstract void create(ControllerInputHandler controllerInputHandler);
	
	public abstract void update();

}
