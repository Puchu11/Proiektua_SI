package Eredua;

public interface GelaxkaEgoera {

	String lortuKolorea();
	String lortuMota();

}

class HutsaEgoera implements GelaxkaEgoera {
	@Override
	public String lortuKolorea() {
		return "beltza";
	}

	@Override
	public String lortuMota() {
		return "hutsa";
	}
}

class EtsaiaEgoera implements GelaxkaEgoera {
	@Override
	public String lortuKolorea() {
		return "berdea";
	}

	@Override
	public String lortuMota() {
		return "etsaia";
	}
}

class EspaziontziaEgoera implements GelaxkaEgoera {

	@Override
	public String lortuKolorea() {
		return MatrizeE.getEma().getEspaziontzia().getKolorea(); 
	}

	@Override
	public String lortuMota() {
		return "espaziontzia";
	}
}

class TiroaEgoera implements GelaxkaEgoera {
	@Override
	public String lortuKolorea() {
		return "horia";
	}

	@Override
	public String lortuMota() {
		return "tiroJokalaria";
	}
}

class TiroEtsaiaEgoera implements GelaxkaEgoera {
    @Override
    public String lortuKolorea() {
        return "gorria";
    }

    @Override
    public String lortuMota() {
        return "tiroEtsaia";
    }
}