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
		if (mota.equals("green")) {
			return new EspaziontziaGreen(x,y);
		}
		else if (mota.equals("blue")) {
			return new EspaziontziaBlue(x,y);
		}
		else {
			return new EspaziontziaRed(x,y);
		}
	}
}