package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GalduPantaila extends JPanel {
<<<<<<< HEAD

    private JLabel puntuazioa;
    private Image fondo;

    public GalduPantaila() {
        setLayout(null);
        setBackground(Color.BLACK);

        fondo = new ImageIcon(getClass().getResource("/portada/GalduPantaila.png")).getImage();

        puntuazioa = new JLabel("", SwingConstants.CENTER);
        puntuazioa.setBounds(0, 300, 900, 50);
        puntuazioa.setForeground(Color.RED); 
        puntuazioa.setFont(new Font("Arial", Font.BOLD, 30));
        add(puntuazioa);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void eguneratuTestua(int puntuak) {
        puntuazioa.setText("ZURE PUNTUAZIOA: " + puntuak);
    }
}
=======
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
>>>>>>> branch 'Jara' of https://github.com/Puchu11/Proiektua_SI.git
