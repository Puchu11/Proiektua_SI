package Eredua;

import java.util.Observable;

public class GelaxkaE extends Observable{
	private Posizioa posizioa;
	private GelaxkaEgoera egoera; 
	
	public GelaxkaE(int x, int y, GelaxkaEgoera hasierakoEgoera) {
		this.posizioa = new Posizioa(x,y);
		if (hasierakoEgoera != null) {
			this.egoera = hasierakoEgoera;
			
		}else {
			this.egoera = new HutsaEgoera();
			
		}
		
	}
	
	public void gelaxkaEguneratu(GelaxkaEgoera berria) {	
		this.egoera = berria;
		setChanged();
		this.notifyObservers(this.egoera.lortuKolorea());
	}

	public String getEntitateMota() {
		return egoera.lortuMota();
	}
	public GelaxkaEgoera getEgoera() {
		return this.egoera; 		
	}
	public Posizioa getPosizioa() {
		return posizioa; 
	}
}
