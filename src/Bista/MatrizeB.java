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
import Eredua.JokoKudeatzailea;
import Eredua.MatrizeE;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
public class MatrizeB extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel kontenedorea; 
	private CardLayout nabegadorea; 
    private JPanel jokoPanela;
    private HasierakoPantaila hasierakoPantaila; 
	
	private GelaxkaB labelN[][] = new GelaxkaB[60][100];

	private Controller controller = null;

	public MatrizeB() {
		setTitle("Space Invaders");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		nabegadorea = new CardLayout();
		kontenedorea = new Jpanel (nabegadorea); 
		
		hasierakoPantaila = new HasierakoPantaila();
		jokoPanela = getPanel();
		
		kontenedorea.add(hasierakoPantaila, "HASIERA");
		kontenedorea.add(jokoPanela, "JOKOA");
		getContentPane().add(kontenedorea);
		
		
		MatrizeE Eredua = MatrizeE.getEma();
		
		Eredua.addObserver(this);
		
		JokoKudeatzailea.getNireJokoKudeatzailea().addObserver(this);
		
		
		matrizeaSortu();
		
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
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
				jokoPanela.add(gelaxkaB);
				
				if (Eredua.getGelaxka(j, i).getEntitateMota().equals("espaziontzia")) {
					labelN[i][j].setBackground(Color.RED);
				}else if (Eredua.getGelaxka(j, i).getEntitateMota().equals("etsaia")) {
					labelN[i][j].setBackground(Color.GREEN);
				}else if (Eredua.getGelaxka(j, i).getEntitateMota().equals("tiro")) {
					labelN[i][j].setBackground(Color.GRAY);
				} else {
					labelN[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if ("HASI".equals(arg)) {
			navegador.show(kontenedorea, "JOKOA")
			this.rewuestFocusInWindow();

		}
			
	}

	private JPanel getPanel() {
		if (jokoPanela == null) {
			jokoPanela = new JPanel();
			jokoPanela.setLayout(new GridLayout(60, 100, 0, 0));
		}
		return jokoPanela;
	}

	private Controller getController() {
		if (controller == null) controller = new Controller();
		return controller;
	}

	private class Controller implements KeyListener {

	    private static final int MUGITU_TICK_MS = 100;
	    private static final int TIRO_DELAY_MS = 300;

	    private final Timer EzkerTimer  = new Timer(MUGITU_TICK_MS, e -> MatrizeE.getEma().mugituEspaziontzia("ezkerrera"));
	    private final Timer EskuinTimer = new Timer(MUGITU_TICK_MS, e -> MatrizeE.getEma().mugituEspaziontzia("eskuinera"));
	    private final Timer GoraTimer    = new Timer(MUGITU_TICK_MS, e -> MatrizeE.getEma().mugituEspaziontzia("gora"));
	    private final Timer BeheraTimer  = new Timer(MUGITU_TICK_MS, e -> MatrizeE.getEma().mugituEspaziontzia("behera"));

	    private final Timer TiroTimer = new Timer(TIRO_DELAY_MS, e -> MatrizeE.getEma().tiroEgin());
	    
	    private void GeldituMugimenduTimerrak() {
	        EzkerTimer.stop();
	        EskuinTimer.stop();
	        GoraTimer.stop();
	        BeheraTimer.stop();	
	    }
	    
	    @Override
	    public void keyPressed(KeyEvent e) {
	    	if (e.getKeyCode()== KeyEvent.VK_ENTER) {
	    		JokoKudeatzailea.getNireJokoKudeatzailea().hasiJokoa();
	    	}
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_LEFT -> {
	                if (!EzkerTimer.isRunning()) {
	                	GeldituMugimenduTimerrak();
	                    MatrizeE.getEma().mugituEspaziontzia("ezkerrera");
	                    EzkerTimer.start();
	                }
	            }
	            case KeyEvent.VK_RIGHT -> {
	                if (!EskuinTimer.isRunning()) {
	                	GeldituMugimenduTimerrak();
	                    MatrizeE.getEma().mugituEspaziontzia("eskuinera");
	                    EskuinTimer.start();
	                }
	            }
	            case KeyEvent.VK_UP -> {
	                if (!GoraTimer.isRunning()) {
	                	GeldituMugimenduTimerrak();
	                    MatrizeE.getEma().mugituEspaziontzia("gora");
	                    GoraTimer.start();
	                }
	            }
	            case KeyEvent.VK_DOWN -> {
	                if (!BeheraTimer.isRunning()) {
	                	GeldituMugimenduTimerrak();
	                    MatrizeE.getEma().mugituEspaziontzia("behera");
	                    BeheraTimer.start();
	                }
	            }
	            case KeyEvent.VK_SPACE -> {
	                if (!TiroTimer.isRunning()) {
	                    MatrizeE.getEma().tiroEgin();
	                    TiroTimer.start();
	                }
	            }
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_LEFT  -> EzkerTimer.stop();
	            case KeyEvent.VK_RIGHT -> EskuinTimer.stop();
	            case KeyEvent.VK_UP    -> GoraTimer.stop();
	            case KeyEvent.VK_DOWN  -> BeheraTimer.stop();
	            case KeyEvent.VK_SPACE -> TiroTimer.stop();
	        }
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {}
	}
}