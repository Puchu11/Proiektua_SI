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

	private JLabel labelN[][] = new JLabel[60][100];

	private Controller controller = null;

	public Matrizea() {
		setTitle("Space Invaders");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPanel(), BorderLayout.CENTER);


		matrizeaSortu();

		// observer
		MatrizeKudeatzailea.getEma().addObserver(this);
		
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
		requestFocusInWindow();

		setVisible(true);
	}

	public void matrizeaSortu() {
		MatrizeKudeatzailea Eredua = MatrizeKudeatzailea.getEma(); // espaziontzia sortu eta matrizean jarri
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 100; j++) {
				if(Eredua.getMatrizea()[i][j] instanceof Espaziontzia) {
					labelN[i][j] = getLabelN(i, j, Color.RED);
					panel.add(labelN[i][j]);	
				}else if(Eredua.getMatrizea()[i][j] instanceof Etsaia) {
					labelN[i][j] = getLabelN(i, j, Color.GREEN);
					panel.add(labelN[i][j]);
					
				}
				else {
					labelN[i][j] = getLabelN(i, j, Color.WHITE);
					panel.add(labelN[i][j]);
				}
			}
		}
		panel.revalidate();
		panel.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Espaziontzia) {

		Espaziontzia e = (Espaziontzia) arg;
		int x = e.getPosizioa().getX();
		int y = e.getPosizioa().getY();
		
		if (y >= 0 && y < 60 && x >= 0 && x < 100) {
			labelN[y][x].setBackground(Color.RED);
			
			if(y + 1 < 60 && labelN[y+1][x].getBackground().equals(Color.RED)) {
				labelN[y+1][x].setBackground(Color.WHITE);
			}
			if(y-1 >=0 && labelN[y-1][x].getBackground().equals(Color.RED)) {
				labelN[y-1][x].setBackground(Color.WHITE);
			}
			if(x+1 < 100 && labelN[y][x+1].getBackground().equals(Color.RED)) {
				labelN[y][x+1].setBackground(Color.WHITE);
			}
			if(x-1 >= 0 && labelN[y][x-1].getBackground().equals(Color.RED)) {
				labelN[y][x-1].setBackground(Color.WHITE);
			}
		}

		panel.repaint();
	
		}
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
				MatrizeKudeatzailea.getEma().Mugitu(-1, 0);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				MatrizeKudeatzailea.getEma().Mugitu(1, 0);
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				MatrizeKudeatzailea.getEma().Mugitu(0, -1);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				MatrizeKudeatzailea.getEma().Mugitu(0, 1);
			}
		}

		@Override public void keyReleased(KeyEvent e) {}
		@Override public void keyTyped(KeyEvent e) {}
	}
}