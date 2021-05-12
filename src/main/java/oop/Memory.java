package oop;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class Memory extends Application {

  @Override
  public void start(Stage peaLava) throws Exception {
    int suurus = 2;
    Mängulaud laud = new Mängulaud(suurus);
    GridPane gridPane = new GridPane();
    Kontroll kontroll = new Kontroll(laud);

    Font font = Font.font("Arial", FontWeight.BOLD, 15);

    for (int i = 0; i < suurus; i++) {
      for (int j = 0; j < suurus; j++) {
        PauseTransition wait = new PauseTransition(Duration.seconds(3));
        Button nupp = new Button(laud.getElement(new int[]{i,j}));

        nupp.setStyle("-fx-background-color: gray;");
        nupp.setMinSize(40,40);
        nupp.setFont(font);

        gridPane.add(nupp,i,j);


        wait.setOnFinished(event -> nupp.setText(""));
        wait.play();

        int veerg = GridPane.getColumnIndex(nupp);
        int rida = gridPane.getRowIndex(nupp);

        nupp.setOnMouseClicked(event -> {
          try {
            kontroll.lisa(nupp, rida, veerg);
          } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("viga");
          }
        });
      }
    }
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    Scene scene = new Scene(gridPane, 500, 500);
    peaLava.setScene(scene);  // lavale lisatakse stseen
    peaLava.show();  // lava tehakse nähtavaks
  }

  public static void main(String[] args) {
    launch(args);
  }
}


