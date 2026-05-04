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