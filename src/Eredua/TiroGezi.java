package Eredua;

public class TiroGezi implements TiroPortaera {
    public void tiroEgin(int x, int y) {
        TiroNodoa gezia = new TiroNodoa();
        int[][] posizioak = {
            {0, 0},
            {-1, 1},
            {1, 1}
        };
        for (int[] pos : posizioak) {
            gezia.gehituTiroa(new Tiro(x + pos[0], y + pos[1],"gora","jokalaria"));
        }
        gezia.start();
    }
}