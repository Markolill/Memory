package oop;
import java.util.ArrayList;
import java.util.Collections;


public class Mängulaud {
    private static String tähed = "abcdefghijklmonp"; //tähed mida kasutame
    private static String[][] mängulaud;


    public static String getElement(int[] asukoht){
        return mängulaud[asukoht[0]][asukoht[1]];
    }

    public Mängulaud(int suurus) {
        genereeri(suurus);
    }

    public static void genereeri(int suurus){ //tuleb enne vaadata kas paaris arv, sest muidu ei saa paare

        ArrayList<String> paarid = new ArrayList<>();
        int random_int;


        for (int i = 0; i < suurus*suurus/2; i++) {  //teeb paarid
            random_int = (int)(Math.random()*tähed.length()+1);
            paarid.add(tähed.substring(random_int-1,random_int));
            paarid.add(tähed.substring(random_int-1,random_int));
        }
        Collections.shuffle(paarid); //segab paarid ära

        String[][] valmis = new String[suurus][suurus];
        for (int i = 0, a = 0; i < suurus; i++) { //jaotab maatriksisse
            for (int j = 0; j < suurus; j++, a++) {
                valmis[i][j] = paarid.get(a);
            }
        }
        mängulaud = valmis; //genereeri

    }


    public static void mängulaud(int rida,int veerg,int rida2, int veerg2,String element){
        mängulaud[rida][veerg]=element;
        mängulaud[rida2][veerg2]=element;
    }

}
