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
    private EntitateNodo espaziontzia= new EntitateNodo();
    private Espaziontzia espaziontziaIm;
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
    }
    
    public Espaziontzia getEspaziontzia() {
    	return espaziontziaIm;
    }
    
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
	            matrizea[etsaia.getPosizioa().getY()][etsaia.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
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
    		gelaxkaEguneratu(x,y, new HutsaEgoera());
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
    		
    		matrizea[y][x].gelaxkaEguneratu(new HutsaEgoera());
			matrizea[e.getPosizioa().getY()][e.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
    		
			if(getGelaxka(e.getPosizioa().getX(),e.getPosizioa().getY()).getEntitateMota().equals("tiro")) {
				etsaiak.remove(e);
				e=null;
				matrizea[y][x].gelaxkaEguneratu(new HutsaEgoera());
				if (etsaiak.isEmpty()) {
	    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
	    		}
				return;
    			
    		}
    	}
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
    
    public ArrayList<Etsaia> getEtsaiak(){
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