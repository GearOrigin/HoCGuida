package info.gear.hocguide;

import java.util.ArrayList;
import java.util.Comparator;

public class Carta implements Comparator<Carta>
{
	//DATI
    private String nome;
    private int tipo;
    private int stelle;
    private int mana;
    private String skills;
    private int immagine;
    private int hp1;
    private int atk1;
    private int hp2;
    private int atk2;
    private int hp3;
    private int atk3;
    private int hp4;
    private int atk4;
    private ArrayList<Combo> Combos=new ArrayList<Combo>();

    //COSTRUTTORE
    public Carta(String nome, int tipo,int stelle,int mana,String skills,int immagine,
                 int hp1,int atk1,int hp2,int atk2,int hp3,int atk3,int hp4,int atk4,ArrayList<Combo> idcombos)
    {
        this.setNome(nome);
        this.setTipo(tipo);
        this.setStelle(stelle);
        this.setMana(mana);
        this.setSkills(skills);
        this.setImmagine(immagine);
        this.setHp1(hp1);
        this.setAtk1(atk1);
        this.setHp2(hp2);
        this.setAtk2(atk2);
        this.setHp3(hp3);
        this.setAtk3(atk3);
        this.setHp4(hp4);
        this.setAtk4(atk4);
        this.setCombos(idcombos);
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getImmagine() {
        return immagine;
    }

    public void setImmagine(int immagine) {
        this.immagine = immagine;
    }

    public int getHp1() {
        return hp1;
    }

    public void setHp1(int hp1) {
        this.hp1 = hp1;
    }

    public int getAtk1() {
        return atk1;
    }

    public void setAtk1(int atk1) {
        this.atk1 = atk1;
    }

    public int getHp2() {
        return hp2;
    }

    public void setHp2(int hp2) {
        this.hp2 = hp2;
    }

    public int getAtk2() {
        return atk2;
    }

    public void setAtk2(int atk2) {
        this.atk2 = atk2;
    }

    public int getHp3() {
        return hp3;
    }

    public void setHp3(int hp3) {
        this.hp3 = hp3;
    }

    public int getAtk3() {
        return atk3;
    }

    public void setAtk3(int atk3) {
        this.atk3 = atk3;
    }

    public int getHp4() {
        return hp4;
    }

    public void setHp4(int hp4) {
        this.hp4 = hp4;
    }

    public int getAtk4() {
        return atk4;
    }

    public void setAtk4(int atk4) {
        this.atk4 = atk4;
    }

    public ArrayList<Combo> getCombos() {
        return Combos;
    }

    public void setCombo(Combo combos) {
        Combos.add(combos);
    }

    public void setCombos(ArrayList combos) { Combos=combos;}

	
	public int compareToC(Carta another) {
			if(stelle > another.getStelle()) return 1;
			else if(stelle < another.getStelle()) return -1;
			else return 0;
	}
	public int compareToD(Carta another) {
		if(stelle < another.getStelle()) return 1;
		else if(stelle > another.getStelle()) return -1;
		else return 0;
}

	public int bestAtk() {
		int max=0;
		if (this.atk1<atk2) max=atk2;
		else max=atk1;
		if (max<atk3)	max=atk3;
		if (max<atk4)	max=atk4;
		
		return max;
	}
	public int bestHp() {
		int max=0;
		if (this.hp1<hp2) max=hp2;
		else max=hp1;
		if (max<hp3)	max=hp3;
		if (max<hp4)	max=hp4;
		
		return max;
	}
	@Override
	public int compare(Carta lhs, Carta rhs) {
		// TODO Auto-generated method stub
		return 0;
	}
}