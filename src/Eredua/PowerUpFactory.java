package Eredua;

import java.util.Arrays;
import java.util.List;
//PowerUp motak definitzeko klase lagungarria

public class PowerUpFactory {
    public static List<PowerUp> lortuPowerUpGuztiak() {
        return Arrays.asList(
            esp -> esp.setTiroPortaera(1),
            esp -> esp.setTiroPortaera(2),
            esp -> JokoKudeatzailea.getNireJokoKudeatzailea().puntuazioaGehitu(500),
            esp -> JokoKudeatzailea.getNireJokoKudeatzailea().bizitzaBatGehitu(),
            esp -> esp.setGeziMunizioa(esp.getGeziMunizioa() + 20),
            esp -> esp.setErronboMunizioa(esp.getErronboMunizioa() + 10),
            esp -> {
                esp.setBabesa(true);
                JokoKudeatzailea.getNireJokoKudeatzailea().mezuaErakutsi("Babeslea aktibatuta!");
            }
        );
    }
}