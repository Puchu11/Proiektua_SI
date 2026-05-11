package Eredua;

public class EtsaiTiro extends Etsaia {

    EtsaiTiro(int x, int y, int indizea) {
        super(x, y, indizea);
    }

    public void tiroEgin() {
    	if (JokoKudeatzailea.getNireJokoKudeatzailea().getEgoera() != Egoera.JOKATZEN) {
    	        return;
    	}
        Tiro tiro = new Tiro(
            getPosizioa().getX(),
            getPosizioa().getY() + 1,
            "behera", "etsaia"
        );

        TiroNodoa tiroNodo = new TiroNodoa();
        tiroNodo.gehituTiroa(tiro);
        tiroNodo.start();
    }
}
