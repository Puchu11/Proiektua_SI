package Eredua;

import java.awt.Color;

public abstract class Espaziontzia extends Entitatea {
	
	private int geziMunizioa=30;
	private int erronboMunizioa=20;
	private int tiroMota = 0;
	
	Espaziontzia(int x, int y) {
		super(x, y);
	}
	public abstract Color getKolorea();
	public void mugituGora() {
		this.getPosizioa().setY(this.getPosizioa().getY()-1);
	}
	
	public void setTiro(int pMota) {
		this.tiroMota=pMota;
	}
	
	public void tiroEgin() {
		if(tiroMota==1) {
			if(geziMunizioa > 0) {
				geziMunizioa--;
				System.out.println("Gezia geratzen da: "+geziMunizioa);
				// 3 pixel sortzen ditugu gezi itxura egiteko
				sortuTiroGezi();
			}else {
				// Muniziorik gabe, tiro zuzen bat botako du automatikoki
				sortuTiroNormala();
				
			} 
		}else if(tiroMota==2) {
			if(erronboMunizioa>0) {
				erronboMunizioa--;
				System.out.println("Erronbo geratzen dira: "+ erronboMunizioa);
				sortuTiroErronboa();
			}else {
				sortuTiroNormala();
			}
		}else {
			//tiro zuzena
			sortuTiroNormala();
			
		}
	}
	
	private void sortuTiroGezi() {
		int x =this.getPosizioa().getX();
		int y = this.getPosizioa().getY()-4;
		
		TiroNodoa gezia = new TiroNodoa ();
		gezia.gehituTiroa(new Tiro(x,y));
		gezia.gehituTiroa(new Tiro(x-1, y+1));
		gezia.gehituTiroa(new Tiro(x+1, y+1));
		
		gezia.start();
	}
	
	
	private void sortuTiroNormala(){
		int x=this.getPosizioa().getX();
		int y=this.getPosizioa().getY();
		TiroNodoa normala=new TiroNodoa();
		normala.gehituTiroa(new Tiro(x,y-4));
		
		normala.start();
	}
	
	
	private void sortuTiroErronboa() {
		int x =this.getPosizioa().getX();
		int y = this.getPosizioa().getY()-3;
		
		TiroNodoa erronboa=new TiroNodoa();
		
		erronboa.gehituTiroa(new Tiro(x,y-5));
		erronboa.gehituTiroa(new Tiro(x-1, y-4));
		erronboa.gehituTiroa(new Tiro(x+1, y-4));
		erronboa.gehituTiroa(new Tiro(x, y-4));
		erronboa.gehituTiroa(new Tiro(x-1, y-3));
		erronboa.gehituTiroa(new Tiro(x+1, y-3));
		erronboa.gehituTiroa(new Tiro(x, y-3));
		erronboa.gehituTiroa(new Tiro(x-2, y-3));
		erronboa.gehituTiroa(new Tiro(x+2, y-3));
		erronboa.gehituTiroa(new Tiro(x-1, y-2));
		erronboa.gehituTiroa(new Tiro(x+1, y-2));
		erronboa.gehituTiroa(new Tiro(x, y-2));
		erronboa.gehituTiroa(new Tiro(x, y-1));
		
		
		erronboa.start();
	}
 
    public int getTiroMota() {
    	return this.tiroMota;
    }
    
    public void setTiroMota(int pMota) {
    	if(pMota>=0 && pMota<=2) {
    		this.tiroMota=pMota;
    		System.out.print("tiro mota: "+ this.tiroMota);
    	}
    }
}
