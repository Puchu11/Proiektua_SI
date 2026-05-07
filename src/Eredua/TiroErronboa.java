package Eredua;

public class TiroErronboa implements TiroPortaera {

    public void tiroEgin(int x, int y) {

        TiroNodoa erronboa = new TiroNodoa();

        int[][] posizioak = {
            {0, -5},
            {-1, -4}, {0, -4}, {1, -4},
            {-2, -3}, {-1, -3}, {0, -3}, {1, -3}, {2, -3},
            {-1, -2}, {0, -2}, {1, -2},
            {0, -1}
        };

        for (int[] pos : posizioak) {
            erronboa.gehituTiroa(new Tiro(x + pos[0], y + pos[1],"gora","jokalaria"));
        }

        erronboa.start();
    }
}
