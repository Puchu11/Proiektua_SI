package Bista;

import java.awt.Color;
import java.util.Observer;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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
		
		if (arg instanceof String entitate) {
			SwingUtilities.invokeLater(() -> {	
				switch (entitate) {
                	case "etsaia" -> setBackground(Color.GREEN);
                	case "espaziontzia" -> setBackground(Color.RED);
                	case "tiro" -> setBackground(Color.YELLOW);
                	default -> setBackground(Color.BLACK);
            	}
            repaint(); 
			
			});
		}
	}
}