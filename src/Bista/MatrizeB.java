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
import Eredua.AudioKudeatzailea;
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
	private JLabel balakLabel;
	private JLabel puntuakLabel;
	private JLabel recordLabel;
	private Timer repaintTimer;

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
		
		bizitzaPanela = new JPanel(new BorderLayout());
		bizitzaPanela.setBackground(Color.BLACK);

		// PANEL BALAK
		JPanel balakPanela = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		balakPanela.setBackground(Color.BLACK);
		balakPanela.setPreferredSize(new java.awt.Dimension(500, 40));
		
		balakLabel = new JLabel("BALAK: --");
		balakLabel.setForeground(Color.CYAN);
		balakLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		balakPanela.add(balakLabel);

		// PANEL PUNTUAK
		JPanel puntuakPanela = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 5));
		puntuakPanela.setBackground(Color.BLACK);

		puntuakLabel = new JLabel("PUNTUAK: 0");
		puntuakLabel.setForeground(Color.YELLOW);
		puntuakLabel.setFont(new Font("Arial", Font.BOLD, 18));
		puntuakPanela.add(puntuakLabel);
		//PANEL RECORD
		recordLabel = new JLabel("RECORD: 0");
		recordLabel.setForeground(Color.ORANGE);
		recordLabel.setFont(new Font("Arial", Font.BOLD, 18));
		puntuakPanela.add(recordLabel);
		// PANEL BIZITZAK
		JPanel bizitzakPanela = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
		bizitzakPanela.setBackground(Color.BLACK);

		bihotzEtiketak = new JLabel[3];
		for (int i = 0; i < 3; i++) {
		    bihotzEtiketak[i] = new JLabel("♥");
		    bihotzEtiketak[i].setForeground(Color.RED);
		    bihotzEtiketak[i].setFont(new Font("Arial", Font.BOLD, 25));
		    bizitzakPanela.add(bihotzEtiketak[i]);
		}

		bizitzaPanela.add(balakPanela, BorderLayout.WEST);
		bizitzaPanela.add(puntuakPanela, BorderLayout.CENTER);
		bizitzaPanela.add(bizitzakPanela, BorderLayout.EAST);

		this.add(bizitzaPanela, BorderLayout.NORTH);
		repaintTimer = new Timer(16, ev -> jokoPanela.repaint());
	}
	
	private void matrizeaSortu() {
		jokoPanela.removeAll();
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 100; j++) {
				GelaxkaB gelaxkaB = new GelaxkaB();
				MatrizeE.getEma().getGelaxka(j, i).addObserver(gelaxkaB);
				labelN[i][j] = gelaxkaB;
				jokoPanela.add(gelaxkaB);	
				String kolorea = MatrizeE.getEma().getGelaxka(j, i).getEgoera().lortuKolorea();
				Color color = koloreaLortu(kolorea);
				gelaxkaB.setBackground(color);
			}
		}
		jokoPanela.revalidate();
	    jokoPanela.repaint();
	}
	
	private Color koloreaLortu(String kolorea) {

		if (kolorea == null) return Color.BLACK;

		switch (kolorea.toLowerCase()) {
			case "berdea":
				return Color.GREEN;

			case "urdina":
				return Color.BLUE;

			case "gorria":
				return Color.RED;

			case "horia":
				return Color.YELLOW;

			case "zuria":
				return Color.WHITE;

			case "beltza":
				return Color.BLACK;

			default:
				return Color.BLACK;
		}
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
	            if (!repaintTimer.isRunning()) {
	                repaintTimer.start();
	            }
	            nabegadorea.show(kontenedorea, "JOKOA");
	            this.requestFocusInWindow();
	            getController().hasi();
	        } else if (e == Egoera.IRABAZI || e == Egoera.GALDU) {

	            bizitzaPanela.setVisible(false);

	            if (repaintTimer != null && repaintTimer.isRunning()) {
	                repaintTimer.stop();
	            }
	            mezua.setText("");
	            balakLabel.setText("");            
	            int puntuak = JokoKudeatzailea
	                    .getNireJokoKudeatzailea()
	                    .getPuntuazioTotala();
	            if (e == Egoera.IRABAZI) {
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
	    
	    else if (arg instanceof Integer geratzenDirenBizitzak) {
	        for (int i = 0; i < 3; i++) {
	            bihotzEtiketak[i].setVisible(i < geratzenDirenBizitzak);
	        }
	    } 

	    else if (arg instanceof String mezuaString) {

	        if (mezuaString.startsWith("BALAK:")) {
	            balakLabel.setText(mezuaString.replace("BALAK_INFO:", ""));
	        } 
	        
	        else if (mezuaString.startsWith("PUNTUAK:")) {
	            puntuakLabel.setText(mezuaString);
	        }
	        else if (mezuaString.startsWith("RECORD:")) {
	            recordLabel.setText(mezuaString);
	        }
	        else {
	            mezuaErakutsi(mezuaString);
	        }
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
	    	if (JokoKudeatzailea.getNireJokoKudeatzailea().getEgoera() == Egoera.PAUSA) {
	            return;
	    	}
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
	            // --- JOKOAREN KONTROL OROKORRAK ---
	            case KeyEvent.VK_R      -> JokoKudeatzailea.getNireJokoKudeatzailea().berriroJolastu("jolastu");
	            case KeyEvent.VK_ESCAPE -> JokoKudeatzailea.getNireJokoKudeatzailea().berriroJolastu("amatatu");
	            case KeyEvent.VK_P      -> JokoKudeatzailea.getNireJokoKudeatzailea().pausaAldatu(); // PAUSA BERRIA

	            // --- MUGIMENDUA ETA TIROA ---
	            case KeyEvent.VK_SPACE  -> tiro = true;
	            case KeyEvent.VK_LEFT   -> ezkerra = true;
	            case KeyEvent.VK_RIGHT  -> eskuma = true;
	            case KeyEvent.VK_UP     -> gora = true;
	            case KeyEvent.VK_DOWN   -> behera = true;

	            // --- AUDIO KONTROLAK ---
	            case KeyEvent.VK_M -> {
	                AudioKudeatzailea.getAudioKudeatzailea().musikaMuteatu();
	                JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Musika mututu/aktibatu da");
	            }
	            case KeyEvent.VK_PLUS, KeyEvent.VK_ADD -> {
	                AudioKudeatzailea.getAudioKudeatzailea().bolumenaJaitsi(-3.0f);
	                JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Bolumena igota");
	            }
	            case KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT -> {
	                AudioKudeatzailea.getAudioKudeatzailea().bolumenaJaitsi(3.0f);
	                JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Bolumena jaitsita");
	            }

	            // --- ARMA ALDAKETA ---
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