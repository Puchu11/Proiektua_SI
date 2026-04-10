package Eredua;

import java.awt.Color;

public class EspaziontziaGreen extends Espaziontzia {
    public EspaziontziaGreen(int x, int y) {
        super(x, y);
    }

    public Color getKolorea() {
        return Color.GREEN;
    }
    
    @Override
    public void setTiroMota(int pMota) {
        if (pMota== 2) {
        	JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Green ezin du erronborik bota!");
        }else {
        	setTiro(pMota);
        	
        }
    }
}