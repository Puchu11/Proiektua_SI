package Eredua;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MatrizeE extends Observable {

    private static MatrizeE ema;

    private static int zabalera = 100;  
    private static int altuera = 60;   

    private GelaxkaE matrizea[][] = new GelaxkaE[altuera][zabalera];
    private EntitateNodo espaziontzia= new EntitateNodo();
    private ArrayList<EntitateNodo> etsaiak = new ArrayList<EntitateNodo>();
    private java.util.Timer etsaienTimer;
    private Random rnd = new Random();

    private MatrizeE() {}
    
    public static MatrizeE getEma() {
        if (ema == null) {
            ema = new MatrizeE();
        }
        return ema;
    }
    
    public void matrizeaSortu() {
    	for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
            	matrizea[i][j] = new GelaxkaE(j, i, new HutsaEgoera());
				
            }   
    	}
    	
    	String espaziontziMota= JokoKudeatzailea.getNireJokoKudeatzailea().getEspaziontziMota(); 
    	espaziontziaSortu(espaziontziMota);
    	etsaiakSortu();
    	hasieratuEtsaienMugimendua();
    }
    public void mugituEspaziontzia(String norabidea) {
    	
    	espaziontzia.mugitu(norabidea);
    	
    }
    
    public GelaxkaE[][] getMatrizea(){
    	return matrizea;
    }
    
    public GelaxkaE getGelaxka(int x, int y) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
			return matrizea[y][x];
		}
		return null;
	}
    
    public void gelaxkaEguneratu(int x, int y, GelaxkaEgoera mota) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
			matrizea[y][x].gelaxkaEguneratu(mota);
		}
	}
    
    public void tiroEgin() {
    	Espaziontzia erdikoa = (Espaziontzia) espaziontzia.getLista().get(0);
    	erdikoa.tiroEgin();
    	AudioKudeatzailea.getAudioKudeatzailea().soinuaErreproduzitu("src/res/laser.wav");
    }
    
    public Espaziontzia getEspaziontzia() {
    	Espaziontzia erdikoa = (Espaziontzia) espaziontzia.getLista().get(0);
    	return erdikoa;
    }
    
    private void etsaiakSortu() {
        int kopurua = 6 + rnd.nextInt(4);   
        int sortuta 	= 0;                    
        int saiakerak = 0;                 
        int maxSaiakerak = 500;

        while (sortuta < kopurua && saiakerak < maxSaiakerak) {
            saiakerak++;
            int x = rnd.nextInt(zabalera);
            boolean okupatuta = false;

            for (int j = -2; j < 3; j++) {
                if (x + j < 0 || x + j >= zabalera || getGelaxka(x + j, 5).getEntitateMota().equals("etsaia")) {
                    okupatuta = true;
                    break;
                }
            }

            if (!okupatuta) {
            	int[][] koordenatuak = {
            		    {0, 5},{-2, 3},{-2, 5},{2, 3},{2, 5},
            		    {0, 4},{-1, 4},{1, 4},
            		    {-1, 6},{1, 6},{-2, 6},{2, 6},
            		    {1, 7},{0, 7},{-1, 7},
            		    {-2, 4},{2, 4}
            		};

            		EntitateNodo etsaiNodo = new EntitateNodo();
            		etsaiak.add(etsaiNodo);

            		for (int[] pos : koordenatuak) {
            		    int posX = x + pos[0];
            		    int posY = pos[1];

            		    Etsaia etsaia = new Etsaia(posX, posY, sortuta);

            		    matrizea[posY][posX]
            		            .gelaxkaEguneratu(new EtsaiaEgoera());

            		    etsaiNodo.gehituEntitate(etsaia);
            		}
            		sortuta++;
            }
        }
    }
    
    private void hasieratuEtsaienMugimendua() {
    	etsaienTimer = new Timer();
        TimerTask ataza = new TimerTask() {
            public void run() {
                etsaiakMugitu();
            }
        };

        etsaienTimer.schedule(ataza, 0, 200);
    }
    private void etsaiakMugitu() {
        int kopurua = etsaiak.size();
        for (int i = 0; i < etsaiak.size(); i++) {
            if (i >= etsaiak.size()) break;

            EntitateNodo e = etsaiak.get(i);
            int aukera = rnd.nextInt(3);
            String norabideHautatuta = (aukera == 0) ? "ezkerrera" : (aukera == 1) ? "eskuinera" : "behera";

            final String norabideaFinal = norabideHautatuta;

            // Stream bidezko talka detekzioa (main branch-eko logika)
            boolean talka = e.getLista().stream()
                    .map(ent -> (Etsaia) ent)
                    .anyMatch(enemy -> espaziontziaTalka(
                            norabideaFinal,
                            enemy.getPosizioa().getX(),
                            enemy.getPosizioa().getY()
                    ));

            if (talka) {
                System.out.println("!!GALDU DOZU:TALKA");
                jokoaAmaitu();
                AudioKudeatzailea.getAudioKudeatzailea().musikaGelditu();
                AudioKudeatzailea.getAudioKudeatzailea().soinuaErreproduzitu("src/res/mario_death.wav");
                JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
                return;
            }

            if (e.mugituDaiteke(norabideaFinal)) {
                e.mugitu(norabideaFinal);
            }
        }

        // Behera iristearen logika
        boolean behera = etsaiak.stream()
                .flatMap(nodo -> nodo.getLista().stream())
                .map(ent -> (Etsaia) ent)
                .anyMatch(e -> e.getPosizioa().getY() >= 59);

        if (behera) {
            System.out.println("!!! KONSOLEAN: Etsaia behera iritsi da !!!");
            jokoaAmaitu();
            JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
            return;
        }
    }
   
    	

    public void etsaiakEzabatu(int x, int y) {	
    	EntitateNodo nodoEzabatu = etsaiak.stream().filter(nodo -> nodo.getLista().stream()
    	                .map(ent -> (Etsaia) ent)
    	                .anyMatch(e ->
    	                        e.getPosizioa().getX() == x &&
    	                        e.getPosizioa().getY() == y
    	                ))
    	        .findFirst()
    	        .orElse(null);
    	
    	if (nodoEzabatu != null) {
    	    nodoEzabatu.getLista().stream()
    	            .map(ent -> (Etsaia) ent)
    	            .forEach(e ->
    	                    matrizea[e.getPosizioa().getY()][e.getPosizioa().getX()]
    	                            .gelaxkaEguneratu(new HutsaEgoera())
    	            );

    	    etsaiak.remove(nodoEzabatu);
    	}
    	
    	if (etsaiak.isEmpty()) {
    		JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
    		
    	}
    	
    }

    


    public void jokoaAmaitu() {
    	if (etsaienTimer != null) {
            etsaienTimer.cancel();
            etsaienTimer.purge();
        }  	
        System.out.println("Matrize amatatuta");
        
    }
    
    public ArrayList<EntitateNodo> getEtsaiak(){
    	return etsaiak;
    }
    
    private void espaziontziaSortu(String mota) {

        int[][] koordenatuak = {
            {50, 55},{51, 55},{49, 55},
            {49, 54},{51, 54},
            {50, 56},{50, 54},{50, 53},{50, 52},
            {51, 53},{49, 53},
            {52, 55},{52, 56},{52, 57},
            {48, 55},{48, 56},{48, 57}
        };

        for (int[] pos : koordenatuak) {
            Espaziontzia pixel = EspaziontziaFactory.getEspaziontziaFactory()
                    .sortuEspaziontzia(mota, pos[0], pos[1]);

            espaziontzia.gehituEntitate(pixel);

            matrizea[pos[1]][pos[0]]
                    .gelaxkaEguneratu(new EspaziontziaEgoera());
        }
    }
    
    private boolean espaziontziaTalka(String norabidea,int x, int y) {
        if(espaziontziaDago(norabidea,x,y)) {
        	return true;
        }
        return false;
    }
    
    
    private boolean espaziontziaDago(String norabidea,int x, int y) {
    	
    	if(norabidea.equals("ezkerrera")) {
    		x--;
    	}else if (norabidea.equals("eskuinera")){
    		x++;
    	}else {
    		y++;
    	}
    	
    	GelaxkaE g = getGelaxka(x, y);
        if (g == null) return false;

        return g.getEntitateMota().equals("espaziontzia");
    }
    
}