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
		if (arg instanceof Color kolorea) {
				
               setBackground(kolorea);
               repaint();
		}
	}
}