package info.gear.hocguide;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.sax.RootElement;
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

public class ViewHolderAdapterCombo extends ArrayAdapter<Combo> {

	//DATI
	 private final Activity context;
	 private final Combo[] combos;
	 private ArrayList<Carta> databaseCarta=new ArrayList<Carta>();
	 private boolean lingua=false;
	 private Combo c;
	 
	 public ViewHolderAdapterCombo(Activity context,Combo[] combos,ArrayList<Carta> databaseCarta,boolean lingua) 
	 {
       super(context, R.layout.simplerow2,combos);
       this.context = context;
       this.combos=combos;
       this.databaseCarta=databaseCarta;
       this.lingua=lingua;
       
	 }


		static class ViewHolderCombo {
	        TextView textCombo;
	        ImageView combo1;
	        ImageView combo2;
	        ImageView combo3;
	        ImageView combo4;
	   }
		
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolderCombo holdercombo;
	        
	        if (convertView == null) 
	        {
	        	LayoutInflater inflater = context.getLayoutInflater();
	            convertView= inflater.inflate(R.layout.simplerow2, null, true);
	              holdercombo = new ViewHolderCombo();
	              holdercombo.textCombo = (TextView) convertView.findViewById(R.id.textcombo);
	              holdercombo.combo1 = (ImageView) convertView.findViewById(R.id.combo1);
	              holdercombo.combo2= (ImageView) convertView.findViewById(R.id.combo2);
	              holdercombo.combo3 = (ImageView) convertView.findViewById(R.id.combo3);
	              holdercombo.combo4 = (ImageView) convertView.findViewById(R.id.combo4);
	              convertView.setTag(holdercombo);
	              
	              holdercombo.textCombo.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) 
					{
						Dialog dialogcombo = new Dialog(v.getContext());
					    dialogcombo.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
					    dialogcombo.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
					    // layout to display
					    dialogcombo.setContentView(R.layout.dialog_layout_combo2);
					    Typeface custom_font = Typeface.createFromAsset(v.getResources().getAssets(),
				  	      	      "fonts/CELTICHD.TTF");
					    // set color transpartent
					    dialogcombo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					    Combo ccc=(Combo) v.getTag();
						ImageView immagine1= (ImageView) dialogcombo.findViewById(R.id.dd1);
						immagine1.setImageDrawable(v.getResources().getDrawable(ccc.getCarte().get(0).getImmagine()));
					    immagine1.setTag(ccc.getCarte().get(0));
					    immagine1.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								ilSuoListener(v);
							}
						});
					    ImageView immagine2= (ImageView) dialogcombo.findViewById(R.id.dd2);
						immagine2.setImageDrawable(v.getResources().getDrawable(ccc.getCarte().get(1).getImmagine()));
					    immagine2.setTag(ccc.getCarte().get(1));
					    immagine2.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								ilSuoListener(v);
							}
						});
					    ImageView immagine3= (ImageView) dialogcombo.findViewById(R.id.dd3);
						if (ccc.getCarte().size()>2 && ccc.getCarte().get(2)!=null) 
							{
								immagine3.setBackgroundResource(R.layout.rounded_image);
								immagine3.setImageDrawable(v.getResources().getDrawable(ccc.getCarte().get(2).getImmagine()));
								immagine3.setTag(ccc.getCarte().get(2));
							    immagine3.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										ilSuoListener(v);
									}
								});
							}
						else immagine3.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
					    
					    ImageView immagine4= (ImageView) dialogcombo.findViewById(R.id.dd4);
						if (ccc.getCarte().size()==4 && ccc.getCarte().get(3)!=null)
						{
							immagine4.setBackgroundResource(R.layout.rounded_image);
							immagine4.setImageDrawable(v.getResources().getDrawable(ccc.getCarte().get(3).getImmagine()));
							immagine4.setTag(ccc.getCarte().get(3));
							immagine4.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									ilSuoListener(v);
								}
							});
						}
						else immagine4.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
					    
					    TextView tes=(TextView) dialogcombo.findViewById(R.id.ddtextcombo);
					    tes.setTypeface(custom_font);
					    tes.setText(ccc.getDescrizione());
					    tes.setTextSize(25);
					    tes.setTextColor(Color.WHITE);
					    dialogcombo.show();
					}
				});
	        } 
	        else 
	        {
	        	holdercombo = (ViewHolderCombo) convertView.getTag(); 
	        }
	        c=combos[position];

	        ArrayList<Carta> comboIm=new ArrayList<Carta>();
	        comboIm=c.getCarte();
	        holdercombo.textCombo.setText(c.getNome());
	        holdercombo.combo1.setImageResource(comboIm.get(0).getImmagine());
	        holdercombo.combo2.setImageResource(comboIm.get(1).getImmagine());
	        if (comboIm.size()>2) holdercombo.combo3.setImageResource(comboIm.get(2).getImmagine());
	        else holdercombo.combo3.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
	        if (comboIm.size()>3) holdercombo.combo4.setImageResource(comboIm.get(3).getImmagine());
	        else holdercombo.combo4.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
	        holdercombo.textCombo.setTag(c);
	        
	        return convertView;
	  }
		
		public void ilSuoListener(View v)
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
  			    	"EVO 4:8 CARTE\n\nHp: "+c.getHp4()+"\t\tAttacco: "+c.getAtk4() :
  			    		"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
  			    	c.getNome()+"\n"+
  			    		"\nSkill:\n"+c.getSkills()+"\n\n\n"+
  			    		
  			    		"TIER1\n\nHp: "+c.getHp1()+"\t\tAtk: "+c.getAtk1()+"\n\n"+
  			    		"TIER2\n\nHp: "+c.getHp2()+"\t\tAtk: "+c.getAtk2()+"\n\n"+
  			    		"TIER3:4 CARDS\n\nHp: "+c.getHp3()+"\t\tAtk: "+c.getAtk3()+"\n\n"+
  			    		"TIER4:8 CARDS\n\nHp: "+c.getHp4()+"\t\tAtk: "+c.getAtk4()	 ;
			    tiers.setText(testo2);
			    
			    
			    if ((c.getTipo())==R.drawable.druidlogonew){	statistiche.setTextColor(Color.YELLOW); tiers.setTextColor(Color.GREEN);}
			    else if ((c.getTipo())==R.drawable.camelotlogonew){	statistiche.setTextColor(Color.RED); tiers.setTextColor(Color.WHITE);}
			    else{ statistiche.setTextColor(Color.WHITE); tiers.setTextColor(Color.RED);}
	   		       tiers.setTextSize(24);
	   		       tiers.setTypeface(custom_font);
			   
			    dialog.show(); 
		}

		public void notifyDataSetChangedOrdineAlfa(boolean b) {
			if (b==true)
			{
				Arrays.sort(combos, new Comparator<Combo>() {
					    public int compare(Combo arg0, Combo arg1) 
					    {
					    	return controlloalfa(arg0, arg1);
					    }
					});
			}
		else
		{
			Arrays.sort(combos, new Comparator<Combo>() {
			    public int compare(Combo arg0, Combo arg1) 
			    {
			    	return controlloalfa(arg0, arg1);
			    }
			});
		}
	    super.notifyDataSetChanged();
			
		}
		
		public int controlloalfa(Combo arg0,Combo arg1)
		{
			int lunghezzaMin=(arg0.getNome().length() > arg1.getNome().length()) ? arg1.getNome().length()-1 : arg0.getNome().length()-1;
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

		public void notifyDataSetChangedOrd(boolean b) 
		{
			if (b==false) //crescente
			{
				Arrays.sort(combos, new Comparator<Combo>() {
				    public int compare(Combo arg0, Combo arg1) 
				    {
				    	if (arg0.getCarte().size() > arg1.getCarte().size()) return 1;
				    	else if  (arg0.getCarte().size() < arg1.getCarte().size()) return -1;
				    	else return 0;
				    }
				});
			}
			else //decres
			{
				Arrays.sort(combos, new Comparator<Combo>() {
				    public int compare(Combo arg0, Combo arg1) 
				    {
				    	if (arg0.getCarte().size() < arg1.getCarte().size()) return 1;
				    	else if  (arg0.getCarte().size() > arg1.getCarte().size()) return -1;
				    	else return 0;
				    }
				});
			}
			super.notifyDataSetChanged();
		}
}

