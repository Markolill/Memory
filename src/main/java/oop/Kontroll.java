package oop;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kontroll {

    private static List<Node> paar = new ArrayList<>();
    private static List<int[]> asukohad = new ArrayList<>();
    private static Mängulaud laud;

    public Kontroll(Mängulaud laud) {
        this.laud = laud;
    }

    public static void lisa(Node nupp, int rida, int veerg) throws InterruptedException {
        int[] asukoht = {rida, veerg};
        asukohad.add(asukoht);
        paar.add(nupp);
        Button a = (Button)nupp;
        a.setText(laud.getElement(asukoht));
        a.setStyle("-fx-background-color: blue;");

        if (paar.size() >= 2){
            if (laud.getElement(asukohad.get(0)).equals(laud.getElement(asukohad.get(1)))){
                for (int i = 0; i < 2; i++) {
                    Button b = (Button)paar.get(i);
                    b.setStyle("-fx-background-color: green;");
                    b.setDisable(true);
                }
                paar.clear();
                asukohad.clear();
            }
            else {
                for (int i = 0; i < 2; i++) {
                    Button b = (Button)paar.get(i);
                    b.setStyle("-fx-background-color: red;");
                }

                for (int i = 0; i < 2; i++) {
                    Button b = (Button)paar.get(i);
                    PauseTransition wait = new PauseTransition(Duration.seconds(1));
                    wait.setOnFinished(event -> {
                                b.setText("");
                                b.setStyle("-fx-background-color: gray;");
                            });
                    wait.play();
                }

                paar.clear();
                asukohad.clear();
            }
        }
    }
}
