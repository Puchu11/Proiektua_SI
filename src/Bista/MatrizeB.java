package Bista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Eredua.Egoera;
import Eredua.JokoKudeatzailea;
import Eredua.MatrizeE;
public class MatrizeB extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private JPanel kontenedorea; 
	private CardLayout nabegadorea; 
    private JPanel jokoPanela;
    private IrabaziPantaila irabaziPantaila; 
    private GalduPantaila galduPantaila; 
	private GelaxkaB labelN[][] = new GelaxkaB[60][100];

	private Controller controller = null;

	public MatrizeB() {
		setTitle("Space Invaders");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		nabegadorea = new CardLayout();
		kontenedorea = new JPanel (nabegadorea); 
		
		jokoPanela = getPanel();
		irabaziPantaila=new IrabaziPantaila();
		galduPantaila=new GalduPantaila();
		kontenedorea.add(jokoPanela, "JOKOA");
	    kontenedorea.add(irabaziPantaila,"IRABAZI_PANTAILA");
		kontenedorea.add(galduPantaila,"GALDU_PANTAILA");
		getContentPane().add(kontenedorea);
		
		
		MatrizeE Eredua = MatrizeE.getEma();
		
		
		JokoKudeatzailea.getNireJokoKudeatzailea().addObserver(this);
		
		
		matrizeaSortu();
		
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
		setVisible(true);
		
		new Timer(16, e -> kontenedorea.repaint()).start();
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
		if (arg == Egoera.JOKATZEN) {
			nabegadorea.show(kontenedorea, "JOKOA");
			this.requestFocusInWindow();
		} else if (arg == Egoera.IRABAZI) {
			nabegadorea.show(kontenedorea, "IRABAZI_PANTAILA");
		} else if (arg == Egoera.GALDU) {
			nabegadorea.show(kontenedorea, "GALDU_PANTAILA");
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