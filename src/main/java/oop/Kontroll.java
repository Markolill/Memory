package oop;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Kontroll {

    private List<Node> paar = new ArrayList<>();
    private List<int[]> asukohad = new ArrayList<>();
    private Mängulaud laud;
    private int õigeid;
    private int suurus;

    //konstruktor mängu loomiseks
    public Kontroll(Mängulaud laud, int suurus) {
        õigeid = 0;
        this.laud = laud;
        this.suurus = suurus;

    }

    //Kui kasutaja vajutab nuppu lisatakse nupp koos asukoha parameetriga järjendisse
    public void lisa(Node nupp, int rida, int veerg) throws InterruptedException, Valmis {
        int[] asukoht = {rida, veerg};
        asukohad.add(asukoht);
        paar.add(nupp);
        Button a = (Button) nupp;
        a.setText(laud.getElement(asukoht));
        a.setStyle("-fx-background-color: blue;");

        //kui järjendis on kaks nuppu, siis kontrollitakse, kas valik on korrektne
        if (paar.size() >= 2) {
            if (laud.getElement(asukohad.get(0)).equals(laud.getElement(asukohad.get(1)))) {
                õigeid += 1;
                if (õigeid == ((suurus/2)*(suurus/2))*2) { //kui kõik vastused on leitud visatakse Valmis(), mis suunab mängu edasi.
                    throw new Valmis();
                }
                for (int i = 0; i < 2; i++) {
                    Button b = (Button) paar.get(i);
                    b.setStyle("-fx-background-color: green;");
                    b.setDisable(true);
                }
            } else { //kui paar ei ole õige värvitakse ruudud punaseks oodatakse 1 sekund ja nupud taastavad algse vormingu.
                for (int i = 0; i < 2; i++) {
                    Button b = (Button) paar.get(i);
                    b.setStyle("-fx-background-color: red;");
                }

                for (int i = 0; i < 2; i++) {
                    Button b = (Button) paar.get(i);
                    PauseTransition wait = new PauseTransition(Duration.seconds(1));
                    wait.setOnFinished(event -> {
                        b.setText("");
                        b.setStyle("-fx-background-color: gray;");
                    });
                    wait.play();
                }

            }
            paar.clear();
            asukohad.clear();
        }
    }
}
