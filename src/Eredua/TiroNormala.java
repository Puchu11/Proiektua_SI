package Eredua;

public class TiroNormala implements TiroPortaera{
	public void tiroEgin(int x, int y) {		
		TiroNodoa normala=new TiroNodoa();
		normala.gehituTiroa(new Tiro(x,y));		
		normala.start();
	}
}
