package Eredua;

import java.util.Observable;

import javax.swing.Timer;

import Bista.HasierakoPantaila;
import Bista.MatrizeB;

public class JokoKudeatzailea extends Observable {
	private Egoera egoera= Egoera.HASIERA;
	
	private int bizitzak=3;
	
	private static JokoKudeatzailea ema;
	
	private JokoKudeatzailea() {}
	
	private String espaziontziMota = "green";
	private int puntuazioTotala = 0;
	private int puntuazioMaximoa=0;
	
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
		this.puntuazioTotala = 0;
		setChanged();
		notifyObservers(Egoera.HASIERA);
	}
	
	public void egoeraAldatu(Egoera berria) {
		this.egoera=berria;
		AudioKudeatzailea audio = AudioKudeatzailea.getAudioKudeatzailea();
		
		if (berria == Egoera.HASIERA) {
			audio.musikaErreproduzitu("src/res/intro_musika.wav");
			
		}else if (berria == Egoera.JOKATZEN) {
			this.bizitzak = 3;
			MatrizeE.getEma().matrizeaSortu();
			this.balakEguneratu(0);
			audio.musikaErreproduzitu("src/res/joko_musika.wav");
		}else if (berria == Egoera.IRABAZI || berria == Egoera.GALDU) {
			audio.musikaGelditu();
			MatrizeE.getEma().jokoaAmaitu();
			
			if (berria == Egoera.IRABAZI) {
				audio.musikaErreproduzitu("src/res/irabazi.wav");
			} else {
				audio.musikaErreproduzitu("src/res/galdu.wav");
			}
	
		}
		this.setChanged();
		this.notifyObservers(this.egoera);
	}
	
	public void mezuaErakutsi(String mezua) {
		if (this.egoera==Egoera.JOKATZEN || this.egoera == Egoera.PAUSA) {
			this.setChanged();
			this.notifyObservers(mezua);
		}
	}

	public void bizitzaBatKendu() {
	    this.bizitzak--;
	    this.setChanged();
	    this.notifyObservers(this.bizitzak);

	    if (this.bizitzak <= 0) {
	        this.egoeraAldatu(Egoera.GALDU);
	    } else {
	        AudioKudeatzailea.getAudioKudeatzailea().soinuaErreproduzitu("src/res/mario_death.wav");
	    }
	}
	
	public void balakEguneratu(int balak) {

	    String ikurra = MatrizeE.getEma().getEspaziontzia().getBalarenIkurra();
	    StringBuilder sb = new StringBuilder();
	    if (ikurra.equals("●")) {

	        sb.append("● ∞");
	    } else {
	        for (int i = 0; i < balak; i++) {
	            sb.append(ikurra).append(" ");
	        }
	    }
	    setChanged();
	    notifyObservers("BALAK:" + sb.toString());
	}
	
	
	public int getBizitzak() {
	    return this.bizitzak;
	}
	public void bizitzaBatGehitu() {
	    if (this.bizitzak < 3) {
	        this.bizitzak++;
	        this.setChanged();
	        this.notifyObservers(this.bizitzak);
	    }
	}

	public void berriroJolastu(String Jolastu) {
		if(this.egoera==Egoera.IRABAZI || this.egoera==Egoera.GALDU) {
			if(Jolastu.equals("jolastu")) {
				if(this.egoera==Egoera.GALDU) {
					resetPuntuazioTotala();
				}
				egoeraAldatu(Egoera.JOKATZEN);		
			}else {
				System.exit(0);
			}
		}
	}
	public int getPuntuazioTotala() { 
		return puntuazioTotala; 
	}
	public void resetPuntuazioTotala() { 
		puntuazioTotala=0;
		 setChanged();
		 notifyObservers("PUNTUAK:" + this.puntuazioTotala); 
	}
	public void puntuazioaGehitu(int p) {
	    this.puntuazioTotala = this.puntuazioTotala + p;
	    if (this.puntuazioTotala > this.puntuazioMaximoa) {
	    	this.puntuazioMaximoa=this.puntuazioTotala;
	    }
	    setChanged();
	    notifyObservers("PUNTUAK:" + this.puntuazioTotala);
	    setChanged();
	    notifyObservers("RECORD:"+ this.puntuazioMaximoa);
	}
	public Egoera getEgoera() {
	    return this.egoera;
	}

	public void pausaAldatu() {
	    if (this.egoera == Egoera.JOKATZEN) {
	        this.egoera = Egoera.PAUSA;
	        AudioKudeatzailea.getAudioKudeatzailea().musikaMuteatu();
	        setChanged();
	        notifyObservers(Egoera.PAUSA);
	    } 
	    else if (this.egoera == Egoera.PAUSA) {
	        this.egoera = Egoera.JOKATZEN;
	        AudioKudeatzailea.getAudioKudeatzailea().musikaMuteatu();
	        setChanged();
	        notifyObservers(Egoera.JOKATZEN);
	    }
	}

}