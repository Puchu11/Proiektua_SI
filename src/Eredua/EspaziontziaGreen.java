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
    public void tiroEgin() {
        if (getTiroMota() == 2) {
            System.out.println("Green ezin du erronborik bota!");
            setTiroMota(0);
        }
        super.tiroEgin();
    }
}