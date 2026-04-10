package Eredua;

import java.util.Observable;

import javax.swing.Timer;

import Bista.HasierakoPantaila;
import Bista.MatrizeB;

public class JokoKudeatzailea extends Observable {
	private Egoera egoera= Egoera.HASIERA;
	
	private static JokoKudeatzailea ema;
	
	private JokoKudeatzailea() {}
	
	private String espaziontziMota = "green";
	
	public static JokoKudeatzailea getNireJokoKudeatzailea() {
		if(ema==null) {
			ema=new JokoKudeatzailea();
		}
		return ema;
	}
	public void setEspaziontziMota(String motaBerria) {
		this.espaziontziMota = motaBerria;
	}
	public String getEspaziontziMota() {
		return this.espaziontziMota;
	}
	
	public void hasiJokoa() {
		this.egoera = Egoera.HASIERA;
		setChanged();
		notifyObservers(Egoera.HASIERA); 
	}
	
	public void egoeraAldatu(Egoera berria) {
		this.egoera=berria;
		if(this.egoera==Egoera.JOKATZEN) {
			MatrizeE.getEma().matrizeaSortu();
		}
		if(this.egoera==Egoera.IRABAZI||this.egoera==Egoera.GALDU) {
			MatrizeE.getEma().jokoaAmaitu();
		}
		this.setChanged();
		this.notifyObservers(this.egoera);
	}
	
	public void mezuaErakutsi(String mezua) {
		this.setChanged();
		this.notifyObservers(mezua);
	}
}
