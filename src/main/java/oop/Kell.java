package oop;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Kell {
    private boolean algus = false;

    //kella käivitamine ja seiskamine
    public void startstopp(Node node){
        Timeline aeg = new Timeline(new KeyFrame(Duration.seconds(0),event -> uusaeg(node)), new KeyFrame(Duration.seconds(1)));
        aeg.setCycleCount(Animation.INDEFINITE);
        if (algus){
            aeg.stop();
            return;
        }
        aeg.play();
        algus = !algus;

    }

    //kella välja uuendamine
    public void uusaeg(Node node){
        Text kell = (Text)node;
        int aeg = Integer.parseInt(kell.getText())+1;
        kell.setText(Integer.toString(aeg));
    }


}
