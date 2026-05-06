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
    private boolean jokoaMartxan = true;
    private Random rnd = new Random();
    private java.util.Timer powerUpTimer;
    private java.util.List<PowerUpObjektua> erortzenDirenPowerUpak = new java.util.ArrayList<>();
    private MatrizeE() {}
    
    public static MatrizeE getEma() {
        if (ema == null) {
            ema = new MatrizeE();
        }
        return ema;
    }
    
    public void matrizeaSortu() {
    	jokoaMartxan = true;
    	espaziontzia.getLista().clear(); // partida berriz jokatzeko aurrekoa ezabatu
        etsaiak.clear();
    	for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
            	matrizea[i][j] = new GelaxkaE(j, i, new HutsaEgoera());
				
            }   
    	}
    	
    	String espaziontziMota = JokoKudeatzailea.getNireJokoKudeatzailea().getEspaziontziMota(); 
    	espaziontzia = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(espaziontziMota);
    	etsaiakSortu();  	
    	hasieratuPowerUpErorikoa();
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
        int kopurua = 8;
        int tartea = zabalera / (kopurua + 1);
        int id = 0;

        for (int i = 0; i < kopurua; i++) {
            int x = tartea * (i + 1);

            EntitateNodo e1 = EtsaiFactory.getEtsaiFactory()
                    .sortuEtsaia("ertaina", x, 5, id++);
            etsaiak.add(e1);
            marraztu(e1);
            hasiEtsaiThread(e1, "ertaina");

            String mota = (i % 2 == 0) ? "handia" : "txikia";
            EntitateNodo e2 = EtsaiFactory.getEtsaiFactory()
                    .sortuEtsaia(mota, x, 10, id++);
            etsaiak.add(e2);
            marraztu(e2);
            hasiEtsaiThread(e2, mota);

            EntitateNodo e3 = EtsaiFactory.getEtsaiFactory()
                    .sortuEtsaia("tiratzailea", x, 15, id++);
            etsaiak.add(e3);
            marraztu(e3);
            hasiEtsaiThread(e3, "tiratzailea");
        }
    }

    private void marraztu(EntitateNodo nodo) {
    	nodo.getLista().forEach(ent -> {
    		Etsaia e = (Etsaia) ent;

    		matrizea[e.getPosizioa().getY()][e.getPosizioa().getX()]
    				.gelaxkaEguneratu(new EtsaiaEgoera());
    	});
    }
    
    private void hasiEtsaiThread(EntitateNodo etsaia, String mota) {
        new Thread(() -> {
        	while (jokoaMartxan && etsaiak.contains(etsaia)) {
                synchronized (this) {
                    if (!etsaiak.contains(etsaia)) {
                        break;
                    }
                    String[] norabideak = {"ezkerrera", "behera", "eskuinera"};
                    String norabidea = norabideak[rnd.nextInt(3)];

                    mugituEtsaia(etsaia, norabidea);
                }
                try {
                    Thread.sleep(abiadura(mota));
                } catch (Exception e) {
                    break;
                }
            }

        }).start();
    }

    private void mugituEtsaia(EntitateNodo entNodo, String norabidea) {
        boolean talka = entNodo.getLista().stream()
                .map(ent -> (Etsaia) ent)
                .anyMatch(etsaia -> espaziontziaTalka(
                        norabidea,
                        etsaia.getPosizioa().getX(),
                        etsaia.getPosizioa().getY()
                ));

        if (talka) {
            JokoKudeatzailea.getNireJokoKudeatzailea().bizitzaBatKendu();

            if (!entNodo.getLista().isEmpty()) {
                Etsaia lehenengoa = (Etsaia) entNodo.getLista().get(0);
                etsaiakEzabatu(
                        lehenengoa.getPosizioa().getX(),
                        lehenengoa.getPosizioa().getY()
                );
            }

            return;
        }

        if (entNodo.mugituDaiteke(norabidea)) {
            entNodo.mugitu(norabidea);
        }

        boolean iritsiDaBehera = entNodo.getLista().stream()
                .map(ent -> (Etsaia) ent)
                .anyMatch(enemy -> enemy.getPosizioa().getY() >= 59);

        if (iritsiDaBehera) {
            JokoKudeatzailea.getNireJokoKudeatzailea().bizitzaBatKendu();

            if (!entNodo.getLista().isEmpty()) {
                Etsaia lehenengoa = (Etsaia) entNodo.getLista().get(0);
                etsaiakEzabatu(
                        lehenengoa.getPosizioa().getX(),
                        lehenengoa.getPosizioa().getY()
                );
            }
        }
        
        if(JokoKudeatzailea.getNireJokoKudeatzailea().getBizitzak()<=0) {
        	JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
        }
        
    }

    private int abiadura(String mota) {
        switch (mota) {
            case "txikia": return 100;
            case "ertaina": return 200;
            case "handia": return 500;
            case "tiratzailea": return 700;
            default: return 300;
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
    	    JokoKudeatzailea.getNireJokoKudeatzailea().puntuazioaGehitu(1);
    	    
    	    //PowerUp berria sortu eta gehitu
    	    if (rnd.nextDouble()<0.3) {
    	    	java.util.List<PowerUp> Lista= PowerUpFactory.lortuPowerUpGuztiak();
        	    	int indizea= rnd.nextInt(Lista.size());
    	    		PowerUp aukeratua = Lista.get(rnd.nextInt(Lista.size()));
        	    
        	    	String izena = "";
	        	    	switch (indizea) {
	        	    	case 0: izena = "Gezi Tiroa"; break;
	        	    	case 1: izena = "Erronbo Tiroa"; break;
	        	    	case 2: izena = "Bizitza extra"; break;
	        	    	case 3: izena = "Gezi Munizioa(+20)"; break;
	        	    	case 4: izena = "Erronbo Munizioa (+10)"; break;
	        	    	default: izena = "Power-up";
        	    	}
	        	erortzenDirenPowerUpak.add(new PowerUpObjektua(x, y,  izena, aukeratua));
        	    JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("PowerUp erortzen hari da!");	
    	    }
    	    
    	}
    	
    	if (etsaiak.isEmpty()) {
    		JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
    		
    	}   	
    }

    public void jokoaAmaitu() {
        jokoaMartxan = false;
        if (powerUpTimer != null) {
            powerUpTimer
            .cancel();
            powerUpTimer.purge();
        }

        System.out.println("Matrize amatatuta");
    }
    
    public ArrayList<EntitateNodo> getEtsaiak(){
    	return etsaiak;
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


    public void hasieratuPowerUpErorikoa() {
        powerUpTimer = new java.util.Timer();
        powerUpTimer.schedule(new java.util.TimerTask(){
            public void run() {
                new java.util.ArrayList<>(erortzenDirenPowerUpak).forEach(p -> {
                    p.jaitsi();
            
                    // Talka espaziontziarekin egiaztatu (erdian edo alboetan)
                    GelaxkaE g = getGelaxka(p.getX(), p.getY());
                    GelaxkaE gLeft = getGelaxka(p.getX() - 1, p.getY());
                    GelaxkaE gRight = getGelaxka(p.getX() + 1, p.getY());
                    
                    if ((g != null && g.getEntitateMota().equals("espaziontzia")) ||
                        (gLeft != null && gLeft.getEntitateMota().equals("espaziontzia")) ||
                        (gRight != null && gRight.getEntitateMota().equals("espaziontzia"))) {
                        
                        p.aplikatuEfektua(getEspaziontzia());
                        erortzenDirenPowerUpak.remove(p);
                        JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Power-up aplikatua: " + p.getIzena() + "!");
                        
                        // Nabearen egoera eta kolorea berrezarri (piezak ezabatu gabe)
                        gelaxkaEguneratu(p.getX(), p.getY(), new EspaziontziaEgoera());
                        return;
                    }
                    
                    // Muga baino gehiago jaisten bada, ezabatu
                    if (p.getY() >= 60) {
                    	GelaxkaE gOut = getGelaxka(p.getX(), p.getY() - 1);
                        if (gOut != null && !gOut.getEntitateMota().equals("espaziontzia")) {
                            gOut.gelaxkaEguneratu(new HutsaEgoera());
                        }
                        erortzenDirenPowerUpak.remove(p);
                    }
                });
            }
        }, 0, 300); //300ms-tik behin mugituko da
    }
}