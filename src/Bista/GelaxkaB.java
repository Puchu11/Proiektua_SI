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
		if (arg instanceof String entitate) {
            switch (entitate) {
                case "etsaia" -> setBackground(Color.GREEN);
                case "espaziontzia" -> setBackground(Color.RED);
                case "tiro" -> setBackground(Color.YELLOW);
                default -> setBackground(Color.WHITE);
            }
            repaint(); 
        }
	}
		
}
