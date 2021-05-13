package oop;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

public class Memory extends Application {
    private int raskusaste;

    @Override
    public void start(Stage peaLava) {
        peaLava.setTitle("Memory");
        peaLava.setScene(algus(peaLava));
        peaLava.show();
    }

    //rakenduse käivitamisel esimene lehekülg
    //sellel valitakse sobiv raskusaste ning alustatakse mängu
    public Scene algus(Stage peaLava) {
        GridPane gridPane = new GridPane();
        Font font = Font.font("Arial", FontWeight.BOLD, 15);
        Text taane = new Text();
        taane.setText("");
        gridPane.add(taane, 0, 0);

        Text tekst = new Text();
        tekst.setFont(font);
        tekst.setText(" Vali mängu raskus");
        gridPane.add(tekst, 1, 1);

        //nupp et alustada mängu
        Button nupp = new Button("Mängima");
        nupp.setStyle("-fx-background-color: gray;");
        nupp.setMinSize(40, 40);
        nupp.setFont(font);
        gridPane.add(nupp, 10, 10);

        //lohistatav nupp raskusastme valikuks
        Slider slider = new Slider(1, 5, 2);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setSnapToTicks(true);
        gridPane.add(slider, 1, 2);


        Scene scene = new Scene(gridPane, 250, 150, Color.LIGHTSKYBLUE);

        //kui vajutatakse nuppu "Alusta mängu"
        //siis minnakse edasi mängulaua juurde
        nupp.setOnAction(e -> {
            raskusaste = (int) slider.getValue();
            try {
                peaLava.setScene(mäng(peaLava));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        return scene;

    }


    //leht millel toimub mängimine
    //lehele luuakse mängulaud vastavalt valitud raskusastmele
    public Scene mäng(Stage peaLava) {
        int suurus = raskusaste * 2;
        Mängulaud laud = new Mängulaud(suurus);
        GridPane gridPane = new GridPane();
        Kontroll kontroll = new Kontroll(laud, suurus);
        BorderPane border = new BorderPane();
        VBox vasak = new VBox();
        Font font = Font.font("Arial", FontWeight.BOLD, 15);

        //kell, mis näitab reaalajas mängule kulunud aega
        Text kell = new Text("0");
        Text pealkiri = new Text("Kulunud aeg:");
        pealkiri.setFont(font);
        vasak.getChildren().addAll(pealkiri, kell);
        Kell aeg = new Kell();
        aeg.startstopp(kell);


        for (int i = 0; i < suurus; i++) {
            for (int j = 0; j < suurus; j++) {
                PauseTransition wait = new PauseTransition(Duration.seconds(3));
                Button nupp = new Button(laud.getElement(new int[]{i, j}));

                nupp.setStyle("-fx-background-color: gray;");
                nupp.setMinSize(40, 40);
                nupp.setFont(font);

                gridPane.add(nupp, i, j);
                wait.setOnFinished(event -> nupp.setText(""));
                wait.play();

                int veerg = GridPane.getColumnIndex(nupp);
                int rida = GridPane.getRowIndex(nupp);

                nupp.setOnMouseClicked(event -> {
                    try {
                        kontroll.lisa(nupp, rida, veerg);
                    } catch (InterruptedException g) {
                        g.printStackTrace();
                        System.out.println("viga");
                    } catch (Valmis e) {
                        aeg.startstopp(kell);
                        try {
                            peaLava.setScene(nimesisestus(peaLava, kell.getText()));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                });
            }
        }
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 50, 0, 50));


        border.setPadding(new Insets(50, 50, 50, 50));
        border.setLeft(vasak);
        border.setCenter(gridPane);

        return new Scene(border, suurus * (45) + 400, suurus * (45) + 150);

    }

    //leht, millel peale mängu edukat sooritamist sisestatakse kasutajanimi,
    //et saadud tulemus lisada edetabelisse
    public Scene nimesisestus(Stage peaLava, String aeg) {
        GridPane gridPane = new GridPane();
        Font font = Font.font("Arial", FontWeight.BOLD, 15);

        //kasutajale näidatakse sooritatud tulemus
        Text tekst = new Text("Sinu aeg oli: " + aeg);
        tekst.setFont(font);
        gridPane.add(tekst, 1, 0);

        //sisestusväli nime sisestamiseks
        Label label1 = new Label("Sisesta oma nimi:");
        label1.setFont(font);
        TextField textField = new TextField();

        //nupp, et kinnitada sisestatud nimi
        Button sisesta = new Button("Sisesta");
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, sisesta);
        hb.setSpacing(20);
        gridPane.add(hb, 1, 1);

        //kui kasutaja vajutab nuppu "sisesta",
        // liigub programm edetabeli lehe juurde
        sisesta.setOnAction(event -> {
            try {
                //programm kirjutab saadud tulemuse faili edetabel.txt
                Edetabel.kirjutaFaili(textField.getText(), raskusaste, Integer.parseInt(aeg));
                peaLava.setScene(edetabel(peaLava));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return new Scene(gridPane, 400, 100);
    }

    //leht, millel kasutajale näidatakse eelnevaid tulemusi
    //ning kasutaja saab valida, kas mängida edasi või lõpetada mäng
    public Scene edetabel(Stage peaLava) throws Exception {

        //programm loeb eelnevad tulemused failist sisse
        List<String> tulemused = Edetabel.loefailist();
        GridPane gridPane = new GridPane();
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        Font fontTulemused = Font.font("Arial", FontWeight.NORMAL, 15);

        Text tekst = new Text();
        tekst.setFont(font);
        tekst.setText("Eelnevad tulemused:");
        gridPane.add(tekst, 1, 1);

        //kasutajale näidatakse maksimaalselt 20 varasemat tulemust
        int arv = Math.min(tulemused.size(), 20);
        //tulemused väljastatakse ekraanile
        for (int i = 0; i < arv; i++) {
            Text tulemus = new Text();
            tulemus.setFont(fontTulemused);
            tulemus.setText(tulemused.get(i));
            gridPane.add(tulemus, 1, i + 2);

        }

        //nupp, et lõpetada mängimine
        Button nupp = new Button("Lõpp");
        nupp.setStyle("-fx-background-color: gray;");
        nupp.setMinSize(40, 40);
        nupp.setFont(font);
        gridPane.add(nupp, 2, tulemused.size() + 3);

        //nupp, et mängida uuesti
        Button nupp1 = new Button("Mängin uuesti");
        nupp1.setStyle("-fx-background-color: gray;");
        nupp1.setMinSize(40, 40);
        nupp1.setFont(font);
        gridPane.add(nupp1, 1, tulemused.size() + 3);

        Scene scene = new Scene(gridPane, 400, 300);

        //Lõpeta vajutamisel programm sulgub
        nupp.setOnAction(e -> Platform.exit());
        //Mängin uuesti vajutamisel minnakse programmi algusesse tagasi
        nupp1.setOnAction(e -> {
            try {
                peaLava.setScene(algus(peaLava));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        return scene;

    }


    public static void main(String[] args) {
        launch(args);
    }
}


