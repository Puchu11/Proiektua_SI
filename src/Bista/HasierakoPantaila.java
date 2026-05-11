package Bista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Eredua.AudioKudeatzailea;
import Eredua.Egoera;
import Eredua.JokoKudeatzailea;

public class HasierakoPantaila extends JFrame implements Observer {

    private Controller controller = null;

    public HasierakoPantaila() {
        JokoKudeatzailea.getNireJokoKudeatzailea().addObserver(this);
    }

    public void update(java.util.Observable o, Object arg) {
        if (arg instanceof Egoera egoera) {
            if (egoera == Egoera.HASIERA) {
                setTitle("Space Invaders");
                setBounds(100, 100, 900, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setContentPane(new FondoPanel());

                addKeyListener(getController());
                setFocusable(true);
                setVisible(true);
                requestFocusInWindow();

            } else {
                setVisible(false);
                dispose();
            }
        }
    }

    private Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    private class FondoPanel extends JPanel {

        private Image fondo;

        public FondoPanel() {
            fondo = new ImageIcon(getClass().getResource("/portada/HasierakoPantaila.png")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            
            //Laguntza testua marraztu pantailan musika menurako
            g.setColor(java.awt.Color.WHITE);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
            g.drawString("Audio Kontrola_ [M] Mute |[+] Bolumena igo | [-] Bolumena jaitsi" ,20 , 540);
        }
    }

    private class Controller implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.JOKATZEN);
            }

            if (e.getKeyCode() == KeyEvent.VK_G) {
                JokoKudeatzailea.getNireJokoKudeatzailea().setEspaziontziMota("green");
            }

            if (e.getKeyCode() == KeyEvent.VK_B) {
                JokoKudeatzailea.getNireJokoKudeatzailea().setEspaziontziMota("blue");
            }

            if (e.getKeyCode() == KeyEvent.VK_R) {
                JokoKudeatzailea.getNireJokoKudeatzailea().setEspaziontziMota("red");
            }
            if (e.getKeyCode()==KeyEvent.VK_M) {
            	AudioKudeatzailea.getAudioKudeatzailea().musikaMuteatu();
            }
            if (e.getKeyCode()==KeyEvent.VK_PLUS|| e.getKeyCode() == KeyEvent.VK_ADD) {
            	AudioKudeatzailea.getAudioKudeatzailea().bolumenaJaitsi(-3.0f);
            }
            if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT) {

                AudioKudeatzailea.getAudioKudeatzailea().bolumenaJaitsi(3.0f);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}