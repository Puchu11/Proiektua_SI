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
	
	public EntitateNodo sortuEspaziontzia(String mota) {
		if (mota.equalsIgnoreCase("green")) {
			return espaziontziaGreen();
		}
		else if (mota.equalsIgnoreCase("blue")) {
			return espaziontziaBlue();
		}
		else if (mota.equalsIgnoreCase("red")) {
			return espaziontziaRed();
		} 
		//Jokalariak despistatu egiten bada eta ez badu kolorerik hautatzen defektuzkoa berdea izango da, errorrerik ez egoteko
		return espaziontziaRed();
	}
	
	private EntitateNodo espaziontziaGreen() {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
				{50, 51},                 
				{49, 52}, {50, 52}, {51, 52},
				{48, 53}, {49, 53}, {50, 53}, {51, 53}, {52, 53},
				{49, 54}, {50, 54}, {51, 54},
				{49, 55}, {51, 55}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new EspaziontziaGreen(pos[0], pos[1]));
			MatrizeE.getEma().getMatrizea()[pos[1]][pos[0]].setEgoera(new EspaziontziaEgoera());
		}

		return nodo;
	}

	private EntitateNodo espaziontziaBlue() {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
				{50, 51},                 
				{49, 52}, {51, 52},
				{48, 53}, {50, 53}, {52, 53},
				{49, 54}, {51, 54},
				{48, 55}, {52, 55}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new EspaziontziaBlue(pos[0], pos[1]));
			MatrizeE.getEma().getMatrizea()[pos[1]][pos[0]].setEgoera(new EspaziontziaEgoera());
		}

		return nodo;
	}

	private EntitateNodo espaziontziaRed() {
		EntitateNodo nodo = new EntitateNodo();

		int[][] koordenatuak = {
				{50, 50},                
				{50, 51},
				{49, 52}, {50, 52}, {51, 52},
				{48, 53}, {50, 53}, {52, 53},
				{49, 54}, {51, 54}
		};

		for (int[] pos : koordenatuak) {
			nodo.gehituEntitate(new EspaziontziaRed(pos[0], pos[1]));
			MatrizeE.getEma().getMatrizea()[pos[1]][pos[0]].setEgoera(new EspaziontziaEgoera());
		}
		return nodo;
	}
}