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
    private Espaziontzia espaziontziaIm;
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
    }
    
    public Espaziontzia getEspaziontzia() {
    	return espaziontziaIm;
    }
    
    private void etsaiakSortu() {
        int kopurua = 6 + rnd.nextInt(4);   
        int sortuta = 0;                    
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
                Etsaia pE1 = new Etsaia(x, 5, sortuta);
                matrizea[pE1.getPosizioa().getY()][pE1.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE2 = new Etsaia(x - 1, 5, sortuta);
                matrizea[pE2.getPosizioa().getY()][pE2.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE3 = new Etsaia(x - 2, 5, sortuta);
                matrizea[pE3.getPosizioa().getY()][pE3.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE4 = new Etsaia(x + 1, 5, sortuta);
                matrizea[pE4.getPosizioa().getY()][pE4.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE5 = new Etsaia(x + 2, 5, sortuta);
                matrizea[pE5.getPosizioa().getY()][pE5.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE6 = new Etsaia(x, 4, sortuta);
                matrizea[pE6.getPosizioa().getY()][pE6.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE7 = new Etsaia(x - 1, 4, sortuta);
                matrizea[pE7.getPosizioa().getY()][pE7.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE8 = new Etsaia(x + 1, 4, sortuta);
                matrizea[pE8.getPosizioa().getY()][pE8.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE9 = new Etsaia(x - 1, 6, sortuta);
                matrizea[pE9.getPosizioa().getY()][pE9.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE10 = new Etsaia(x + 1, 6, sortuta);
                matrizea[pE10.getPosizioa().getY()][pE10.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                EntitateNodo etsaiNodo = new EntitateNodo();
                etsaiak.add(etsaiNodo);

                etsaiNodo.gehituEntitate(pE1);
                etsaiNodo.gehituEntitate(pE2);
                etsaiNodo.gehituEntitate(pE3);
                etsaiNodo.gehituEntitate(pE4);
                etsaiNodo.gehituEntitate(pE5);
                etsaiNodo.gehituEntitate(pE6);
                etsaiNodo.gehituEntitate(pE7);
                etsaiNodo.gehituEntitate(pE8);
                etsaiNodo.gehituEntitate(pE9);
                etsaiNodo.gehituEntitate(pE10);

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
    
    public boolean talkaEginDu(int x, int y) {
    	if(matrizea[y][x].getEntitateMota().equals("etsaia")) {
    		Etsaia hilda=null;
    		for(Etsaia e: etsaiNodo) {
    			if(e.getPosizioa().getX()== x && e.getPosizioa().getY()==y) {
    				hilda=e;
    				break;
    			}
    		}
    		if(hilda!= null) {
    			etsaiNodo.remove(hilda);
    		}
    		gelaxkaEguneratu(x,y, new HutsaEgoera());
    		if (etsaiNodo.isEmpty()) {
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
    		}
    		return true;
    	}
    	return false;
    }
    
    private void jokoaAmaituDa(int x, int y) {
    	for(Etsaia e: etsaiNodo) {
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
    	Iterator<Entitatea> itr = etsaiNodo.getIterator();
    	while(itr.hasNext()) {
    		Etsaia e = (Etsaia) itr.next();

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
    	}
    	
    }
    private void kolizioa(int x, int y) {
    	for(Etsaia e: etsaiNodo) {
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
    
    public ArrayList<EntitateNodo> getEtsaiak(){
    	return etsaiak;
    }
    
    private void espaziontziaSortu(String mota) {
    	espaziontziaIm = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 55);    	
    	
    	Espaziontzia pixel1 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 55);
        matrizea[pixel1.getPosizioa().getY()][pixel1.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel2 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 51, 55);
        matrizea[pixel2.getPosizioa().getY()][pixel2.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel3 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 49, 55);
        matrizea[pixel3.getPosizioa().getY()][pixel3.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel4 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 49, 54);
        matrizea[pixel4.getPosizioa().getY()][pixel4.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel5 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 51, 54);
        matrizea[pixel5.getPosizioa().getY()][pixel5.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
    	
        espaziontzia.gehituEntitate(pixel1);
        espaziontzia.gehituEntitate(pixel2);
        espaziontzia.gehituEntitate(pixel3);
        espaziontzia.gehituEntitate(pixel4);
        espaziontzia.gehituEntitate(pixel5);
        
    }
}