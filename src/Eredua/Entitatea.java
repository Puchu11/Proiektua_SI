package Eredua;

public abstract class Entitatea {
	protected Posizioa posizioa;
	
	public Entitatea(int x, int y) {
		this.posizioa=new Posizioa(x, y);
	}

	public Posizioa getPosizioa() {
		return posizioa;
	}

	public void setPosizioa(Posizioa posizioa) {
		this.posizioa = posizioa;
	}
	
	public boolean mugituDaiteke(String norabidea) {
		int posX=this.getPosizioa().getX();
		int posY=this.getPosizioa().getY();
		
		return switch (norabidea) {
			case "gora" -> posY-1 >= 0;
			case "behera" -> posY+1 < 60;
			case "ezkerrera" -> posX-1 >= 0;
			case "eskuinera" -> posX +1 < 100;
			default -> false;
		};
	}
	
}
