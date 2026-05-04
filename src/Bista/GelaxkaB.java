package Bista;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import Eredua.GelaxkaEgoera;

public class GelaxkaB extends JLabel implements Observer {
	
	
	public GelaxkaB() {
		setOpaque(true);
	    setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	    setBackground(Color.BLACK);
	}	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String kolorea) {
			switch (kolorea.toLowerCase()) {
				case "berdea":
					setBackground(Color.GREEN);
					break;
				case "urdina":
					setBackground(Color.BLUE);
					break;
				case "gorria":
					setBackground(Color.RED);
					break;
				case "horia":
					setBackground(Color.YELLOW);
					break;
				case "zuria":
					setBackground(Color.WHITE);
					break;
				case "beltza":
					setBackground(Color.BLACK);
					break;
				case "larrosa":
					setBackground(Color.BLACK);
					break;
				default:
					setBackground(Color.BLACK);
					break;
			}
			repaint();
		}
	}
}