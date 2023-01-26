package b100.input.controllers.jamepad;

import com.studiohartman.jamepad.ControllerAxis;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerUnpluggedException;

import b100.input.controllers.Button;
import b100.input.controllers.ControllerDisconnectException;

public class AxisButtonJamepad extends Button {

	private ControllerIndex index;
	private ControllerAxis axis;

	private float deadZone = 0.5f;
	
	public AxisButtonJamepad(int id, ControllerIndex index, ControllerAxis axis) {
		super(id);
		
		this.index = index;
		this.axis = axis;
	}
	
	@Override
	protected boolean pollState() throws ControllerDisconnectException {
		try {
			return index.getAxisState(axis) > deadZone;
		} catch (ControllerUnpluggedException e) {
			throw new ControllerDisconnectException();
		}
	}
	
	public void setDeadZone(float deadZone) {
		this.deadZone = deadZone;
	}
	
	public float getDeadZone() {
		return deadZone;
	}

}
