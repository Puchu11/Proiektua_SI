package Eredua;

public class MugimenduZuzena implements MugimenduEstrategia{
	@Override
	public void mugitu(Posizioa p) {
		p.setY(p.getY()-1);//mugimendu estandarra gorantz
	}
}
