package Eredua;
import java.util.ArrayList;

public class Tiro implements TiroInterfazea{
	
    private Posizioa pos;
    private String norabidea;
    private String jabea; 
    public Tiro(int x, int y,String pNorabidea,String pJabea) {
    	this.norabidea=pNorabidea;
        this.pos = new Posizioa(x, y);
        this.jabea=pJabea;
    } 
    
    public Posizioa getPosizioa() {
        return pos;
    }
    
    public String getNorabidea() {
        return norabidea;
    }
    public String getJabea() {
        return jabea;
    }
    public boolean kolizioa() {
        if (pos.getY() <= 0 || pos.getY() >= 59) {
            return true;
        }
        GelaxkaE gelaxka = MatrizeE.getEma().getGelaxka(pos.getX(),pos.getY()+1);
        if(norabidea.equals("gora")) {
        	gelaxka = MatrizeE.getEma().getGelaxka(pos.getX(),pos.getY()-1);
        }
        if (gelaxka == null) {
            return true;
        }
        String mota = gelaxka.getEntitateMota();

        if (mota.equals("etsaia")) {
        	if(norabidea.equals("gora")) {	
        		MatrizeE.getEma().etsaiakEzabatu(pos.getX(), pos.getY()-1);
        	}else {
        		MatrizeE.getEma().etsaiakEzabatu(pos.getX(), pos.getY()+1);
        	}
            return true;
        }

        if (mota.equals("tiroEtsaia") && jabea.equals("jokalaria")) {
            hil();
            return true;
        }

        if (mota.equals("tiroJokalaria") && jabea.equals("etsaia")) {
            hil();
            return true;
        }

        if (mota.equals("espaziontzia")) {   
            JokoKudeatzailea.getNireJokoKudeatzailea().bizitzaBatKendu();
            return true;
        }
        return false;
    }
    
    public void mugitu() {

        MatrizeE.getEma()
                .getMatrizea()[this.getPosizioa().getY()][this.getPosizioa().getX()]
                .gelaxkaEguneratu(new HutsaEgoera());

        if (norabidea.equals("gora")) {
            getPosizioa().setY(getPosizioa().getY() - 1);
        } else if (norabidea.equals("behera")) {
            getPosizioa().setY(getPosizioa().getY() + 1);
        }

        if (jabea.equals("jokalaria")) {
            MatrizeE.getEma()
                    .getMatrizea()[this.getPosizioa().getY()][this.getPosizioa().getX()]
                    .gelaxkaEguneratu(new TiroaEgoera());
        } else {
            MatrizeE.getEma()
                    .getMatrizea()[this.getPosizioa().getY()][this.getPosizioa().getX()]
                    .gelaxkaEguneratu(new TiroEtsaiaEgoera());
        }
    }
    
    public void hil() {
        MatrizeE.getEma().getMatrizea()[this.getPosizioa().getY()][this.getPosizioa().getX()].gelaxkaEguneratu(new HutsaEgoera());
    } 
    
}