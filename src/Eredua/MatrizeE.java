package Eredua;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class MatrizeE extends Observable {

    private static MatrizeE ema;

    private static int zabalera = 100;  
    private static int altuera = 60;   

    private GelaxkaE matrizea[][] = new GelaxkaE[altuera][zabalera];
    private Espaziontzia espaziontzia;
    private ArrayList<Etsaia> etsaiak = new ArrayList<Etsaia>();
    
    private Random rnd = new Random();

    private MatrizeE() {
    	
    	for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
					matrizea[i][j] = new GelaxkaE(j, i, EntitateMota.HUTSA);
				
            }   
    	}
       	espaziontzia = new Espaziontzia(50, 55);
    	matrizea[espaziontzia.getPosizioa().getY()][espaziontzia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ESPAZIONTZIA);
    	etsaiakSortu();
    }
    public static MatrizeE getEma() {
        if (ema == null) {
            ema = new MatrizeE();
        }
        return ema;
    }

    public void mugituEspaziontzia(String norabidea) {
        
    	if(mugituDaiteke(norabidea, espaziontzia)) {
    		int zaharX= espaziontzia.getPosizioa().getX();
    		int zaharY = espaziontzia.getPosizioa().getY();
    		gelaxkaEguneratu(zaharX, zaharY, EntitateMota.HUTSA);
    		if (norabidea.equals("gora")) {
    			espaziontzia.mugituGora();
    		} else if (norabidea.equals("behera")) {
    			espaziontzia.mugituBehera();
    		} else if (norabidea.equals("ezkerrera")) {
    			espaziontzia.mugituEzkerra();
    		} else if (norabidea.equals("eskuinera")) {
    			espaziontzia.mugituEskuina();
    		}	
    		int berriaX = espaziontzia.getPosizioa().getX();
    		int berriaY = espaziontzia.getPosizioa().getY();
    		for(Etsaia e : etsaiak) {
	    		if (e.getPosizioa().getX()==berriaX && e.getPosizioa().getY()==berriaY) {
	    			System.out.println("!!!DETECTADO");
	    			this.setChanged();
	    			this.notifyObservers("GALDU");
	    			break;
	    		}
    		}
    		gelaxkaEguneratu(berriaX,berriaY, EntitateMota.ESPAZIONTZIA);
    	}
    }

    public Espaziontzia getEspaziontzia() {
        return espaziontzia;
    }
    
    public GelaxkaE[][] getMatrizea() {
		return matrizea;
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
    	
    public synchronized void tiroEgin() {espaziontzia.tiroEgin();  }
    
    private void etsaiakSortu() {
    	for (int i=0; i<4+rnd.nextInt(4); i++) {
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
    public synchronized boolean talkaEginDu(int x, int y) {
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
    			setChanged();
    			notifyObservers("IRABAZI");
    		}
    		return true;
    	}
    	return false;
    }
    public synchronized void jokoaAmaituDa() {
    	for(Etsaia e: etsaiak) {
    		if(e.getPosizioa().getY()>= espaziontzia.getPosizioa().getY()) {
    			this.setChanged();
    			this.notifyObservers("GALDU");
    			break;
    		}
    	}
    }
    
}