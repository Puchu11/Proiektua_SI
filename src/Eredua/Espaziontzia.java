package Eredua;

public class Espaziontzia extends Entitatea {
	Espaziontzia(int x, int y) {
		super(x, y);
	}
	
	public void mugituGora() {
		this.getPosizioa().setY(this.getPosizioa().getY()-1);
	}
	public void mugituBehera() {
	    this.getPosizioa().setY(this.getPosizioa().getY() + 1);
	}
	public void mugituEzkerra() {
	    this.getPosizioa().setX(this.getPosizioa().getX() - 1);
	}
	public void mugituEskuina() {
	    this.getPosizioa().setX(this.getPosizioa().getX() + 1);
	}
	public void tiroEgin() {
        Tiro tiroa = new Tiro(this.getPosizioa().getX(), this.getPosizioa().getY() - 2);
        tiroa.start();
    }
}
