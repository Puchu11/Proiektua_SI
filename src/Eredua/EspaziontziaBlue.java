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
    public void tiroEgin() {
        if (getTiroMota() == 1) {
            System.out.println("Blue ezin du gezirik bota!");
            setTiroMota(0);
        }
        super.tiroEgin();
    }
}