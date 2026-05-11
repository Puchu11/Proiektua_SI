package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Eredua.JokoKudeatzailea;

public class IrabaziPantaila extends JPanel {

    private JLabel puntuazioa;
    private JLabel instrukzioa;
    private Image fondo;

    public IrabaziPantaila() {
        this.setLayout(null); // Posizioak zehatz kudeatzeko irudiaren gainean
        this.setBackground(Color.BLACK);

        // Atzeko planoko irudia kargatu
        try {
            fondo = new ImageIcon(getClass().getResource("/portada/IrabaziPantaila.png")).getImage();
        } catch (Exception e) {
            System.out.println("Ezin izan da irabazi pantailako irudia kargatu: " + e.getMessage());
        }

        // Puntuazioa eta estatistikak erakusteko label-a
        puntuazioa = new JLabel("", SwingConstants.CENTER);
        puntuazioa.setBounds(0, 300, 900, 50); // Irudiaren gainean kokatzeko altuera
        puntuazioa.setForeground(Color.YELLOW);
        puntuazioa.setFont(new Font("Arial", Font.BOLD, 26));
        this.add(puntuazioa);

        // Jarraitzeko instrukzioak
        instrukzioa = new JLabel("Sakatu 'R' berriro jolasteko edo 'ESC' irteteko", SwingConstants.CENTER);
        instrukzioa.setBounds(0, 370, 900, 40);
        instrukzioa.setForeground(Color.LIGHT_GRAY);
        instrukzioa.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(instrukzioa);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void eguneratuTestua(int puntuak) {
        int tiroak = JokoKudeatzailea.getNireJokoKudeatzailea().getTiroKopurua();
        puntuazioa.setText("ZURE PUNTUAZIOA: " + puntuak + "  |  TIROAK: " + tiroak);
    }
}