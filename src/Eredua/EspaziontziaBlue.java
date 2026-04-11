package Eredua;

import java.awt.Color;

public class EspaziontziaBlue extends Espaziontzia {
    
	public EspaziontziaBlue(int x, int y) {
        super(x, y);
    }
    
    public Color getKolorea() {
        return Color.BLUE;
    }
    
    @Override
    public void setTiroMota(int pMota) {
        if (pMota == 1) {
        	JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Blue ezin du gezirik bota!");
        }else {
        	setTiro(pMota);
        }
        
    }
}