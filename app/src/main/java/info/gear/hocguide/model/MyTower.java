package info.gear.hocguide.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by marco on 27/03/2015.
 */
public class MyTower {

    private ArrayList<Tower> databaseTorre;
    private Integer[] databaseImmagini = new Integer[32];

    public MyTower(Reader reader, Integer[] databaseImmagini) throws IllegalArgumentException {
        BufferedReader buffer = new BufferedReader(reader);
        this.databaseImmagini = databaseImmagini;
        databaseTorre = new ArrayList<Tower>();
        try {
            String line = null;

            while ((line = buffer.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, ";");

                int num_piano = Integer.parseInt(token.nextToken().trim());
                String isAChest = token.nextToken().trim();
                String nome = token.nextToken().trim();
                String prezzo=token.nextToken();
                int immagineGiusta = this.databaseImmagini[num_piano];

                String listaRewards = token.nextToken().trim();
                //String[] possibleRewards=new String[10];
                //possibleRewards=listaRewards.split(",");

                Tower torre = new Tower(num_piano, isAChest, nome,prezzo, immagineGiusta, listaRewards);
                //Log.i("System.out","Torre:"+torre.getNum_piano()+" "+torre.isMyFuckingTreasure()+" "+torre.getPrezzo());
                databaseTorre.add(torre);

            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public ArrayList<Tower> getDatabaseTorre() {
        return databaseTorre;

    }
    public Tower[] getDataTorreInArray()
    {
        Tower[] torre=new Tower[databaseTorre.size()];
        int cont=0;
        for(Tower c:databaseTorre)
        {
            torre[cont]=c;
            cont++;
        }
        return torre;
    }
}


