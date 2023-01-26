package b100.input.controllers.jinput;

import net.java.games.input.Component;

public interface ComponentListener {
	
	public static final ComponentListener defaultListener = (comp) -> comp.getPollData() > 0.5f;
	
	public boolean isPressed(Component comp);
	
}
