package Eredua;

import java.util.Observable;

public class Gelaxka extends Observable{
	private Posizioa posizioa;
	private EntitateMota entitate;
	
	public Gelaxka(int x, int y, EntitateMota entitate) {
		this.posizioa=new Posizioa(x, y);
		this.entitate=entitate;
	}
	
	public void gelaxkaEguneratu(EntitateMota entitate) {
		this.entitate=entitate;
		if(entitate==EntitateMota.ETSAIA) {
			this.setChanged();
			this.notifyObservers("etsaia");
		}else if(entitate==EntitateMota.ESPAZIONTZIA) {
			this.setChanged();
			this.notifyObservers("espaziontzia");	
		}else if(entitate==EntitateMota.TIROA) {
			this.setChanged();
			this.notifyObservers("tiro");
		}
	}
}
