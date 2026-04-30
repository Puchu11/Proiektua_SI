package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GalduPantaila extends JPanel {
	private JLabel puntuazioa;
	private JLabel instrukzioa;
	
	public GalduPantaila() {
		this.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(3,1));
		
		JLabel titulua = new JLabel ("GALDU DOZU", SwingConstants.CENTER);
		titulua.setForeground(Color.WHITE);
		titulua.setFont(new Font("Arial", Font.BOLD, 40)); 
		this.add(titulua);
		
		
		puntuazioa = new JLabel("", SwingConstants.CENTER);
        puntuazioa.setForeground(Color.YELLOW);
        puntuazioa.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(puntuazioa);
        
        instrukzioa = new JLabel("", SwingConstants.CENTER);
        instrukzioa.setForeground(Color.GRAY);
        instrukzioa.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(instrukzioa);
	}
	public void eguneratuTestua(int puntuak) {
	    puntuazioa.setText("ZURE PUNTUAZIOA: " + puntuak);
	    instrukzioa.setText("Pultsatu 'R'berriz jolasteko edo 'ESC' irteteko" );
	}
}
