package info.gear.hocguide.model;

/**
 * Created by marco on 27/03/2015.
 */
public class Tower
{
    private int num_piano;
    private String isAChest;
    private String nome;
    private String prezzo;
    private int immagineGiusta;
    private String listaRewards;
    public Tower(int num_piano, String isAChest, String nome,String prezzo, int immagineGiusta, String listaRewards)
    {
        this.num_piano=num_piano;
        this.isAChest=isAChest;
        this.nome=nome;
        this.prezzo=prezzo;
        this.immagineGiusta=immagineGiusta;
        this.listaRewards=listaRewards;
    }

    public boolean isMyFuckingTreasure()
    {
        if (this.isAChest.equalsIgnoreCase("NO")) return false;
        return true;
    }
    public int getNum_piano() {
        return num_piano;
    }

    public String getNome() {
        return nome;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public int getImmagineGiusta() {
        return immagineGiusta;
    }

    public String getListaRewards() {
        return listaRewards;
    }

    @Override
    public String toString() {
        return "Tower{" +
                "num_piano=" + num_piano +
                ", isAChest='" + isAChest + '\'' +
                ", nome='" + nome + '\'' +
                ", prezzo='" + prezzo + '\'' +
                ", immagineGiusta=" + immagineGiusta +
                ", listaRewards='" + listaRewards + '\'' +
                '}';
    }
}
