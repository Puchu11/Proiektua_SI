package Eredua;

public class TiroGezi implements TiroPortaera{
	public void tiroEgin(int x, int y) {
		TiroNodoa gezia = new TiroNodoa ();
		gezia.gehituTiroa(new Tiro(x,y));
		gezia.gehituTiroa(new Tiro(x-1, y+1));
		gezia.gehituTiroa(new Tiro(x+1, y+1));
		
		gezia.start();
	}
}
