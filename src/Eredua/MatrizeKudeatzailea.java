package Eredua;

import java.util.ArrayList;
import java.util.Observable;

public class MatrizeKudeatzailea extends Observable{

    private static MatrizeKudeatzailea ema;  

    private static int zabalera = 60;
    private static int altuera = 100;
    private Entitatea matrizea[][] = new Entitatea[altuera][zabalera];
    private ArrayList<Entitatea> entitateak = new ArrayList<Entitatea>();

    private MatrizeKudeatzailea() {
        for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
                matrizea[i][j] = null;
            }
        }
    }

    public static MatrizeKudeatzailea getEma() {
        if (ema == null) {
            ema = new MatrizeKudeatzailea();
        }
        return ema;
    }

    public void MatrizeaSortu() {
    	Espaziontzia espaziontzia = new Espaziontzia(3,0);
    }
}
