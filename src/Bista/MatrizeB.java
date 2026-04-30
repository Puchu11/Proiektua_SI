package Bista;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Eredua.Egoera;
import Eredua.GelaxkaE;
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
	private JLabel mezua = new JLabel("", SwingConstants.CENTER);
	private Timer mezuaTimer;
	private JLabel[] bihotzEtiketak;
	private JPanel bizitzaPanela;
	private JLabel bizitzaMezuaLabel;

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
		
		mezua.setForeground(Color.YELLOW);
		mezua.setFont(new Font("Arial", Font.BOLD, 20));
		mezua.setBounds(0, 20, 900, 50);
		mezua.setVisible(false);

		getLayeredPane().add(mezua, JLayeredPane.POPUP_LAYER);
		
		mezuaTimer = new Timer(3000, e -> mezua.setVisible(false));
		mezuaTimer.setRepeats(false);
		
		JokoKudeatzailea.getNireJokoKudeatzailea().addObserver(this);
		
		// controller 
		addKeyListener(getController());
		setFocusable(true);
		
		//bizitzak
		bizitzaPanela = new JPanel(new BorderLayout()); // BorderLayout erabiliko dugu barruan
		bizitzaPanela.setBackground(Color.BLACK);

		// Mezua (Ezkerrean)
		bizitzaMezuaLabel = new JLabel(""); 
		bizitzaMezuaLabel.setForeground(Color.RED); // Gorriz nabarmentzeko
		bizitzaMezuaLabel.setFont(new Font("Arial", Font.BOLD, 18));
		bizitzaPanela.add(bizitzaMezuaLabel, BorderLayout.WEST); // WEST = Ezkerra

		// Bihotzak (Eskuinean)
		JPanel bihotzenEremua = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bihotzenEremua.setBackground(Color.BLACK);
		bihotzEtiketak = new JLabel[3];
		for (int i = 0; i < 3; i++) {
		    bihotzEtiketak[i] = new JLabel("♥"); 
		    bihotzEtiketak[i].setForeground(new Color(255, 105, 180));
		    bihotzEtiketak[i].setFont(new Font("Arial", Font.BOLD, 30));
		    bihotzenEremua.add(bihotzEtiketak[i]);
		}
		bizitzaPanela.add(bihotzenEremua, BorderLayout.EAST); // EAST = Eskuina

		this.add(bizitzaPanela, BorderLayout.NORTH);

	}
	
	private void matrizeaSortu() {
		jokoPanela.removeAll();
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 100; j++) {
				GelaxkaB gelaxkaB = new GelaxkaB();
				GelaxkaE gelaxkaE = MatrizeE.getEma().getGelaxka(j, i);
				gelaxkaE.addObserver(gelaxkaB);
				labelN[i][j] = gelaxkaB;
				jokoPanela.add(gelaxkaB);	
				gelaxkaB.setBackground(gelaxkaE.getEgoera().lortuKolorea());
			}
		}
		jokoPanela.revalidate();
	    jokoPanela.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
	    if (arg instanceof Egoera) {
	        Egoera e = (Egoera) arg;
	        
	        if (e == Egoera.JOKATZEN) {
	        	bizitzaPanela.setVisible(true);
	        	//bizitzak jokoa berriz hastean berriz marraztu
	            for (int i = 0; i < 3; i++) {
	                bihotzEtiketak[i].setVisible(true);
	            }
	            matrizeaSortu();
	            setVisible(true);    
	            new Timer(16, ev -> {jokoPanela.repaint();}).start();
	            nabegadorea.show(kontenedorea, "JOKOA");
	            this.requestFocusInWindow();
	            getController().hasi();
	        } else if (e == Egoera.IRABAZI || e==Egoera.GALDU) {
	        	bizitzaPanela.setVisible(false);
	        	int puntuak= JokoKudeatzailea.getNireJokoKudeatzailea().getPuntuazioTotala();
	        	if (e==Egoera.IRABAZI) {
	        		irabaziPantaila.eguneratuTestua(puntuak);
	        		nabegadorea.show(kontenedorea, "IRABAZI_PANTAILA");
	        	} else {
	        		galduPantaila.eguneratuTestua(puntuak);
	        		nabegadorea.show(kontenedorea, "GALDU_PANTAILA");
	        	}
	        	this.requestFocusInWindow();
	        	this.repaint();
	        }
	    } 
	    
	    else if (arg instanceof Integer) {
	        int geratzenDirenBizitzak = (Integer) arg;
	        for (int i = 0; i < 3; i++) {
	            bihotzEtiketak[i].setVisible(i < geratzenDirenBizitzak);
	        }
	        if (geratzenDirenBizitzak < 3 && geratzenDirenBizitzak > 0) {
	        	bizitzaMezuaLabel.setText("BIZITZA BAT GALDU DUZU!");
	        	Timer t = new Timer(3000, e -> bizitzaMezuaLabel.setText(""));
	            t.setRepeats(false);
	            t.start();
	        }
	        for (int i = 0; i < 3; i++) {
	            if (i >= geratzenDirenBizitzak) {
	                bihotzEtiketak[i].setVisible(false); // Bihotza desagertu
	            }
	        }	        
	    } 
	    else if (arg instanceof String mezuaString) {
	        mezuaErakutsi(mezuaString);
	    }
	}

	private JPanel getPanel() {
		if (jokoPanela == null) {
			jokoPanela = new JPanel();
			jokoPanela.setLayout(new GridLayout(60, 100, 0, 0));
		}
		return jokoPanela;
	}
	
	private void mezuaErakutsi(String m) {
	    mezua.setText(m);
	    mezua.setVisible(true);

	    SwingUtilities.invokeLater(() -> {
	        int zabalera = getWidth();
	        mezua.setBounds(0, 5, zabalera, 50);
	    });

	    mezuaTimer.restart();
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
	    	Egoera unekoEgoera = JokoKudeatzailea.getNireJokoKudeatzailea().getEgoera();

	        if (unekoEgoera == Egoera.IRABAZI || unekoEgoera == Egoera.GALDU) {
	            if (e.getKeyCode() == KeyEvent.VK_R) {
	                // Berriz jokoa hasten da baina puntuazioa gordetzen da
	                JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.JOKATZEN);
	                return;
	            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                System.exit(0); // amaitu
	            }
	        }
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_LEFT  -> ezkerra = true;
	            case KeyEvent.VK_RIGHT -> eskuma = true;
	            case KeyEvent.VK_UP    -> gora = true;
	            case KeyEvent.VK_DOWN  -> behera = true;
	            case KeyEvent.VK_SPACE -> tiro = true;
	            case KeyEvent.VK_1 -> MatrizeE.getEma().getEspaziontzia().portaeraAldatu(0);
	            case KeyEvent.VK_2 -> MatrizeE.getEma().getEspaziontzia().portaeraAldatu(1);
	            case KeyEvent.VK_3 -> MatrizeE.getEma().getEspaziontzia().portaeraAldatu(2);
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