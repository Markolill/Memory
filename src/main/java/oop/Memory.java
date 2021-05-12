package oop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

public class Memory extends Application {

  @Override
  public void start(Stage peaLava) throws Exception {
    GridPane gridPane = new GridPane();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        ToggleButton nupp = new ToggleButton("x");
        nupp.setMinSize(30,30);
        //nupp.setOnMouseClicked(event -> nupp.);


        gridPane.add(nupp,i,j);

      }

    }
    System.out.println(Arrays.toString(gridPane.getChildren().toArray()));


    //gridPane.add(button1, 0, 0, 1, 1);
    //gridPane.add(button2, 1, 0, 1, 1);
    //gridPane.add(button3, 2, 0, 1, 1);
    //gridPane.add(button4, 0, 1, 1, 1);
    //gridPane.add(button5, 1, 1, 1, 1);
    //gridPane.add(button6, 2, 1, 1, 1);
    //gridPane.add(kast, 3, 3, 1, 1);

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
