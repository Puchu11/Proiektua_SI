package Bista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Eredua.Egoera;
import Eredua.JokoKudeatzailea;

public class HasierakoPantaila extends JFrame implements Observer{

	private Controller controller = null;

	public HasierakoPantaila() {
		setTitle("Space Invaders");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		setLayout(new GridLayout(2, 1));
		setFocusable(true);

		JLabel titulua = new JLabel("SPACE INVADERS", SwingConstants.CENTER);
		titulua.setForeground(Color.WHITE);
		titulua.setFont(new Font("Arial", Font.BOLD, 40));

		JLabel azpititulua = new JLabel("Sakatu ENTER hasteko", SwingConstants.CENTER);
		azpititulua.setForeground(Color.YELLOW);
		azpititulua.setFont(new Font("Arial", Font.PLAIN, 18));

		add(titulua);
		add(azpititulua);

		JokoKudeatzailea.getNireJokoKudeatzailea().addObserver(this);
		addKeyListener(getController());

	}
	
	public void update(java.util.Observable o, Object arg) {
		if (arg instanceof Egoera egoera) {
			if (egoera == Egoera.HASIERA) {
				setVisible(true);
				requestFocusInWindow();
			}else {
				setVisible(false);
			}
		}
	}
	
	private Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private class Controller implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.JOKATZEN);
				setVisible(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
}