package b100.input.controllers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Controller {
	
	public static final int BUTTON_A = 0;
	public static final int BUTTON_B = 1;
	public static final int BUTTON_X = 2;
	public static final int BUTTON_Y = 3;
	
	public static final int BUTTON_L1 = 4;
	public static final int BUTTON_R1 = 5;
	
	public static final int BUTTON_L2 = 6;
	public static final int BUTTON_R2 = 7;
	
	public static final int BUTTON_D_UP = 8;
	public static final int BUTTON_D_LEFT = 9;
	public static final int BUTTON_D_DOWN = 10;
	public static final int BUTTON_D_RIGHT = 11;
	
	public static final int BUTTON_START = 12;
	public static final int BUTTON_SELECT = 13;
	public static final int BUTTON_HOME = 14;
	
	public static final int BUTTON_LEFT_STICK = 15;
	public static final int BUTTON_RIGHT_STICK = 16;
	
	public static final int STICK_LEFT = 17;
	public static final int STICK_RIGHT = 18;
	
	private static Map<Integer, String> buttonNames = new HashMap<>();
	private static Map<Integer, String> joystickNames = new HashMap<>();
	
	static {
		try {
			Field[] fields = Controller.class.getDeclaredFields();
			
			for(int i=0; i < fields.length; i++) {
				Field field = fields[i];
				int mod = field.getModifiers();
				
				if(Modifier.isStatic(mod) && Modifier.isPublic(mod) && field.getType() == int.class) {
					String name = field.getName();
					
					int val = field.getInt(null);
					
					if(name.startsWith("BUTTON_")) {
						buttonNames.put(val, name.substring(7));
					}
					if(name.startsWith("STICK_")) {
						joystickNames.put(val, name.substring(6));
					}
				}
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getButtonName(int id) {
		String name = buttonNames.get(id);
		
		return name != null ? name : String.valueOf(id);
	}
	
	public static String getJoystickName(int id) {
		String name = joystickNames.get(id);
		
		return name != null ? name : String.valueOf(id);
	}
	
	///////////////////////////////////////////////
	
	private List<Button> buttons = new ArrayList<>();
	private List<Joystick> joysticks = new ArrayList<>();
	
	private Map<Integer, Button> buttonMap = new HashMap<>();
	private Map<Integer, Joystick> joystickMap = new HashMap<>();
	
	private ControllerInputHandler controllerInputHandler;
	
	public Controller(ControllerInputHandler controllerInputHandler) {
		this.controllerInputHandler = controllerInputHandler;
	}
	
	protected Button addButton(Button button) {
		this.buttons.add(button);
		this.buttonMap.put(button.getId(), button);
		return button;
	}
	
	protected Joystick addJoystick(Joystick joystick) {
		this.joysticks.add(joystick);
		this.joystickMap.put(joystick.getId(), joystick);
		return joystick;
	}
	
	public Button getButton(int id) {
		return buttonMap.get(id);
	}
	
	public Joystick getJoystick(int id) {
		return joystickMap.get(id);
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
	
	public abstract boolean isConnected();
	
	@Override
	public String toString() {
		return "Controller: "+getName();
	}
	
}
