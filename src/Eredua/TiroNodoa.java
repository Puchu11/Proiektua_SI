package Eredua;

import java.util.List;
import java.util.ArrayList;

public class TiroNodoa extends Thread {
	private List<Tiro> tiroLista = new ArrayList<>();
	private volatile boolean bizirik;
	
	public TiroNodoa () {
		this.bizirik=true;
	}
	public void gehituTiroa(Tiro t) {
        tiroLista.add(t);
    }
	@Override
	public void run() {
		while(bizirik) {
			try {
				Thread.sleep(50);
			}catch (InterruptedException e) {
				bizirik=false;
				break;
			}
	
			//ezabatu posizio zaharrak
			for(Tiro t: tiroLista) {
				MatrizeE.getEma().getMatrizea()[t.getPosizioa().getY()][t.getPosizioa().getX()].gelaxkaEguneratu(new HutsaEgoera());
			}
			
			//mugitu eta kolisioa egiaztatu
			for(Tiro t: tiroLista) {
					t.mugituGora();
					
					if (t.getPosizioa().getY() <= 0) {
						this.bizirik = false; 
						break;
					}
					
					GelaxkaE gelaxka = MatrizeE.getEma().getGelaxka(t.getPosizioa().getX(), t.getPosizioa().getY());
					if (gelaxka != null && gelaxka.getEntitateMota().equals("etsaia")) {
						MatrizeE.getEma().etsaiaEzabatu(t.getPosizioa().getX(), t.getPosizioa().getY());
						this.bizirik = false; 
						break; 
						
					}
				}
				//marraztu bizirik badago 
			if (bizirik) {
				for (Tiro t: tiroLista) {
					MatrizeE.getEma().getMatrizea()[t.getPosizioa().getY()][t.getPosizioa().getX()].gelaxkaEguneratu(new TiroaEgoera());
				}
			}
		}
	}
	public void mugituGora() {
		for (Tiro t: tiroLista) {
			t.mugituGora();
		}
	}
	public void hil() {
		for(Tiro t: tiroLista) {
			t.hil();
		}
		bizirik=false;
	}
	public boolean bizirikDaude() {
		for (Tiro t: tiroLista) {
			if(t.getPosizioa().getY()<=0) {
				return false;
			}
		}
		return true;
	}
}
