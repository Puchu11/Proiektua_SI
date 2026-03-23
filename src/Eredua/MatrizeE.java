package Eredua;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MatrizeE extends Observable {

    private static MatrizeE ema;

    private static int zabalera = 100;  
    private static int altuera = 60;   

    private GelaxkaE matrizea[][] = new GelaxkaE[altuera][zabalera];
    private Espaziontzia espaziontzia;
    private ArrayList<Etsaia> etsaiak = new ArrayList<Etsaia>();
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
					matrizea[i][j] = new GelaxkaE(j, i, EntitateMota.HUTSA);
				
            }   
    	}
       	espaziontzia = new Espaziontzia(50, 55);
    	matrizea[espaziontzia.getPosizioa().getY()][espaziontzia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ESPAZIONTZIA);
    	etsaiakSortu();
    	hasieratuEtsaienMugimendua();
    }
    public void mugituEspaziontzia(String norabidea) {
        
    	if(mugituDaiteke(norabidea, espaziontzia)) {
    		int zaharX= espaziontzia.getPosizioa().getX();
    		int zaharY = espaziontzia.getPosizioa().getY();
    		gelaxkaEguneratu(zaharX, zaharY, EntitateMota.HUTSA);
    		if(norabidea.equals("gora")) {
    			espaziontzia.mugituGora();
    		}else {
    			espaziontzia.mugitu(norabidea);
    		}
    		int berriaX = espaziontzia.getPosizioa().getX();
    		int berriaY = espaziontzia.getPosizioa().getY();
    		kolizioa(berriaX, berriaY);
    		gelaxkaEguneratu(berriaX,berriaY, EntitateMota.ESPAZIONTZIA);
    	}
    }

    public Espaziontzia getEspaziontzia() {
        return espaziontzia;
    }
    
    public GelaxkaE getGelaxka(int x, int y) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
			return matrizea[y][x];
		}
		return null;
	}
    
    public void gelaxkaEguneratu(int x, int y, EntitateMota mota) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
			matrizea[y][x].gelaxkaEguneratu(mota);
		}
	}
    private Boolean mugituDaiteke(String norabidea, Entitatea entitatea) {
    	boolean Mugitu = entitatea.mugituDaiteke(norabidea);
    	return Mugitu;
    }
    	
    public void tiroEgin() {espaziontzia.tiroEgin();  }
    
    private void etsaiakSortu() {
    	for (int i=0; i<6+rnd.nextInt(4); i++) {
			int x = rnd.nextInt(zabalera);
			boolean okupatuta = false;
			for (Etsaia e : etsaiak) {
	            if (e.getPosizioa().getX() == x) {
	                okupatuta = true;
	                break;
	            }
	        }
	        if (!okupatuta) {
	            Etsaia etsaia = new Etsaia(x, 5, i);
	            etsaiak.add(etsaia);
	            matrizea[etsaia.getPosizioa().getY()][etsaia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ETSAIA);
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
    
    public boolean talkaEginDu(int x, int y) {
    	if(matrizea[y][x].getEntitateMota().equals("etsaia")) {
    		Etsaia hilda=null;
    		for(Etsaia e: etsaiak) {
    			if(e.getPosizioa().getX()== x && e.getPosizioa().getY()==y) {
    				hilda=e;
    				break;
    			}
    		}
    		if(hilda!= null) {
    			etsaiak.remove(hilda);
    		}
    		gelaxkaEguneratu(x,y,EntitateMota.HUTSA);
    		if (etsaiak.isEmpty()) {
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
    		}
    		return true;
    	}
    	return false;
    }
    
    private void jokoaAmaituDa(int x, int y) {
    	for(Etsaia e: etsaiak) {
    		if (e.getPosizioa().getX()==x && e.getPosizioa().getY()==y) {
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
    			break;
    		}
    		if(e.getPosizioa().getY()>=altuera-1) {
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
    		}
    	}
    }
    
    private void etsaiakMugitu() {
    	for (Etsaia e: etsaiak) {
    		int x=e.getPosizioa().getX();
    		int y=e.getPosizioa().getY();
    		int aukera= rnd.nextInt(3);

		
    		if (aukera==0 && e.mugituDaiteke("ezkerrera")) {
    			e.mugitu("ezkerrera");
    		}
    		else if (aukera==1 && e.mugituDaiteke("eskuinera")) {
    			e.mugitu("eskuinera");
    		}
    		else if (aukera==2 && e.mugituDaiteke("behera")) {
    			e.mugitu("behera");
    		}
    		
    		if(getGelaxka(e.getPosizioa().getX(),e.getPosizioa().getY()).getEntitateMota().equals("tiro")) {
				etsaiak.remove(e);
				e=null;
				matrizea[y][x].gelaxkaEguneratu(EntitateMota.HUTSA);
				if (etsaiak.isEmpty()) {
	    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
	    		}
				return;
    			
    		}
   
    		if (!matrizea[e.getPosizioa().getY()][e.getPosizioa().getX()].getEntitateMota().equals("etsaia")) {
    			matrizea[y][x].gelaxkaEguneratu(EntitateMota.HUTSA);
    			matrizea[e.getPosizioa().getY()][e.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ETSAIA);
    		}
    	}
    	jokoaAmaituDa(this.espaziontzia.getPosizioa().getX(),this.espaziontzia.getPosizioa().getY());
    }
    private void kolizioa(int x, int y) {
    	for(Etsaia e: etsaiak) {
    		if (e.getPosizioa().getX()==x && e.getPosizioa().getY()==y) {
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
    			break;
    		}
    	}
    }
    public void jokoaAmaitu() {
    	if (etsaienTimer != null) {
            etsaienTimer.cancel();
            etsaienTimer.purge();
        }  	
        System.out.println("Matrize amatatuta");
        
    }
}