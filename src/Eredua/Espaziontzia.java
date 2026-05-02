package Eredua;

import java.awt.Color;

public abstract class Espaziontzia extends Entitatea {
	
	private int geziMunizioa=30;
	private int erronboMunizioa=20;
	private TiroPortaera tiroPortaera;
	private boolean babesa= false;
	
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
			balakEguneratu(1);
		}else if(tiroPortaera instanceof TiroGezi && geziMunizioa<=0) {
			JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Ez da geratzen Gezi Munizioa");
		}
		else if(tiroPortaera instanceof TiroErronboa && erronboMunizioa>0){
			tiroPortaera.tiroEgin(this.getPosizioa().getX(), this.getPosizioa().getY()-4);
			erronboMunizioa--;
			balakEguneratu(2);
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
		balakEguneratu(pMota);
	}
	
	public String getBalarenIkurra() {
		if(tiroPortaera instanceof TiroGezi) {
            return "▲";
	    }else if(tiroPortaera instanceof TiroErronboa){
	    	return "◆";
	    }else {
	    	return "●";
	    }
	}
	
	private void balakEguneratu(int pMota) {
		if (pMota==1) {
			JokoKudeatzailea.getNireJokoKudeatzailea().balakEguneratu(geziMunizioa);
		}else if(pMota==2) {
			JokoKudeatzailea.getNireJokoKudeatzailea().balakEguneratu(erronboMunizioa);
		}else {
			JokoKudeatzailea.getNireJokoKudeatzailea().balakEguneratu(0);
		}
	}
	public int getGeziMunizioa() {return geziMunizioa; }
	public void setGeziMunizioa(int pM) { this.geziMunizioa = pM;}
	
	public int getErronboMunizioa() { return erronboMunizioa;}
	public void setErronboMunizioa(int pM) {this.erronboMunizioa=pM;} 
	
	public boolean isBabesa() {
		return babesa;
	}
	public void setBabesa(boolean babesa) {
		this.babesa=babesa;
	}
}
	
