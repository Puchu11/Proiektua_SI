package Eredua;

public abstract class Entitatea implements EntitateInterfazea {
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
		}
		;
	}
	public void mugitu(String norabidea) {
		switch (norabidea) {
		case "gora":
            mugituGora();
            break;
        case "behera":
            mugituBehera();
            break;
        case "ezkerrera":
            mugituEzkerra();
            break;
        case "eskuinera":
            mugituEskuina();
            break;
        default:
            break;
		}
	}
	private void mugituGora() {
		 this.getPosizioa().setY(this.getPosizioa().getY() - 1);
	}
	private void mugituBehera() {
	    this.getPosizioa().setY(this.getPosizioa().getY() + 1);
	}
	private void mugituEzkerra() {
	    this.getPosizioa().setX(this.getPosizioa().getX() - 1);
	}
	private void mugituEskuina() {
	    this.getPosizioa().setX(this.getPosizioa().getX() + 1);
	}  
	
}
