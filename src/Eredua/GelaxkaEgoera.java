package Eredua;

import java.awt.Color;

public interface GelaxkaEgoera {

	Color lortuKolorea();
	String lortuMota();
	

}

class HutsaEgoera implements GelaxkaEgoera {
	@Override
	public Color lortuKolorea() {
		return Color.BLACK;
		
		
	}
	@Override
	public String lortuMota() {
		return "hutsa";
	}
	
}

class EtsaiaEgoera implements GelaxkaEgoera {
	@Override
	public Color lortuKolorea() {
		return Color.GREEN;
		
		}
	@Override
	public String lortuMota() {
		return "etsaia";
	
		
	}
	
}

class EspaziontziaEgoera implements GelaxkaEgoera{
	@Override
	public Color lortuKolorea() {
		return MatrizeE.getEma().getEspaziontzia().getKolorea();
		
	}
	@Override
	public String lortuMota() {
		return "espaziontzia";
	}
}

class TiroaEgoera implements GelaxkaEgoera {
	@Override
	public Color lortuKolorea() {
		return Color.YELLOW;
				
		
	}
	@Override
	public String lortuMota() {
		return "tiro";
	}
	
}
