package oop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Algus extends Application {


    public void start(Stage peaLava) throws Exception {
        GridPane gridPane = new GridPane();
        Font font = Font.font("Arial", FontWeight.BOLD, 15);
        Text taane=new Text();
        taane.setText("");
        gridPane.add(taane,0,0);

        Text tekst = new Text();
        tekst.setFont(font);
        tekst.setText(" Vali mängu raskus");
        gridPane.add(tekst,1,1);


        Button nupp = new Button("Mängima");
        nupp.setStyle("-fx-background-color: gray;");
        nupp.setMinSize(40, 40);
        nupp.setFont(font);
        gridPane.add(nupp, 10, 10);

        Slider slider = new Slider(1, 5, 2);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        gridPane.add(slider, 1, 2);


        Scene scene = new Scene(gridPane, 250, 150);
        peaLava.setScene(scene);  // lavale lisatakse stseen
        peaLava.show();  // lava tehakse nähtavaks

        nupp.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                int raskusaste = (int) slider.getValue();
                System.out.println(raskusaste);
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}


