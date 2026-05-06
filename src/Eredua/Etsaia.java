package Eredua;

public class Etsaia extends Entitatea {

    private int indizea;


    Etsaia(int x, int y, int indizea, int pAbiadura) {
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

        // Mapa mugak
        if (xBerria < 0 || xBerria >= 100 || yBerria < 0 || yBerria >= 60) {
            return false;
        }
 
        final int xFinal = xBerria;
        final int yFinal = yBerria;
        
        // Beste etsaien kontra talka
        return !MatrizeE.getEma().getEtsaiak().stream()
        	    .flatMap(nodo -> nodo.getLista().stream())
        	    .filter(e -> e instanceof Etsaia)
        	    .map(e -> (Etsaia) e)
        	    .anyMatch(etsaia ->
        	        etsaia != this &&
        	        etsaia.getIndizea() != this.getIndizea() &&
        	        etsaia.getPosizioa().getX() == xFinal &&
        	        etsaia.getPosizioa().getY() == yFinal
        	    );
    }

}