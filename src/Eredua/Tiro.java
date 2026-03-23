package Eredua;

public class Tiro extends Thread {

    private Posizioa pos;
    private MugimenduEstrategia estrategia;
    private volatile boolean bizirik = true;
    
    public Tiro(int x, int y, MugimenduEstrategia est) {
        this.pos = new Posizioa(x, y);
        this.estrategia=est;
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
            MatrizeE.getEma().gelaxkaEguneratu(x, y, EntitateMota.HUTSA);    
            estrategia.mugitu(pos);            
            //zig zag tiroa egiterakoan es agertzeko matriz-etik kanpo
            if (pos.getY() < 0 || pos.getX()<0 || pos.getX()>= 100) {
                bizirik = false;             
                break;
            }else {
            	if(MatrizeE.getEma().talkaEginDu(pos.getX(), pos.getY())) {
            		this.hil();
            	}else {
            		MatrizeE.getEma().gelaxkaEguneratu(pos.getX(), pos.getY(), EntitateMota.TIROA);
            	}
            }
        }
    }

    public Posizioa getPosizioa() {
        return pos;
    }

 //   private void mugituGora() {
 //       pos.setY(pos.getY() - 1);
 //   }

    private void hil() {
        bizirik = false;
    }
}