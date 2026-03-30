package Eredua;
import java.util.ArrayList;

public class TiroGroup extends Thread {

    private Posizioa pos;
    private int mota;
    private volatile boolean bizirik = true;
    private ArrayList<Posizioa> pixelak = new ArrayList<>();
    public TiroGroup(int x, int y) {
        this.pos = new Posizioa(x, y);
    } 
    //tiro berrietarako erakitzailea
    public TiroGroup(ArrayList<Posizioa> pPixelak) {
    	this.pixelak=pPixelak;
    	this.mota=0;
    }
    public TiroGroup(ArrayList<Posizioa> pPixelak, int mota) {
    	this.pixelak=pPixelak;
    	this.mota=mota;
    }
    @Override 
    public void run() {
        while (bizirik) {
            int x = pos.getX();
            int y = pos.getY();
            switch(mota) {
            	case 0:
            		break;
            	case 1:
            		break;
            	default:
            		break;
            }
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

    private void mugituGora() {
    	pos.setY(pos.getY() - 1);
    }

    private void hil() {
        bizirik = false;
    }
    
}