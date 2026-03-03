package Eredua;

import java.util.ArrayList;
import java.util.Observable;

public class MatrizeKudeatzailea extends Observable {

    private static MatrizeKudeatzailea ema;

    private static int zabalera = 100;  
    private static int altuera = 60;   

    private Gelaxka matrizea[][] = new Gelaxka[altuera][zabalera];
    private Espaziontzia espaziontzia;
    private ArrayList<Etsaia> etsaiak = new ArrayList<Etsaia>();
    private ArrayList<Tiro> tiroak = new ArrayList<Tiro>();

    private MatrizeKudeatzailea() {

    	espaziontzia = new Espaziontzia(50, 55);
    	
    	for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
					matrizea[i][j] = new Gelaxka(j, i, EntitateMota.HUTSA);
				
            }   
    	}
    
    	matrizea[espaziontzia.getPosizioa().getY()][espaziontzia.getPosizioa().getX()].gelaxkaEguneratu(EntitateMota.ESPAZIONTZIA);
    	
    }
    public static MatrizeKudeatzailea getEma() {
        if (ema == null) {
            ema = new MatrizeKudeatzailea();
        }
        return ema;
    }

    public void mugitu(String norabidea) {
        
    }

    public Espaziontzia getEspaziontzia() {
        return espaziontzia;
    }
    
    public Gelaxka[][] getMatrizea() {
		return matrizea;
	}
    
    public Gelaxka getGelaxka(int x, int y) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
			return matrizea[y][x];
		}
		return null;
	}
}