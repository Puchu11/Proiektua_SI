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
			if (!bizirikDaude()) {
				hil();
			}else {
				//ezabatu posizio zaharrak
				for(Tiro t: tiroLista) {
					MatrizeE.getEma().getMatrizea()[t.getPosizioa().getY()][t.getPosizioa().getX()].gelaxkaEguneratu(new HutsaEgoera());
				}
				//mugitu dena
				for(Tiro t: tiroLista) {
					t.mugituGora();
				}
				//marraztu dena
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
