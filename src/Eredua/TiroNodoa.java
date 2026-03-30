package Eredua;

import java.util.List;
import java.util.ArrayList;

public class TiroNodoa extends Tiro {
	private List<Tiro> tiroLista = new ArrayList<>();
	
	public TiroNodoa (int x, int y) {
		super (x,y);
	}
	public void gehituTiroa(Tiro t) {
        tiroLista.add(t);
    }
	public void mugituGora() {
		for (Tiro t: tiroLista) {
			t.mugituGora();
		}
	}
}
