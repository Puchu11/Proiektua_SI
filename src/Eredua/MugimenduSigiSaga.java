package Eredua;

public class MugimenduSigiSaga implements MugimenduEstrategia{
	private int kont=0;
	@Override
	public void mugitu(Posizioa p) {
		p.setY(p.getY()-1);
		//bi pauso behin direkzioz aldatzen da
		if((kont/2)%2==0 ) {
			p.setX(p.getX()+2);
		}else {
			p.setX(p.getX()-2);
		}
		kont++;
	}
}
