package classes;

import java.awt.Color;

public class NotPaid implements State {
	
	private final Color color = Color.red;
	
	public void act(){
		
	}
	
	public Color getColor() {
		return color;
	}

}
