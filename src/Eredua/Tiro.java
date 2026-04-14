package Eredua;
import java.util.ArrayList;

public class Tiro implements TiroInterfazea{

    private Posizioa pos;
    private volatile boolean bizirik = true;
    public Tiro(int x, int y) {
        this.pos = new Posizioa(x, y);
    } 
    
    public Posizioa getPosizioa() {
        return pos;
    }

    public void mugituGora() {
    	pos.setY(pos.getY() - 1);

    }

    public void hil() {
        bizirik = false;
        MatrizeE.getEma().getMatrizea()[this.getPosizioa().getY()][this.getPosizioa().getX()].gelaxkaEguneratu(new HutsaEgoera());
    }    
}