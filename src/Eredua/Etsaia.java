package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Etsaia extends Entitatea {

    private int indizea;


    Etsaia(int x, int y, int indizea) {
        super(x, y);
        this.indizea = indizea;
    }

    public int getIndizea() {
        return indizea;
    }

    @Override
    public boolean mugituDaiteke(String norabidea) {

        int posX = this.getPosizioa().getX();
        int posY = this.getPosizioa().getY();

        int xBerria = posX;
        int yBerria = posY;

        switch (norabidea) {
            case "gora" -> yBerria--;
            case "behera" -> yBerria++;
            case "ezkerrera" -> xBerria--;
            case "eskuinera" -> xBerria++;
            default -> {
                return false;
            }
        }

        if (xBerria < 0 || xBerria >= 100 || yBerria < 0 || yBerria >= 60) {
            return false;
        }

        final int xFinal = xBerria;
        final int yFinal = yBerria;

        List<EntitateNodo> etsaiakKopia =
                new ArrayList<>(MatrizeE.getEma().getEtsaiak());

        return !etsaiakKopia.stream()
                .flatMap(nodo -> new ArrayList<>(nodo.getLista()).stream())
                .filter(e -> e instanceof Etsaia)
                .map(e -> (Etsaia) e)
                .anyMatch(etsaia ->
                        etsaia != this &&
                        etsaia.getIndizea() != this.getIndizea() &&
                        etsaia.getPosizioa().getX() == xFinal &&
                        etsaia.getPosizioa().getY() == yFinal
                );
    }

}