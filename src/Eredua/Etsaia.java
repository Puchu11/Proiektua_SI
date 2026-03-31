package Eredua;

import java.util.Iterator;

public class Etsaia extends Entitatea {
	
	private int indizea;
	Etsaia(int x, int y, int indizea) {
		super(x, y);
		this.indizea = indizea;
	}
	
	public int getIndizea() {
		return indizea;
	}
	
	@Override
	public boolean mugituDaiteke(String norabidea) {
	    int posX = this.getPosizioa().getX();
	    int posY = this.getPosizioa().getY();

	    int xBerria = posX;
	    int yBerria = posY;

	    switch (norabidea) {
	        case "gora":
	            yBerria--;
	            break;
	        case "behera":
	            yBerria++;
	            break;
	        case "ezkerrera":
	            xBerria--;
	            break;
	        case "eskuinera":
	            xBerria++;
	            break;
	        default:
	            return false;
	    }

	    if (xBerria < 0 || xBerria >= 100 || yBerria < 0 || yBerria >= 60) {
	        return false;
	    }
	    Iterator<Entitatea> itr = MatrizeE.getEma().getEtsaiak().getIterator();
	    while (itr.hasNext()){
	        if (e != this &&
	            e.getPosizioa().getX() == xBerria &&
	            e.getPosizioa().getY() == yBerria &&
	            e.getIndizea() != this.getIndizea()) {

	            return false;
	        }
	    }

	    return true;
	}
}
