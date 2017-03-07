package info.gear.hocguide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import android.app.Activity;
import android.app.Dialog;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import info.gear.hocguide.Carta;
import info.gear.hocguide.R;

public class ViewHolderAdapter2 extends ArrayAdapter<Carta> {

//DATI
	private final Activity context;
	private Carta[] carte;
	private Carta selezionata;
	private boolean lingua=false;
	
	public ViewHolderAdapter2(Activity context,Carta[] carte,boolean lingua)
	{
		 super(context, R.layout.simplerow,carte);
		 	this.lingua=lingua;
	        this.context = context;
	        this.carte=carte;
	}

	static class ViewHolder {
        TextView txtTitle;
        ImageView imageView;
        ImageView tipoView;
   }
	
	public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        selezionata=this.carte[position];
        if (convertView == null) {
        	LayoutInflater inflater = context.getLayoutInflater();
            convertView= inflater.inflate(R.layout.simplerowlist, null, true);

              holder = new ViewHolder();
              holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewCarta);
              holder.imageView = (ImageView) convertView.findViewById(R.id.immagineCarta);
              holder.tipoView = (ImageView) convertView.findViewById(R.id.tipoCartaView);
              convertView.setTag(holder);
              holder.imageView.setOnClickListener(new View.OnClickListener() {
      			
      			@Override
      			public void onClick(View v) {
      				
      				Dialog dialog = new Dialog(v.getContext());
      			    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      			    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      			    // layout to display
      			    dialog.setContentView(R.layout.dialog_layout);
      			    
      			    // set color transpartent
      			    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      			    ImageView image=(ImageView) dialog.findViewById(R.id.dialog);
      			    image.setImageDrawable(v.getResources().getDrawable((Integer) v.getTag()));
      			    dialog.show();
      			}
      		});
              holder.txtTitle.setOnClickListener(new View.OnClickListener() {
      			
      			public void onClick(View v) 
      			{
      				Dialog dialog = new Dialog(v.getContext());
      			    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      			    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      			    // layout to display
      			    dialog.setContentView(R.layout.dialog_layout_carte);
      			    Typeface custom_font = Typeface.createFromAsset(v.getResources().getAssets(),
      		  	      	      "fonts/CELTICHD.TTF");
      			    // set color transpartent
      			    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      			    
      			
      			    
      			    ImageView immagine= (ImageView) dialog.findViewById(R.id.imageStatCarta);
      			    Carta c=(Carta) v.getTag();
      			    immagine.setImageDrawable(v.getResources().getDrawable(c.getImmagine()));
      			    immagine.setTag(c.getImmagine());
      			    immagine.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) 
						{
							Dialog dialog = new Dialog(v.getContext());
		      			    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		      			    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		      			    // layout to display
		      			    dialog.setContentView(R.layout.dialog_layout);
		      			    
		      			    // set color transpartent
		      			    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		      			    ImageView image=(ImageView) dialog.findViewById(R.id.dialog);
		      			    image.setImageDrawable(v.getResources().getDrawable((Integer) v.getTag()));
		      			    dialog.show();
							
						}
					});
      			 
      			    
      			    //SETTA IL BACKGROUND COL TIPO
      			    //dialog.getWindow().setBackgroundDrawable(v.getResources().getDrawable(c.getTipo()));
    			    ImageView immagineTipo=(ImageView) dialog.findViewById(R.id.imagetipo);
    			    immagineTipo.setImageDrawable(v.getResources().getDrawable(c.getTipo()));
      			    
      			    
      			    TextView statistiche=(TextView) dialog.findViewById(R.id.testoStatCarta);
      			    statistiche.setTypeface(custom_font);
      			    String testo=(lingua==true) ?
      			    		"\n\t\t\t\t"+
      			    		"Stars:  "+c.getStelle()+
      			    		"\n\t\t\t\t"+
      			    		"Mana:  "+c.getMana():
      			    			"\n\t\t\t\t"+
      			    		"Stelle:  "+c.getStelle()+
          			    	"\n\t\t\t\t"+
      			    		"Mana:  "+c.getMana();
      			    statistiche.setText(testo);
      			    //statistiche.setTextColor(Color.WHITE);
      			    statistiche.setTextSize(25);
      			   
      			    // statistiche.setBackground(v.getResources().getDrawable(c.getTipo()));
      			    
      			    TextView tiers=(TextView) dialog.findViewById(R.id.tierStatCarta);
      			    tiers.setMovementMethod(new ScrollingMovementMethod());
      			    String testo2=(lingua==false) ?
      			    		"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
      			    		c.getNome()+"\n"+
      			    		"\nEffetto:\n"+c.getSkills() +"\n\n\n"+
      			    	
      			    		"EVO 1\n\nHp: "+c.getHp1()+"\t\tAttacco: "+c.getAtk1()+"\n\n"+
          			    	"EVO 2\n\nHp: "+c.getHp2()+"\t\tAttacco: "+c.getAtk2()+"\n\n"+
          			    	"EVO 3:4 CARTE\n\nHp: "+c.getHp3()+"\t\tAttacco: "+c.getAtk3()+"\n\n"+
          			    	"EVO 4:8 CARTE\n\nHp: "+c.getHp4()+"\t\tAttacco: "+c.getAtk4()+"\n\n"+
                            "COMBO:\n\n" + calcolaCombo(c.getCombos())
                            :
          			    		"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
          			    	c.getNome()+"\n"+
          			    		"\nSkill:\n"+c.getSkills()+"\n\n\n"+
          			    		
          			    		"TIER1\n\nHp: "+c.getHp1()+"\t\tAtk: "+c.getAtk1()+"\n\n"+
          			    		"TIER2\n\nHp: "+c.getHp2()+"\t\tAtk: "+c.getAtk2()+"\n\n"+
          			    		"TIER3:4 CARDS\n\nHp: "+c.getHp3()+"\t\tAtk: "+c.getAtk3()+"\n\n"+
          			    		"TIER4:8 CARDS\n\nHp: "+c.getHp4()+"\t\tAtk: "+c.getAtk4()+"\n\n"+
                                "COMBO:\n\n"+calcolaCombo(c.getCombos());


      			    tiers.setText(testo2);
      			    
      			    
      			    if ((c.getTipo())==R.drawable.druidlogonew){	statistiche.setTextColor(Color.YELLOW); tiers.setTextColor(Color.GREEN);}
      			    else if ((c.getTipo())==R.drawable.camelotlogonew){	statistiche.setTextColor(Color.RED); tiers.setTextColor(Color.WHITE);}
      			    else{ statistiche.setTextColor(Color.WHITE); tiers.setTextColor(Color.RED);}
      	   		       tiers.setTextSize(24);
      	   		       tiers.setTypeface(custom_font);
      			   
      			    dialog.show(); 
      				
      			}
      		});
              
        } else {
              holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(selezionata.getNome());
        holder.txtTitle.setTag(selezionata);
        holder.imageView.setImageResource(selezionata.getImmagine());
        holder.imageView.setTag(selezionata.getImmagine());
        holder.tipoView.setImageResource(selezionata.getTipo());
        
        return convertView;
  }


    public String calcolaCombo(ArrayList<Combo> combo)
    {
        StringBuilder sb=new StringBuilder();

        for(Combo c : combo)
        {
           sb.append(c.getNome()+":"+"\n\n");
            sb.append(c.getDescrizione()+"\n\n");

            for(Carta cart : c.getCarte())
            {
                sb.append(cart.getNome()+",");
            }
            sb.deleteCharAt(sb.length()-1);

            sb.append("\n"+"-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"+"\n\n");

        }

        return sb.toString();
    }



	
	public void notifyDataSetChangedStars(boolean b) {
	    if (b==true)//crescente
	    {
	    	Arrays.sort(carte, new Comparator<Carta>() {
					    public int compare(Carta arg0, Carta arg1) 
					    {
					    	return arg0.compareToC(arg1);
					    }
					});
	    }
	    else
	    {
	    	Arrays.sort(carte, new Comparator<Carta>() {
			    public int compare(Carta arg0, Carta arg1) 
			    {
			    	return arg0.compareToD(arg1);
			    }
			});
	    }
		
	    super.notifyDataSetChanged();
	}


	
	public void notifyDataSetChangedMana(boolean b) {
	    
		if (b==true)
			{
			Arrays.sort(carte, new Comparator<Carta>() {
			
					    public int compare(Carta arg0, Carta arg1) 
					    {
					    		if(arg0.getMana() > arg1.getMana()) return 1;
								else if(arg0.getMana() < arg1.getMana()) return -1;
								else return 0;
					    }
					});
			}
		else
		{
			Arrays.sort(carte, new Comparator<Carta>() {
				
			    public int compare(Carta arg0, Carta arg1) 
			    {
			    		if(arg0.getMana() < arg1.getMana()) return 1;
						else if(arg0.getMana() > arg1.getMana()) return -1;
						else return 0;
			    }
			});
		}
	    super.notifyDataSetChanged();
	}


	public void notifyDataSetChangedHp(boolean b) {
		 
		if (b==true)
			{
			Arrays.sort(carte, new Comparator<Carta>() {
			
					    public int compare(Carta arg0, Carta arg1) 
					    {
					    		if(arg0.bestHp() > arg1.bestHp()) return 1;
								else if(arg0.bestHp() < arg1.bestHp()) return -1;
								else return 0;
					    }
					});
			}
		else
		{
			Arrays.sort(carte, new Comparator<Carta>() {
				
			    public int compare(Carta arg0, Carta arg1) 
			    {
			    		if(arg0.bestHp() < arg1.bestHp()) return 1;
						else if(arg0.bestHp() > arg1.bestHp()) return -1;
						else return 0;
			    }
			});
		}
	    super.notifyDataSetChanged();
		
	}


	public void notifyDataSetChangedAtk(boolean b) {
		 
		if (b==true)
			{
				Arrays.sort(carte, new Comparator<Carta>() {
					    public int compare(Carta arg0, Carta arg1) 
					    {
					    	if(arg0.bestAtk() > arg1.bestAtk()) return 1;
							else if(arg0.bestAtk() < arg1.bestAtk()) return -1;
							else return 0;
					    }
					});
			}
		else
		{
			Arrays.sort(carte, new Comparator<Carta>() {
			    public int compare(Carta arg0, Carta arg1) 
			    {
			    	if(arg0.bestAtk() < arg1.bestAtk()) return 1;
					else if(arg0.bestAtk() > arg1.bestAtk()) return -1;
					else return 0;
			    }
			});
		}
	    super.notifyDataSetChanged();
		
	}



	public void notifyDataSetChangedOrdineAlfa(final boolean b) 
	{
		if (b==true)
		{
			Arrays.sort(carte, new Comparator<Carta>() {
				    public int compare(Carta arg0, Carta arg1) 
				    {
				    	return controlloalfa(b, arg0, arg1);
				    }
				});
		}
	else
	{
		Arrays.sort(carte, new Comparator<Carta>() {
		    public int compare(Carta arg0, Carta arg1) 
		    {
		    	return controlloalfa(b, arg0, arg1);
		    }
		});
	}
    super.notifyDataSetChanged();
		
	}
	public int controlloalfa(boolean b,Carta arg0,Carta arg1)
	{
		int lunghezzaMin=(arg0.getNome().length() > arg1.getNome().length()) ? arg1.getNome().length()-1 : arg0.getNome().length()-1;
		if (b==true)
		{
			
			for (int i=0;i<lunghezzaMin;i++)
			{
				if (arg0.getNome().toLowerCase().charAt(i) > arg1.getNome().toLowerCase().charAt(i))
				{
					return 1;
				}
				else if (arg0.getNome().toLowerCase().charAt(i) < arg1.getNome().toLowerCase().charAt(i))
				{
					return -1;
				}
			}
			return 0;
		}
		else
		{
			for (int i=0;i<lunghezzaMin;i++)
			{
				if (arg0.getNome().toLowerCase().charAt(i) < arg1.getNome().toLowerCase().charAt(i))
				{
					return 1;
				}
				else if (arg0.getNome().toLowerCase().charAt(i) > arg1.getNome().toLowerCase().charAt(i))
				{
					return -1;
				}
			}
			return 0;
		}
		
	}
    private String splittaStringa(String s, int lun) {
        String result="";
        if (s.length()>=lun)
        {
            for (int i=0;i<lun;i++)
            {
                result+=""+s.charAt(i);
            }
        }
        else
        {
            for (int i=0;i<s.length();i++)
            {
                result+=""+s.charAt(i);
            }
        }
        return result;
    }
}

