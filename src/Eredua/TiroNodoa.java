package Eredua;

import java.util.List;
import java.util.ArrayList;

public class TiroNodoa extends Thread implements TiroInterfazea {
	private List<TiroInterfazea> tiroLista = new ArrayList<>();
	private volatile boolean bizirik;
	
	public TiroNodoa () {
		this.bizirik=true;
	}
	public void gehituTiroa(TiroInterfazea t) {
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
			
			//mugitu eta kolisioa egiaztatu
			for(TiroInterfazea tInterfazea: tiroLista) {
				Tiro t = (Tiro) tInterfazea;
				MatrizeE.getEma().getMatrizea()[t.getPosizioa().getY()][t.getPosizioa().getX()].gelaxkaEguneratu(new HutsaEgoera());
					t.mugituGora();
					
					if (t.getPosizioa().getY() <= 0) {
						try {
							Thread.sleep(50);
						}catch (InterruptedException e) {
							bizirik=false;
							break;
						}
						
						hil();
						break;
					}
					
					GelaxkaE gelaxka = MatrizeE.getEma().getGelaxka(t.getPosizioa().getX(), t.getPosizioa().getY());
					if (gelaxka != null && gelaxka.getEntitateMota().equals("etsaia")) {
						MatrizeE.getEma().etsaiakEzabatu(t.getPosizioa().getX(), t.getPosizioa().getY());
						hil();
						break; 						
					}
					MatrizeE.getEma().getMatrizea()[t.getPosizioa().getY()][t.getPosizioa().getX()].gelaxkaEguneratu(new TiroaEgoera());
				}
		}
	}
	public void mugituGora() {
		for (TiroInterfazea t: tiroLista) {
			t.mugituGora();
		}
	 }
	public void hil() {
		for(TiroInterfazea t: tiroLista) {
			t.hil();
		}
		bizirik=false;
	}
	public boolean bizirikDaude() {
		for (TiroInterfazea tInterfazea: tiroLista) {
			Tiro t = (Tiro) tInterfazea;
			if(t.getPosizioa().getY()<=0) {
				return false;
			}
		}
		return true;
	}
}
