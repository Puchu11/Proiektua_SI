package Eredua;

import java.awt.Color;

public abstract class Espaziontzia extends Entitatea {
	
	private int geziMunizioa=30;
	private int erronboMunizioa=20;
	private TiroPortaera tiroPortaera;
	
	Espaziontzia(int x, int y) {
		super(x, y);
		tiroPortaera= new TiroNormala();
	}
	public abstract Color getKolorea();
	public void mugituGora() {
		this.getPosizioa().setY(this.getPosizioa().getY()-1);
	}
	
	public void portaeraAldatu(int pMota) {
		if(pMota==1) {
			tiroPortaera= new TiroGezi();
		}else if (pMota==2) {
			tiroPortaera = new TiroErronboa();
		}else {
			tiroPortaera = new TiroNormala();
		}
	}
	
	public void tiroEgin() {		
		if(tiroPortaera instanceof TiroGezi && geziMunizioa>0) {
			tiroPortaera.tiroEgin(this.getPosizioa().getX(), this.getPosizioa().getY()-4);
			geziMunizioa--;
			JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Gezi Munizioa: " + geziMunizioa);
		}else if(tiroPortaera instanceof TiroGezi && geziMunizioa<=0) {
			JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Ez da geratzen Gezi Munizioa");
		}
		else if(tiroPortaera instanceof TiroErronboa && erronboMunizioa>0){
			tiroPortaera.tiroEgin(this.getPosizioa().getX(), this.getPosizioa().getY()-4);
			erronboMunizioa--;
			JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Erronbo Munizioa: " + erronboMunizioa);
		}else if(tiroPortaera instanceof TiroErronboa && geziMunizioa<=0) {
			JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Ez da geratzen Gezi Erronboa");
		}
		else if(tiroPortaera instanceof TiroNormala) {
			tiroPortaera.tiroEgin(this.getPosizioa().getX(), this.getPosizioa().getY()-4);
		}
	}
	
	public void setTiroPortaera(int pMota) {
		if(pMota==1) {
			tiroPortaera= new TiroGezi();
		}else if (pMota==2) {
			tiroPortaera = new TiroErronboa();
		}else {
			tiroPortaera = new TiroNormala();
		}
	}
}
