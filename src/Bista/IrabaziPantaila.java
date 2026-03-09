package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IrabaziPantaila extends JPanel {
	public IrabaziPantaila() {
		this.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(2,1));
		
		JLabel titulua = new JLabel ("IRABAZI DOZU", SwingConstants.CENTER);
		titulua.setForeground(Color.WHITE);
		titulua.setFont(new Font("Arial", Font.BOLD, 40)); 
		this.add(titulua);
	}
	
}
