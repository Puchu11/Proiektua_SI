package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HasierakoPantaila extends JPanel {
	public HasierakoPantaila() {
		this.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(2,1));
		
		JLabel titulua = new JLabel ("SPACE INVADERS", SwingConstants.CENTER);
		titulua.setForeground(Color.WHITE);
		titulua.setFont(new Font("Arial", Font.BOLD, 40)); 
		
		JLabel azpititulua = new JLabel ("Sakatu ENTER hasteko", SwingConstants.CENTER);
		azpititulua.setForeground(Color.YELLOW);
		azpititulua.setFont(new Font("Arial", Font.PLAIN, 18));
		
		this.add(titulua);
		this.add(azpititulua);
	}
	
}
