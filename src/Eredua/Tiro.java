package Eredua;
import java.util.ArrayList;

public class Tiro extends Thread {

    private Posizioa pos;
    private volatile boolean bizirik = true;
    public Tiro(int x, int y) {
        this.pos = new Posizioa(x, y);
    } 

    @Override 
    public void run() {
        while (bizirik) {
            int x = pos.getX();
            int y = pos.getY();
            if (y < 0) {
                bizirik = false;
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }           
            MatrizeE.getEma().gelaxkaEguneratu(x, y, new HutsaEgoera());    
            mugituGora();            
            //
            if (pos.getY() < 0 || pos.getX()<0 || pos.getX()>= 100) {
                bizirik = false;             
                break;
            }else {
            	if(MatrizeE.getEma().talkaEginDu(pos.getX(), pos.getY())) {
            		this.hil();
            	}else {
            		MatrizeE.getEma().gelaxkaEguneratu(pos.getX(), pos.getY(), new TiroaEgoera());
            	}
            }
        }
    }
    
    public Posizioa getPosizioa() {
        return pos;
    }

    public void mugituGora() {
    	pos.setY(pos.getY() - 1);
    }

    private void hil() {
        bizirik = false;
    }
    
}