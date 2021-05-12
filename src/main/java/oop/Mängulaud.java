package oop;
import java.util.ArrayList;
import java.util.Collections;


public class Mängulaud {
    private String tähed = "abcdefghijklmonp"; //tähed mida kasutame
    private String[][] mängulaud;


    public String getElement(int[] asukoht){
        return mängulaud[asukoht[0]][asukoht[1]];
    }

    public Mängulaud(int suurus) {
        genereeri(suurus);
    }

    public void genereeri(int suurus){ //tuleb enne vaadata kas paaris arv, sest muidu ei saa paare

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

}
