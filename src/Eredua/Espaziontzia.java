package Eredua;

import java.awt.Color;

public abstract class Espaziontzia extends Entitatea {
	private MugimenduEstrategia tiroEstrategia= new MugimenduZuzena();
	private int geziMunizioa=30;
	private int erronboMunizioa=20;
	Espaziontzia(int x, int y) {
		super(x, y);
	}
	public abstract Color getKolorea();
	public void mugituGora() {
		this.getPosizioa().setY(this.getPosizioa().getY()-1);
	}
	
	public void setTiroEstrategia(MugimenduEstrategia berria) {
		this.tiroEstrategia=berria;
	}
	public void tiroEgin() {
		
		if(tiroEstrategia instanceof MugimenduSigiSaga) {
			if(geziMunizioa>0) {
				geziMunizioa--;
				System.out.println("Gezia geratzen da: "+geziMunizioa);
				// 3 pixel sortzen ditugu gezi itxura egiteko
				sortuTiroKonposatua();
			}else {
				this.tiroEstrategia = new MugimenduZuzena();
				// Muniziorik gabe, tiro zuzen bat botako du automatikoki
				new Tiro(this.getPosizioa().getX(), this.getPosizioa().getY()-2, tiroEstrategia).start();
			}
		}else {
			//tiro zuzena
			new Tiro(this.getPosizioa().getX(), this.getPosizioa().getY()-2, tiroEstrategia).start();
		}
	}
	private void sortuTiroKonposatua() {
		int x =this.getPosizioa().getX();
		int y = this.getPosizioa().getY()-2;
		new Tiro(x,y, tiroEstrategia).start();
		new Tiro(x-1, y+1 , tiroEstrategia).start();
		new Tiro(x+1, y-1 , tiroEstrategia).start();
	}
}
