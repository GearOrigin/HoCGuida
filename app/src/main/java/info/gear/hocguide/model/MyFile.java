package info.gear.hocguide.model;


import info.gear.hocguide.Carta;
import info.gear.hocguide.Combo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.util.Log;

public class MyFile {
	
	private ArrayList<Carta> databaseCarte;
	private ArrayList<Combo> databaseCombo=new ArrayList<Combo>();
	private Integer[] databaseImmagini=new Integer[200];
	
public MyFile(Reader reader, Integer[] databaseImmagini) throws IllegalArgumentException
{
	BufferedReader buffer=new BufferedReader(reader);
	this.databaseImmagini=databaseImmagini;
	databaseCarte=new ArrayList<Carta>();
    String[] comboString=new String[4];
	
	
	try
	{
		String line=null;
		
		while((line=buffer.readLine())!=null)
		{
			StringTokenizer token=new StringTokenizer(line,";");
			
			String nome=token.nextToken().trim();
			String tipoString=token.nextToken().trim();
			
			
			int tipo=0;
			if(tipoString.charAt(0)=='D')
			{
				tipo=info.gear.hocguide.R.drawable.druidlogonew;
			}
			else if (tipoString.charAt(0)=='C')
			{
				tipo=info.gear.hocguide.R.drawable.camelotlogonew;
			}
			else if (tipoString.charAt(0)=='X')
            {
                tipo=info.gear.hocguide.R.drawable.demonlogo;
            }
            else System.out.println("errore nel tipo di carta riga n:");

			int stelle=0;
            int mana=0;
			try {
                stelle = Integer.parseInt(token.nextToken().trim());
                mana = Integer.parseInt(token.nextToken().trim());
            }catch(NumberFormatException e){System.out.println("errore nelle stelle/mana");}
			String skill=token.nextToken().trim();

            int position=0;
            try {
                position = Integer.parseInt(token.nextToken().trim());
            }catch(NumberFormatException e){System.out.println("errore nella posizione");}

            int immagineGiusta=this.databaseImmagini[position];
			
			int hp1=Integer.parseInt(token.nextToken().trim());
			int atk1=Integer.parseInt(token.nextToken().trim());
			int hp2=Integer.parseInt(token.nextToken().trim());
			int atk2=Integer.parseInt(token.nextToken().trim());
			int hp3=Integer.parseInt(token.nextToken().trim());
			int atk3=Integer.parseInt(token.nextToken().trim());
			int hp4=Integer.parseInt(token.nextToken().trim());
			int atk4=Integer.parseInt(token.nextToken().trim());
			
				
			//RICORDATI SETTATO A NULL PERCHè MANCANO COMBO
			Log.i("System.out","Carta:"+nome+"   immagine giusta:"+immagineGiusta);
			Carta cartaFinal=new Carta(nome, tipo, stelle, mana, skill, immagineGiusta, hp1, atk1, hp2, atk2, hp3, atk3, hp4, atk4, new ArrayList<Combo>());
			
			databaseCarte.add(cartaFinal);
			
		}
	}catch(IOException e)
	{
		throw new IllegalArgumentException();
	}
		}



private ArrayList<Combo> dataBasecomboGenerator(ArrayList<Combo> databaseCombo, Integer[] idCombo) {
	
	ArrayList<Combo> result=new ArrayList<Combo>();
	this.databaseCombo=databaseCombo;
	
	 int count=0;
		  
		  for(Combo c:databaseCombo)
		  {
			  if(idCombo[count]==c.getId())
			  {
				  result.add(c);
			  }
		  }	
	
		
	return result;
	
}

public ArrayList<Carta> getDatabaseCarte()
{
	return databaseCarte;
	
}



}