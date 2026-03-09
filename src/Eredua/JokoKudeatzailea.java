package Eredua;

import java.util.Observable;

public class JokoKudeatzailea extends Observable {
	private Egoera egoera= Egoera.HASIERA;
	
	private static JokoKudeatzailea ema;
	
	private JokoKudeatzailea() {}
	
	
	public static JokoKudeatzailea getNireJokoKudeatzailea() {
		if(ema==null) {
			ema=new JokoKudeatzailea();
		}
		return ema;
	}
	
	public void hasiJokoa() {
		this.egoera = Egoera.JOKATZEN;
		setChanged();
		notifyObservers("HASI"); 
	}
	
	public void egoeraAldatu(Egoera berria) {
		this.egoera=berria;
		this.setChanged();
		this.notifyObservers(this.egoera);
	}
}
