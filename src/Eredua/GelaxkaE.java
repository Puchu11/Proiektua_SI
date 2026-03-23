package Eredua;

import java.util.Observable;

public class GelaxkaE extends Observable{
	private Posizioa posizioa;
	private EntitateMota entitate;
	
	public GelaxkaE(int x, int y, EntitateMota entitate) {
		this.posizioa=new Posizioa(x, y);
		this.entitate=entitate;
	}
	
	public void gelaxkaEguneratu(EntitateMota entitate) {	
		this.entitate=entitate;
		setChanged();
		this.notifyObservers(entitate);
	}

	public String getEntitateMota() {
		if(this.entitate==EntitateMota.ETSAIA) {
			return "etsaia";
		}else if(this.entitate==EntitateMota.ESPAZIONTZIA) {
			return "espaziontzia";
		}else if(this.entitate==EntitateMota.TIROA) {
			return "tiro";
		}else {
			return "hutsa";
		}
	}
}
