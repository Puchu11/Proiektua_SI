package Eredua;

public abstract class Espaziontzia extends Entitatea {
	private int geziMunizioa=30;
	private int erronboMunizioa=20;
	private int tiroMota = 0;
	Espaziontzia(int x, int y) {
		super(x, y);
	}
	
	public void mugituGora() {
		this.getPosizioa().setY(this.getPosizioa().getY()-1);
	}
	

	public void tiroEgin(int tiroMota) {
		if(tiroMota==1) {
			if(geziMunizioa>0) {
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
		int y = this.getPosizioa().getY()-2;
		new Tiro(x,y).start();
		new Tiro(x-1, y+1).start();
		new Tiro(x+1, y+1).start();
	}
	private void sortuTiroNormala(){
		int x=this.getPosizioa().getX();
		int y=this.getPosizioa().getY();
		new Tiro(x,y-1).start();
	}
	private void sortuTiroErronboa() {
		int x =this.getPosizioa().getX();
		int y = this.getPosizioa().getY()-2;
		new Tiro(x,y-5).start();
		new Tiro(x-1, y-4).start();
		new Tiro(x+1, y-4).start();
		new Tiro(x, y-4).start();
		new Tiro(x-1, y-3).start();
		new Tiro(x+1, y-3).start();
		new Tiro(x, y-3).start();
		new Tiro(x-2, y-3).start();
		new Tiro(x+2, y-3).start();
		new Tiro(x-1, y-2).start();
		new Tiro(x+1, y-2).start();
		new Tiro(x, y-2).start();
		new Tiro(x, y-1).start();
	}
 
    public int getTiroMota() {
    	return this.tiroMota;
    }
    public void setTiroMota(int pMota) {
    	if(pMota>=0 && pMota<=2) {
    		this.tiroMota=pMota;
    	}
    }
}
