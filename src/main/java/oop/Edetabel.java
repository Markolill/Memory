package oop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Edetabel extends Application {

    //Failist edetabeli lugemiseks
    public static List<String> loefailist() throws IOException {
        List<String> tulemused = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("C:\\Users\\Aleksander\\Desktop\\Memory\\src\\main\\java\\oop\\edetabel.txt"), StandardCharsets.UTF_8)) {
            while (sc.hasNext()) {
                tulemused.add(sc.nextLine());
            }
            return tulemused;
        }
    }

    //Edetabeli salvestamiseks faili
    public static void kirjutaFaili(String nimi, int raskusaste, int tulemus) throws IOException {
        String lisa = "Raskusaste: " + raskusaste + ", nimi: " + nimi + ", tulemus: " + tulemus;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\edetabel.txt", true));
        writer.append("\n");
        writer.append(lisa);
        writer.close();
    }

    public void start(Stage peaLava) throws Exception {

        List<String> tulemused= loefailist();
        GridPane gridPane = new GridPane();
        Font font = Font.font("Arial", FontWeight.BOLD, 15);
        Font fontTulemused = Font.font("Arial", FontWeight.NORMAL, 10);

        Text tekst = new Text();
        tekst.setFont(font);
        tekst.setText("Eelnevad tulemused:");
        gridPane.add(tekst,1,1);
        int arv;
        if (tulemused.size()>20)  arv=20;
        else arv=tulemused.size();
        for (int i = 0; i < arv; i++) {
            Text tulemus = new Text();
            tulemus.setFont(fontTulemused);
            tulemus.setText(tulemused.get(i));
            gridPane.add(tulemus,1,i+2);

        }


        Button nupp = new Button("Lõpp");
        nupp.setStyle("-fx-background-color: gray;");
        nupp.setMinSize(40, 40);
        nupp.setFont(font);
        gridPane.add(nupp, 2, tulemused.size()+3);

        Button nupp1 = new Button("Mängin uuesti");
        nupp1.setStyle("-fx-background-color: gray;");
        nupp1.setMinSize(40, 40);
        nupp1.setFont(font);
        gridPane.add(nupp1, 1, tulemused.size()+3);


        Scene scene = new Scene(gridPane, 250, 300);
        peaLava.setScene(scene);  // lavale lisatakse stseen
        peaLava.show();  // lava tehakse nähtavaks

        nupp.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(1);
            }
        });
        nupp1.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(1);
            }
        });

    }
    public static void main(String[] args) {
        launch(args);
    }
}
