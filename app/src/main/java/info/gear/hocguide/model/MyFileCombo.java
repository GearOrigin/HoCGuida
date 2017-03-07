package info.gear.hocguide.model;


import info.gear.hocguide.Carta;
import info.gear.hocguide.Combo;
import info.gear.hocguide.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.util.Log;

public class MyFileCombo 
{
	private ArrayList<Combo> databaseCombo;
	private ArrayList<Carta> databaseCarta=new ArrayList<Carta>();
	
	private Integer[] databaseImmagini=new Integer[200];
	public MyFileCombo(Reader reader, ArrayList<Carta> databaseCarta,Integer[] databaseImmagini)
	{
		BufferedReader buffer=new BufferedReader(reader);
		this.databaseImmagini=databaseImmagini;
		this.databaseCarta=databaseCarta;
		
		databaseCombo=new ArrayList<Combo>();
		
		try{
			String line=null;
			line=buffer.readLine();
			
			while(line!=null)
			{
				StringTokenizer token=new StringTokenizer(line,";");
				
				
				int id=Integer.parseInt(token.nextToken().trim());
				String nome=token.nextToken().trim();
				String effect=token.nextToken().trim();
				
		
				String listaCarte=token.nextToken().trim();
				
				String[] comboArr=new String[10];
				
				comboArr=listaCarte.split(",");
				
				//DEVO RECUPERARE IL CODICE DELL'IMMAGINE SULLA CARTA PERO DOVREI REINVOCARE LA FUNZIONE DI MYFILE CHE RILEGGE IL DATABASE
				
			
			  		  
				ArrayList<Carta> numCartCombo=new ArrayList<Carta>();
				
				for(int i=0;i<comboArr.length;i++)
				{
					for(Carta c:databaseCarta)
				    {
						if(comboArr[i].equalsIgnoreCase(c.getNome()))
				    	{
							
							numCartCombo.add(c);
							
				    	}
				    }
				}
				
				
				Combo comb=new Combo(id,nome,effect,numCartCombo);
				//Log.i("System.out", "Combo:"+id+"  effetto "+effect+"   numcartaCombosize "+numCartCombo.size());
				databaseCombo.add(comb);
				line=buffer.readLine();
				
			}
		}catch(IOException e){}
	}

public ArrayList<Combo> getDatabaseCombo()
{
return databaseCombo;
}




}
