import Bista.HasierakoPantaila;
import Bista.MatrizeB;
import Eredua.AudioKudeatzailea;
import Eredua.JokoKudeatzailea;
import Eredua.MatrizeE;

public class Nagusia {
	public static void main(String[] args) {
		AudioKudeatzailea.getAudioKudeatzailea().musikaErreproduzitu("src/res/intro_musika.wav");
		
		HasierakoPantaila hasierakoPantaila = new HasierakoPantaila();
		MatrizeE matrizeE = MatrizeE.getEma();
		MatrizeB matrizeB = new MatrizeB();
		JokoKudeatzailea.getNireJokoKudeatzailea().hasiJokoa();
	}
}

