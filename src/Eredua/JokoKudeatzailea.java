package Eredua;

import java.util.Observable;

public class JokoKudeatzailea extends Observable {
	private Egoera egoera= Egoera.HASIERA;
	
	private static JokoKudeatzailea ema;
	
	private JokoKudeatzailea() {}
	
	
	public static JokoKudeatzailea getNireJokoKudeatzailea() {
		if(ema==null) {
			ema=new JokoKudeatzailea();
		}
		return ema;
	}
	
	
}
