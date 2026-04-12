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
    	Espaziontzia erdikoa = (Espaziontzia) espaziontzia.getLista().get(0);
    	return erdikoa;
    }
    
    private void etsaiakSortu() {
        int kopurua = 6 + rnd.nextInt(4);   
        int sortuta 	= 0;                    
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

                Etsaia pE2 = new Etsaia(x - 2, 3, sortuta);
                matrizea[pE2.getPosizioa().getY()][pE2.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE3 = new Etsaia(x - 2, 5, sortuta);
                matrizea[pE3.getPosizioa().getY()][pE3.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());

                Etsaia pE4 = new Etsaia(x + 2, 3, sortuta);
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
                
                Etsaia pE11= new Etsaia(x-2, 6, sortuta);
                matrizea[pE11.getPosizioa().getY()][pE11.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
                Etsaia pE12= new Etsaia(x+2, 6, sortuta);
                matrizea[pE12.getPosizioa().getY()][pE12.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
                Etsaia pE13= new Etsaia(x+1, 7, sortuta);
                matrizea[pE13.getPosizioa().getY()][pE13.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
                Etsaia pE14= new Etsaia(x, 7, sortuta);
                matrizea[pE14.getPosizioa().getY()][pE14.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
                Etsaia pE15= new Etsaia(x-1, 7, sortuta);
                matrizea[pE15.getPosizioa().getY()][pE15.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
                Etsaia pE16= new Etsaia(x-2, 4, sortuta);
                matrizea[pE16.getPosizioa().getY()][pE16.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
                Etsaia pE17= new Etsaia(x+2, 4, sortuta);
                matrizea[pE17.getPosizioa().getY()][pE17.getPosizioa().getX()].gelaxkaEguneratu(new EtsaiaEgoera());
                
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
                etsaiNodo.gehituEntitate(pE11);
                etsaiNodo.gehituEntitate(pE12);
                etsaiNodo.gehituEntitate(pE13);
                etsaiNodo.gehituEntitate(pE14);
                etsaiNodo.gehituEntitate(pE15);
                etsaiNodo.gehituEntitate(pE16);
                etsaiNodo.gehituEntitate(pE17);
                
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
    private void etsaiakMugitu() {
    	//for-each arazoak ematen ebazan eta erroreak ematen ebazan kolizioa egiterakoan, izan be lista zeharkatzen dogunean eta aldi berean ezabatzen arazoak ematen ebazan
    	int kopurua= etsaiak.size();
    	for(int i= 0; i < etsaiak.size(); i++) {
    		
    		if(i>=etsaiak.size()) break;
    		
    		EntitateNodo e = etsaiak.get(i);
    		
    		int aukera=rnd.nextInt(3);
    		String norabideHautatuta = "";
    		
    		if (aukera == 0) {
    			norabideHautatuta = "ezkerrera";
    		}
    		else if (aukera == 1) {
    			norabideHautatuta = "eskuinera";
    		}
    		else {
    			norabideHautatuta = "behera"; 
    		}
    		
    		if (e.mugituDaiteke(norabideHautatuta)) {
    			e.mugitu(norabideHautatuta);
    		}
    	}	
    		for (int i = 0; i < etsaiak.size(); i++) {
    			EntitateNodo entNodo = etsaiak.get(i);
    			if (!entNodo.getLista().isEmpty()) {
    		        // Begiratu etsaiaren pixel GUZTIAK, baten bat behera iritsi den
    		        for (Entitatea p : entNodo.getLista()) {
    		            if (p.getPosizioa().getY() >= 59) { // 58 jarri dugu muga gisa segurtasunagatik
    		                System.out.println("!!! KONSOLEAN: Etsaia behera iritsi da (" + p.getPosizioa().getY() + ") !!!");
    		                jokoaAmaitu();  
    		                JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
    		                return; 
    		            }
    		        }
    		    }
    		}
    		if(espaziontziaTalka()) {
    			System.out.println("!!GALDU DOZU:TALKA");
    			jokoaAmaitu();
    			JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.GALDU);
    		}
    	}
   
    	

    public void etsaiakEzabatu(int x, int y) {
    	
    	EntitateNodo nodoEzabatu = null;
    	// For klasikoa erabiltzen dugu ArrayList-arekin seguruagoa delako. Etsai fantasmak ekiditeko.
    	for (int i=0; i < etsaiak.size();i++) {
    		EntitateNodo nodo= etsaiak.get(i);
    		for (Entitatea e : nodo.getLista()) {
    			if (e.getPosizioa().getX() == x && e.getPosizioa().getY() == y) {
    				nodoEzabatu = nodo;
    				break; 
    			}
    		}
    		if (nodoEzabatu != null) {
    			break;
    		}
    	}
    	//Etsai osoa (pixel guztiak) ezabatu matrizean eta zerrendan
    	if (nodoEzabatu != null) {
    		// Matrizean pixel guztiak "hutsa" (beltz) jarri berehala
    		for (Entitatea e:nodoEzabatu.getLista()) {
    			matrizea[e.getPosizioa().getY()][e.getPosizioa().getX()].gelaxkaEguneratu(new HutsaEgoera());
    		}
    		etsaiak.remove(nodoEzabatu);
    		
    	}
    	if (etsaiak.isEmpty()) {
    		JokoKudeatzailea.getNireJokoKudeatzailea().egoeraAldatu(Egoera.IRABAZI);
    		
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
  	
    	
    	Espaziontzia pixel1 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 55);
        espaziontzia.gehituEntitate(pixel1);  	
        matrizea[pixel1.getPosizioa().getY()][pixel1.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel2 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 51, 55);
        espaziontzia.gehituEntitate(pixel2);  
        matrizea[pixel2.getPosizioa().getY()][pixel2.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel3 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 49, 55);
        espaziontzia.gehituEntitate(pixel3);  
        matrizea[pixel3.getPosizioa().getY()][pixel3.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel4 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 49, 54);
        espaziontzia.gehituEntitate(pixel4);  
        matrizea[pixel4.getPosizioa().getY()][pixel4.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel5 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 51, 54);
        espaziontzia.gehituEntitate(pixel5);  
        matrizea[pixel5.getPosizioa().getY()][pixel5.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());

        Espaziontzia pixel6 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 56);
        espaziontzia.gehituEntitate(pixel6);  
        matrizea[pixel6.getPosizioa().getY()][pixel6.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());

        Espaziontzia pixel7 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 54);
        espaziontzia.gehituEntitate(pixel7);  
        matrizea[pixel7.getPosizioa().getY()][pixel7.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel8 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 53);
        espaziontzia.gehituEntitate(pixel8);  
        matrizea[pixel8.getPosizioa().getY()][pixel8.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel9 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 50, 52);
        espaziontzia.gehituEntitate(pixel9);  
        matrizea[pixel9.getPosizioa().getY()][pixel9.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel10 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 51, 53);
        espaziontzia.gehituEntitate(pixel10);  
        matrizea[pixel10.getPosizioa().getY()][pixel10.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel11 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 49, 53);
        espaziontzia.gehituEntitate(pixel11);  
        matrizea[pixel11.getPosizioa().getY()][pixel11.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel12 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 52, 55);
        espaziontzia.gehituEntitate(pixel12);  
        matrizea[pixel12.getPosizioa().getY()][pixel12.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel13 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 52, 56);
        espaziontzia.gehituEntitate(pixel13);  
        matrizea[pixel13.getPosizioa().getY()][pixel13.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel14 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 52, 57);
        espaziontzia.gehituEntitate(pixel14);  
        matrizea[pixel14.getPosizioa().getY()][pixel14.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel15 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 48, 55);
        espaziontzia.gehituEntitate(pixel15);  
        matrizea[pixel15.getPosizioa().getY()][pixel15.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel16 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 48, 56);
        espaziontzia.gehituEntitate(pixel16);  
        matrizea[pixel16.getPosizioa().getY()][pixel16.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
        
        Espaziontzia pixel17 = EspaziontziaFactory.getEspaziontziaFactory().sortuEspaziontzia(mota, 48, 57);
        espaziontzia.gehituEntitate(pixel17);  
        matrizea[pixel17.getPosizioa().getY()][pixel17.getPosizioa().getX()].gelaxkaEguneratu(new EspaziontziaEgoera());
    	
        
    }
    private boolean espaziontziaTalka() {
        for (EntitateNodo etsaiNodo : etsaiak) {
            for (Entitatea etsaiPixel : etsaiNodo.getLista()) {
                for (Entitatea espazPixel : espaziontzia.getLista()) {
                    if (etsaiPixel.getPosizioa().getX() == espazPixel.getPosizioa().getX() &&
                        etsaiPixel.getPosizioa().getY() == espazPixel.getPosizioa().getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}