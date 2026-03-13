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
		
		JokoKudeatzailea.getNireJokoKudeatzailea().addObserver(this);
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
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
			matrizeaSortu();
			setVisible(true);			
			new Timer(16, e -> kontenedorea.repaint()).start();
			nabegadorea.show(kontenedorea, "JOKOA");
			this.requestFocusInWindow();
			getController().hasi();
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
		
	    private static final int JOLAS_TICK_MS = 16;   
	    private static final int MUGIMENDU_DELAY_MS = 100; 
	    private static final int TIRO_DELAY_MS = 300; 

	    private boolean ezkerra = false;
	    private boolean eskuma = false;
	    private boolean gora = false;
	    private boolean behera = false;
	    private boolean tiro = false;

	    private long azkenMugimenduKontrola = 0;
	    private long azkenTiroKontrola = 0;

	    private final Timer mugimenduaEguneratu = new Timer(JOLAS_TICK_MS, e -> jolasEguneratu());

	    private void hasi() {
	    	mugimenduaEguneratu.start();
	    }

	    private void jolasEguneratu() {
	        long orain = System.currentTimeMillis();

	        if (orain - azkenMugimenduKontrola >= MUGIMENDU_DELAY_MS) {
	            if (ezkerra) {
	                MatrizeE.getEma().mugituEspaziontzia("ezkerrera");
	            } else if (eskuma) {
	                MatrizeE.getEma().mugituEspaziontzia("eskuinera");
	            } else if (gora) {
	                MatrizeE.getEma().mugituEspaziontzia("gora");
	            } else if (behera) {
	                MatrizeE.getEma().mugituEspaziontzia("behera");
	            }

	            azkenMugimenduKontrola = orain;
	        }

	        if (tiro && orain - azkenTiroKontrola >= TIRO_DELAY_MS) {
	            MatrizeE.getEma().tiroEgin();
	            azkenTiroKontrola = orain;
	        }
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_LEFT  -> ezkerra = true;
	            case KeyEvent.VK_RIGHT -> eskuma = true;
	            case KeyEvent.VK_UP    -> gora = true;
	            case KeyEvent.VK_DOWN  -> behera = true;
	            case KeyEvent.VK_SPACE -> tiro = true;
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_LEFT  -> ezkerra = false;
	            case KeyEvent.VK_RIGHT -> eskuma = false;
	            case KeyEvent.VK_UP    -> gora = false;
	            case KeyEvent.VK_DOWN  -> behera = false;
	            case KeyEvent.VK_SPACE -> tiro = false;
	        }
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {}
	}
}