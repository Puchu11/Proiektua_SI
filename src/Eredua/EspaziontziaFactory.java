package Eredua;

public class EspaziontziaFactory{
	private static EspaziontziaFactory nireFactory;
	private EspaziontziaFactory() {}
	
	public static EspaziontziaFactory getEspaziontziaFactory() {
		if (nireFactory==null) {
			nireFactory = new EspaziontziaFactory();
		}
		return nireFactory;
	}
	
	public Espaziontzia sortuEspaziontzia(String mota, int x, int y) {
		if (mota.equalsIgnoreCase("green")) {
			return new EspaziontziaGreen(x,y);
		}
		else if (mota.equalsIgnoreCase("blue")) {
			return new EspaziontziaBlue(x,y);
		}
		else if (mota.equalsIgnoreCase("red")) {
			return new EspaziontziaRed(x,y);
		} 
		//Jokalariak despistatu egiten bada eta ez badu kolorerik hautatzen defektuzkoa berdea izango da, errorrerik ez egoteko
		return new EspaziontziaGreen(x,y);
	}
}