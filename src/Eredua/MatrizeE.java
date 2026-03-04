package Eredua;

import java.util.ArrayList;
import java.util.Observable;

public class MatrizeE extends Observable {

    private static MatrizeE ema;

    private static int zabalera = 100;  
    private static int altuera = 60;   

    private GelaxkaE matrizea[][] = new GelaxkaE[altuera][zabalera];
    private Espaziontzia espaziontzia;
    private ArrayList<Etsaia> etsaiak = new ArrayList<Etsaia>();
    

    private MatrizeE() {

    	espaziontzia = new Espaziontzia(50, 55);
    	
    	for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
					matrizea[i][j] = new GelaxkaE(j, i, EntitateMota.HUTSA);
				
            }   
    	}
    
    	matrizea[espaziontzia.getPosizioa().getY()][espaziontzia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ESPAZIONTZIA);
    	
    }
    public static MatrizeE getEma() {
        if (ema == null) {
            ema = new MatrizeE();
        }
        return ema;
    }

    public void mugituEspaziontzia(String norabidea) {
        
    	if(mugituDaiteke(norabidea, espaziontzia)) {
    		matrizea[espaziontzia.getPosizioa().getY()][espaziontzia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.HUTSA);
    		if (norabidea.equals("gora")) {
    			espaziontzia.mugituGora();
    		} else if (norabidea.equals("behera")) {
    			espaziontzia.mugituBehera();
    		} else if (norabidea.equals("ezkerrera")) {
    			espaziontzia.mugituEzkerra();
    		} else if (norabidea.equals("eskuinera")) {
    			espaziontzia.mugituEskuina();
    		}	
    		matrizea[espaziontzia.getPosizioa().getY()][espaziontzia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ESPAZIONTZIA);
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
    	
    public synchronized void tiroEgin() {
        Tiro tiroa = new Tiro(
            espaziontzia.getPosizioa().getX(),
            espaziontzia.getPosizioa().getY() - 1
        );
        
        tiroa.start();

    }
    
}