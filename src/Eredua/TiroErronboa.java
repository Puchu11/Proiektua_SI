package Eredua;

public class TiroErronboa implements TiroPortaera{
	public void tiroEgin(int x, int y) {
		
		TiroNodoa erronboa=new TiroNodoa();
		
		erronboa.gehituTiroa(new Tiro(x,y-5));
		erronboa.gehituTiroa(new Tiro(x-1, y-4));
		erronboa.gehituTiroa(new Tiro(x+1, y-4));
		erronboa.gehituTiroa(new Tiro(x, y-4));
		erronboa.gehituTiroa(new Tiro(x-1, y-3));
		erronboa.gehituTiroa(new Tiro(x+1, y-3));
		erronboa.gehituTiroa(new Tiro(x, y-3));
		erronboa.gehituTiroa(new Tiro(x-2, y-3));
		erronboa.gehituTiroa(new Tiro(x+2, y-3));
		erronboa.gehituTiroa(new Tiro(x-1, y-2));
		erronboa.gehituTiroa(new Tiro(x+1, y-2));
		erronboa.gehituTiroa(new Tiro(x, y-2));
		erronboa.gehituTiroa(new Tiro(x, y-1));
		
		
		erronboa.start();
	}
}
