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
	    tiroLista.forEach(TiroInterfazea::mugituGora);
	}
	
	public void hil() {
	    tiroLista.forEach(TiroInterfazea::hil);
	    bizirik = false;
	}
	public boolean bizirikDaude() {
	    return tiroLista.stream()
	            .map(t -> (Tiro) t)
	            .allMatch(t -> t.getPosizioa().getY() > 0);
	}
}
