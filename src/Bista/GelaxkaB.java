package Bista;

import java.awt.Color;
import java.util.Observer;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class GelaxkaB extends JLabel implements Observer {
	private JLabel label;
	
	public GelaxkaB() {
		this.label= new JLabel();
		setOpaque(true);
	    setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	    setBackground(Color.WHITE);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			if(arg.equals("etsaia")) {
				this.label.setBackground(Color.RED);
			}else if(arg.equals("espaziontzia")) {
				this.label.setBackground(Color.BLUE);
			}else if(arg.equals("tiro")) {
				this.label.setBackground(Color.YELLOW);
			}else {
				this.label.setBackground(Color.WHITE);
			}
		}
	}
		
}
