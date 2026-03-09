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
    	hasieratuEtsaienMugimendua();
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
	    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
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
    public void hasieratuEtsaienMugimendua() {
    	new Thread(()->{
    		while(true) {
    			etsaiakMugitu();
    			try {
    				Thread.sleep(500);
    			}catch (InterruptedException ex) {
    				ex.printStackTrace();
    			}
    		}
    	}).start(); 
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
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
    		}
    		return true;
    	}
    	return false;
    }
    
    public synchronized void jokoaAmaituDa() {
    	for(Etsaia e: etsaiak) {
    		if(e.getPosizioa().getY()<=0) {
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
    		}
    	}
    }
    
    public synchronized void etsaiakMugitu() {
    	for (Etsaia e: etsaiak) {
    		int x=e.getPosizioa().getX();
    		int y=e.getPosizioa().getY();
    		int aukera= rnd.nextInt(3);
    		
    		int xBerria=x;
    		int yBerria=y;
    		
    		if (aukera==0 && e.mugituDaiteke("ezkerrera")) {
    			xBerria=x-1;
    		}
    		else if (aukera==1 && e.mugituDaiteke("eskuinera")) {
    			xBerria=x+1;
    		}
    		else if (aukera==2 && e.mugituDaiteke("behera")) {
    			yBerria=y+1;
    		}
    		
    		if (matrizea[yBerria][xBerria].getMota() == EntitateMota.HUTSA) {
    			matrizea[y][x].gelaxkaEguneratu(EntitateMota.HUTSA);
    			e.getPosizioa().setX(xBerria);
    			e.getPosizioa().setY(yBerria);
    			matrizea[yBerria][xBerria].gelaxkaEguneratu(EntitateMota.ETSAIA);
    		}

    	}
    	setChanged();
    	notifyObservers();
    }
    
    
}