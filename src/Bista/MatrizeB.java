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
import Eredua.MatrizeE;
import javax.swing.Timer;

public class MatrizeB extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private GelaxkaB labelN[][] = new GelaxkaB[60][100];

	private Controller controller = null;

	public MatrizeB() {
		setTitle("Space Invaders");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		
		MatrizeE Eredua = MatrizeE.getEma();
		
		Eredua.addObserver(this);
		
		matrizeaSortu();
		
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
		requestFocusInWindow();

		setVisible(true);
		
		new Timer(16, e -> panel.repaint()).start();
	}

	public void matrizeaSortu() {
		MatrizeE Eredua = MatrizeE.getEma(); 
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 100; j++) {
				GelaxkaB gelaxkaB = new GelaxkaB();
				Eredua.getGelaxka(j, i).addObserver(gelaxkaB);
				labelN[i][j] = gelaxkaB;
				getPanel().add(gelaxkaB);
				if (Eredua.getGelaxka(j, i).getEntitateMota().equals("espaziontzia")) {
					labelN[i][j].setBackground(Color.RED);
				}else if (Eredua.getGelaxka(j, i).getEntitateMota().equals("etsaia")) {
					labelN[i][j].setBackground(Color.GREEN);
				}else if (Eredua.getGelaxka(j, i).getEntitateMota().equals("tiroa")) {
					labelN[i][j].setBackground(Color.GRAY);
				} else {
					labelN[i][j].setBackground(Color.BLACK);
				}
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

	private Controller getController() {
		if (controller == null) controller = new Controller();
		return controller;
	}

	private class Controller implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				MatrizeE.getEma().mugituEspaziontzia("ezkerrera");
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				MatrizeE.getEma().mugituEspaziontzia("eskuinera");
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				MatrizeE.getEma().mugituEspaziontzia("gora");
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				MatrizeE.getEma().mugituEspaziontzia("behera");
			} if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				MatrizeE.getEma().tiroEgin();
			}
		}

		@Override public void keyReleased(KeyEvent e) {}
		@Override public void keyTyped(KeyEvent e) {}
	}
}