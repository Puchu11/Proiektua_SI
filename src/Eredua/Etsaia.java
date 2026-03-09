package Eredua;

import java.util.Random;

public class Etsaia extends Entitatea {
	
	private int indizea;
	Etsaia(int x, int y, int indizea) {
		super(x, y);
		this.indizea = indizea;
	}
	
	public int getIndizea() {
		return indizea;
	}
}
