package info.gear.hocguide;


import info.gear.hocguide.adapter.ImageAdapter;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class ArenaFragment extends Fragment {

	private boolean lingua=false;

    public ArenaFragment() {
    }

    public ArenaFragment(boolean lingua) {
		this.lingua=lingua;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		//CARTE BAIA
		final Carta bou=new Carta("Bougard",R.drawable.demonlogo,28350000,12,"3", R.drawable.bougard,52000,14000,80000,24000,103000,33000,132000,43000,null);
        final Carta jez=new Carta("Jezebel",R.drawable.demonlogo,59040000,10,"1/2", R.drawable.jezebel,80000,40000,150000,60000,240000,80000,350000,115000,null);
        final Carta ossion=new Carta("Ossion",R.drawable.demonlogo,88000000,8,"1", R.drawable.ossion,200000,50000,262000,86000,350000,110000,460000,150000,null);
        final Carta queen=new Carta("Queen of Blood",R.drawable.demonlogo,68460000,8,"1", R.drawable.bloodqueen,166000,63000,240000,85000,320000,135000,480000,195000,null);
		
       View rootView = inflater.inflate(R.layout.fragment_arena, container, false);
       GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
              
       gridView.setAdapter(new ImageAdapter(rootView.getContext()));
       
       gridView.setOnItemClickListener(new OnItemClickListener() 
       {
    	   @Override
           public void onItemClick(AdapterView<?> parent, View v,int position, long id) 
    	   {
    		   
    		   ImageAdapter imageAdapter = new ImageAdapter(v.getContext());
    		   
    		   int selezionata=imageAdapter.mThumbIds[position];
    		   Carta c = null;
    		   if (selezionata==R.drawable.boubaia) c=bou;
    		   else if (selezionata==R.drawable.jezbaia) c=jez;
    		   else if (selezionata==R.drawable.baiaossion) c=ossion;
    		   else if (selezionata==R.drawable.queenbaia) c=queen;
    		   
    		   Dialog dialog = new Dialog(v.getContext());
   		       dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
   		       dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
   		       dialog.setContentView(R.layout.dialog_layout_carte);
   		       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
   		    
   		       ImageView immagine= (ImageView) dialog.findViewById(R.id.imageStatCarta);
   		       immagine.setImageDrawable(v.getResources().getDrawable(c.getImmagine()));
   		       Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(),
  	      	      "fonts/CELTICHD.TTF");
   		       TextView statistiche=(TextView) dialog.findViewById(R.id.testoStatCarta);
   		       String testo=(lingua==true) ? "\nHours:"+c.getSkills()+"\n\n\nTotal HP:\n"+c.getStelle() : "\nOre:"+c.getSkills()+"\n\n\nHp totali:\n"+c.getStelle() ;
   		       statistiche.setText(testo);
   		       statistiche.setTextColor(Color.WHITE);
   		       statistiche.setTextSize(25);
   		       statistiche.setTypeface(custom_font);
   		       
   		       TextView tiers=(TextView) dialog.findViewById(R.id.tierStatCarta);
   		       tiers.setMovementMethod(new ScrollingMovementMethod());
   		       String testo2= "\t\t\t\t\tSNEAK\n\nHp:  "+c.getHp1()+"\t\t\tAtk:  "+c.getAtk1()+"\n\n"+
      		    		"\t\t\t\t\tBACK\n\nHp:  "+c.getHp2()+"\t\t\tAtk:  "+c.getAtk2()+"\n\n"+
       		    		"\t\t\t\t\tSIDE\n\nHp:  "+c.getHp3()+"\t\t\tAtk:  "+c.getAtk3()+"\n\n"+
       		    		"\t\t\t\t\tFRONT\n\nHp:  "+c.getHp4()+"\t\t\tAtk:  "+c.getAtk4() ;
   		       tiers.setText(testo2);
   		       tiers.setTextSize(20);
   		       tiers.setTypeface(custom_font);
   		         		      
   		       dialog.show();
    	   }
       });       
       return rootView;
    }
}
