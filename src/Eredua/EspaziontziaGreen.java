package Eredua;

import java.awt.Color;

public class EspaziontziaGreen extends Espaziontzia {
    public EspaziontziaGreen(int x, int y) {
        super(x, y);
    }

    public String getKolorea() {
        return "berdea";
    }
    
    @Override
    public void portaeraAldatu(int pMota) {
        if (pMota== 2) {
        	JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Green ezin du erronborik bota!");
        }else {
        	setTiroPortaera(pMota);
        }
    }
}