package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IrabaziPantaila extends JPanel {
<<<<<<< HEAD

    private JLabel puntuazioa;
    private Image fondo;

    public IrabaziPantaila() {
        setLayout(null); 
        setBackground(Color.BLACK);

        fondo = new ImageIcon(getClass().getResource("/portada/IrabaziPantaila.png")).getImage();

        puntuazioa = new JLabel("", SwingConstants.CENTER);
        puntuazioa.setBounds(0, 300, 900, 50); // ← ajusta altura aquí
=======
	private JLabel puntuazioa;
	private JLabel instrukzioa;
	
	public IrabaziPantaila() {
		this.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(3,1));
		
		JLabel titulua = new JLabel ("IRABAZI DOZU", SwingConstants.CENTER);
		titulua.setForeground(Color.WHITE);
		titulua.setFont(new Font("Arial", Font.BOLD, 40)); 
		this.add(titulua);
		
		puntuazioa = new JLabel("", SwingConstants.CENTER);
>>>>>>> branch 'Jara' of https://github.com/Puchu11/Proiektua_SI.git
        puntuazioa.setForeground(Color.YELLOW);
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