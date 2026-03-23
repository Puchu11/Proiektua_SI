package Bista;

import java.awt.Color;
import java.util.Observer;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Eredua.EntitateMota;

public class GelaxkaB extends JLabel implements Observer {
	private JLabel label;
	
	public GelaxkaB() {
		this.label= new JLabel();
		setOpaque(true);
	    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	    setBackground(Color.BLACK);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof EntitateMota entitate) {
				switch (entitate) {
                	case ETSAIA -> 	setBackground(Color.GREEN);
                	case ESPAZIONTZIA -> setBackground(Color.RED);
                	case TIROA -> setBackground(Color.YELLOW);
                	default -> setBackground(Color.BLACK);
          
				}
		}
	}
}