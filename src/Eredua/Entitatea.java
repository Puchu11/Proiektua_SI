package Eredua;

public abstract class Entitatea {
	private Posizioa posizioa;
	
	public Entitatea(int x, int y) {
		this.posizioa=new Posizioa(x, y);
	}

	public Posizioa getPosizioa() {
		return posizioa;
	}

	public void setPosizioa(Posizioa posizioa) {
		this.posizioa = posizioa;
	}
	
}
