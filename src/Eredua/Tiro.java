package Eredua;

public class Tiro extends Thread {

    private Posizioa pos;
    private volatile boolean bizirik = true;

    public Tiro(int x, int y) {
        this.pos = new Posizioa(x, y);
    }

    @Override
    public void run() {
        MatrizeE m = MatrizeE.getEma();

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
            m.gelaxkaEguneratu(x, y, EntitateMota.HUTSA);    
            mugituGora();            
            if (pos.getY() < 0) {
                bizirik = false;             
            }else {
            	if(m.talkaEginDu(pos.getX(), pos.getY())) {
            		this.hil();
            	}else {
            		m.gelaxkaEguneratu(pos.getX(), pos.getY(), EntitateMota.TIROA);
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