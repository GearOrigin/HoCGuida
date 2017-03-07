package info.gear.hocguide;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	private boolean lingua=false;

    public HomeFragment() {
    }

    public HomeFragment(boolean lingua){
		this.lingua=lingua;
		Log.i("System.out", "lingua:"+lingua);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TextView titolo=(TextView) rootView.findViewById(R.id.titolo);
        String y=(lingua==false) ? "HOC Guida" : "HOC Guide";
        titolo.setText(y);
        titolo.setTextSize(40);
        TextView testo=(TextView) rootView.findViewById(R.id.TextView04);
        testo.setText(Regole(lingua));
        Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(),
        	      "fonts/Deutsch.ttf");
        titolo.setTypeface(custom_font);
        testo.setTypeface(custom_font);
        testo.setTextSize(20);
        testo.setMovementMethod(new ScrollingMovementMethod());
        testo.setVerticalScrollBarEnabled(false); 
        return rootView;
    }

	private String Regole(boolean lingua) 
	{
		if (lingua==false)
		{
			StringBuilder sb=new StringBuilder("");
			sb.append("Questa applicazione e' stata realizzata da due amici legati dalla passione del gioco.\n");
			sb.append("Ogni cosa inerente al gioco e' di proprieta' esclusiva della sua casa produttrice.\n");
			sb.append("Ti invitiamo percio' a scaricarlo e a visitare il suo sito ufficiale.\n");
			sb.append("Nel caso ti piaccia questa applicazione puoi contribuire votando l'applicazione sullo store; ");
			sb.append("E' una versione in continuo aggiornamento, supportaci e contattaci per suggerimenti o eventuali errori.\n\n");
			sb.append("BUON GAME A TUTTI");
			return sb.toString();
		}
		else
		{
			StringBuilder sb=new StringBuilder("");
			sb.append("This app has been set up by two friends who shared an intense passion for this game.\n");
			sb.append("Every information pertinent to the game is exclusive possession of his factory and the concerned members.\n");
			sb.append("We exhort you therefore to download it, visiting the official web-site.\n");
			sb.append("in case you'll appreciate this app, feel free to rate it in the app-store -thus contributing to its growth-; ");
			sb.append("This version is in continuous development and update: support and contact us for suggestions or eventual bugs.\n\n");
			sb.append("ENJOY THE GAME AND HAVE FUN!");
			return sb.toString();
		}
	}
}
