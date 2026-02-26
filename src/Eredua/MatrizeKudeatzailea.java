package Eredua;

import java.util.ArrayList;
import java.util.Observable;

public class MatrizeKudeatzailea extends Observable {

    private static MatrizeKudeatzailea ema;

    private static int zabalera = 100;  // columnas (X)
    private static int altuera = 60;    // filas (Y)

    private Entitatea matrizea[][] = new Entitatea[altuera][zabalera];
    private Espaziontzia espaziontzia;
    private ArrayList<Etsaia> etsaiak = new ArrayList<Etsaia>();
    private ArrayList<Tiro> tiroak = new ArrayList<Tiro>();

    private MatrizeKudeatzailea() {
        // Inicializar matriz
        for (int i = 0; i < altuera; i++) {
            for (int j = 0; j < zabalera; j++) {
                matrizea[i][j] = null;
            }
        }

        // Crear nave inicial en (50,55)
        espaziontzia = new Espaziontzia(50, 55);
        matrizea[55][50] = espaziontzia;
        
    }

    public static MatrizeKudeatzailea getEma() {
        if (ema == null) {
            ema = new MatrizeKudeatzailea();
        }
        return ema;
    }

    // 🔥 MÉTODO PARA MOVER LA NAVE
    public void Mugitu(int dx, int dy) {

        int x = espaziontzia.getPosizioa().getX();
        int y = espaziontzia.getPosizioa().getY();

        int nx = x + dx;
        int ny = y + dy;

        // Comprobar límites
        if (nx < 0 || nx >= zabalera) return;
        if (ny < 0 || ny >= altuera) return;

        // Actualizar posición
        espaziontzia.setPosizioa(new Posizioa(nx, ny));

        // Notificar a la vista
        setChanged();
        notifyObservers(espaziontzia);
    }

    public Espaziontzia getEspaziontzia() {
        return espaziontzia;
    }
}