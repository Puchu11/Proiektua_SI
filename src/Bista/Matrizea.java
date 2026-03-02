package Bista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Eredua.Espaziontzia;
import Eredua.Etsaia;
import Eredua.MatrizeKudeatzailea;

public class Matrizea extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private GelaxkaB labelN[][] = new GelaxkaB[60][100];

	private Controller controller = null;

	public Matrizea() {
		setTitle("Space Invaders");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		
		MatrizeKudeatzailea Eredua = MatrizeKudeatzailea.getEma(); 
		Eredua.addObserver(this);
		
		matrizeaSortu();
		
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
		requestFocusInWindow();

		setVisible(true);
	}

	public void matrizeaSortu() {
		MatrizeKudeatzailea Eredua = MatrizeKudeatzailea.getEma(); 
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 100; j++) {
				GelaxkaB gelaxkaB = new GelaxkaB();
				Eredua.getGelaxka(j, i).addObserver(gelaxkaB);
				labelN[i][j] = gelaxkaB;
				getPanel().add(gelaxkaB);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
			
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(60, 100, 0, 0));
		}
		return panel;
	}

	private JLabel getLabelN(int ler, int zut, Color kolorea) {
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		label.setBackground(kolorea);
		return label;
	}

	private Controller getController() {
		if (controller == null) controller = new Controller();
		return controller;
	}

	private class Controller implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				MatrizeKudeatzailea.getEma().mugitu("ezkerrera");
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				MatrizeKudeatzailea.getEma().mugitu("eskuinera");
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				MatrizeKudeatzailea.getEma().mugitu("gora");
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				MatrizeKudeatzailea.getEma().mugitu("behera");
			}
		}

		@Override public void keyReleased(KeyEvent e) {}
		@Override public void keyTyped(KeyEvent e) {}
	}
}