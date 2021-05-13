package oop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Edetabel {

    //Failist edetabeli lugemiseks
    public static List<String> loefailist() throws IOException {
        List<String> tulemused = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("src/main/java/oop/edetabel.txt"), StandardCharsets.UTF_8)) {
            while (sc.hasNext()) {
                tulemused.add(sc.nextLine());
            }
            return tulemused;
        }
    }

    //Edetabeli salvestamiseks faili
    public static void kirjutaFaili(String nimi, int raskusaste, int tulemus) throws IOException {
        String lisa = "Raskusaste: " + raskusaste + ", nimi: " + nimi + ", tulemus: " + tulemus;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/oop/edetabel.txt", true));
        writer.append("\n");
        writer.append(lisa);
        writer.close();
    }
}
