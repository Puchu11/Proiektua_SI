package Eredua;

import java.util.Observable;

import Bista.HasierakoPantaila;
import Bista.MatrizeB;

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
		this.egoera = Egoera.HASIERA;
		setChanged();
		notifyObservers(Egoera.HASIERA); 
	}
	
	public void egoeraAldatu(Egoera berria) {
		this.egoera=berria;
		if(this.egoera==Egoera.JOKATZEN) {
			MatrizeE.getEma().matrizeaSortu();
		}
		this.setChanged();
		this.notifyObservers(this.egoera);
	}
}
