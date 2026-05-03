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
			this.puntuazioTotala = 0;
			MatrizeE.getEma().matrizeaSortu();
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
		this.setChanged();
		this.notifyObservers(mezua);
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
		
		String testua;

	    if (ikurra.equals("●")) {
	        testua = ikurra + "● ● ● ● ● ● ...";
	    } else {
	    	System.out.println("hola");
	        StringBuilder sb = new StringBuilder();

	        for (int i = 0; i < balak; i++) {
	            sb.append(ikurra).append(" ");
	        }

	        testua = sb.toString();
	    }

	    setChanged();
	    notifyObservers("BALAK:  " + testua);
	}
	
	public int getBizitzak() {
	    return this.bizitzak;
	}
	public void bizitzaBatGehitu() {
		this.bizitzak++;
		this.setChanged();
		this.notifyObservers(this.bizitzak);
	}


	public int getPuntuazioTotala() { 
		return puntuazioTotala; 
	}
	public void puntuazioaGehitu(int p) {
		this.puntuazioTotala = this.puntuazioTotala + p;
	
	}
	public Egoera getEgoera() {
	    return this.egoera;
	}
}