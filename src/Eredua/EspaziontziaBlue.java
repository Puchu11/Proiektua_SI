package Eredua;

import java.awt.Color;

public class EspaziontziaBlue extends Espaziontzia {
    
	public EspaziontziaBlue(int x, int y) {
        super(x, y);
    }
    
    public String getKolorea() {
        return "urdina";
    }
    
    @Override
    public void portaeraAldatu(int pMota) {
        if (pMota == 1) {
        	JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Blue ezin du gezirik bota!");
        }else {
        	setTiroPortaera(pMota);
        }
        
    }
}