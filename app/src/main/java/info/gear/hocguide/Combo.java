package info.gear.hocguide;

import java.util.ArrayList;

public class Combo {

//DATI
    private int id;
    private String descrizione;
    private ArrayList<Carta> carte=new ArrayList<Carta>();
    private String nome;

//COSTRUTTORE
public Combo(int id,String nome,String descrizione,ArrayList<Carta> carte)
{
    this.setId(id);
    this.setDescrizione(descrizione);
    this.setCarte(carte);
    this.setNome(nome);
}

    public void setNome(String nome) 
    {
    	this.nome=nome;
    }
    public String getNome()
    {
    	return this.nome;
    }

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<Carta> getCarte() {
        return carte;
    }

    public void setCarte(ArrayList<Carta> carte) {
        this.carte = carte;
    }

	@Override
	public String toString() {
		return ""+nome;
	}
    
}
