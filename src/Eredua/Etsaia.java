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
	
	public void mugituRandom() {
		Random random= new Random();
		int aukera= random.nextInt(3);
		int x= this.getPosizioa().getX();
		int y= this.getPosizioa().getY();
		
		
		if (aukera==0 && mugituDaiteke("ezkerrera")) {
			//ezkerrerantz mugitu
			this.getPosizioa().setX(x-1);
		}
		if (aukera==1 && mugituDaiteke("eskuinera")) {
			//eskumarantz mugitu
			this.getPosizioa().setX(x+1);
		}
		if (aukera==2 && mugituDaiteke("behera")) {
			//beherantz mugitu
			this.getPosizioa().setY(y+1);
		}
	}
	
}
