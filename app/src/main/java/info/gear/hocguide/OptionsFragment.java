package info.gear.hocguide;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsFragment extends Fragment {
	
//DATI	
	private boolean lingua=false;

    public OptionsFragment() {
    }

    //COSTRUTTORE
	public OptionsFragment(boolean lingua)
	{
		this.lingua=lingua;
	}
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 

      View rootView = inflater.inflate(R.layout.fragment_option2, container, false);
      Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(),"fonts/Deutsch.ttf");
      

      TextView testo=(TextView) rootView.findViewById(R.id.testopzioni);
      if (lingua==false) testo.setText("Opzioni");
      else testo.setText("Settings");
      testo.setTypeface(custom_font);
      testo.setTextSize(40);
      testo.setGravity(Gravity.CENTER);
      
      
      TextView testo2=(TextView) rootView.findViewById(R.id.lingua);
      if (lingua==false) testo2.setText("Lingua:");
      else testo2.setText("Language:");
      testo2.setTypeface(custom_font);
      testo2.setTextSize(20);
      testo2.setGravity(Gravity.CENTER);
      testo2.setTextColor(Color.BLACK);

      ImageView italiano=(ImageView) rootView.findViewById(R.id.italia);
      italiano.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			lingua=false;
			SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
			Editor editor = pref.edit();
			editor.clear();
			editor.putBoolean("opzioniLingua", lingua); // Storing boolean - true/false
			editor.commit();
			//Toast.makeText(v.getContext(),"Cambiata lingua", Toast.LENGTH_SHORT).show();
			Intent intent = getActivity().getIntent(); getActivity().finish(); startActivity(intent);
	}
      });
      ImageView inglese=(ImageView) rootView.findViewById(R.id.inghilterra);
      inglese.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			lingua=true;
			SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
			Editor editor = pref.edit();
			editor.clear();
			editor.putBoolean("opzioniLingua", lingua); // Storing boolean - true/false
			editor.commit();
			//Toast.makeText(v.getContext(),"Changed Language", Toast.LENGTH_SHORT).show();
			Intent intent = getActivity().getIntent(); getActivity().finish(); startActivity(intent);
		}
      });
      
      TextView testo3=(TextView) rootView.findViewById(R.id.recensione);
      if (lingua==false) testo3.setText("Recensione");
      else testo3.setText("Review");
      testo3.setTypeface(custom_font);
      testo3.setTextSize(30);
      testo3.setGravity(Gravity.CENTER);
      
      testo3.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			String pagina="https://play.google.com/store/apps/details?id=info.gear.hocguide";
		    Uri uri = Uri.parse(pagina);
		    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		    startActivity(intent);
		}
      });
      
      TextView testo4=(TextView) rootView.findViewById(R.id.contact);
      if (lingua==false) testo4.setText("Contattaci");
      else testo4.setText("Contact Us");
      testo4.setTypeface(custom_font);
      testo4.setTextSize(30);
      testo4.setGravity(Gravity.CENTER);

      testo4.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			String l=(lingua==false) ? "Se richiedi una correzione delle statistiche di una carta, per favore allega uno screen" : "If you want report a wrong card's detail, please attach a screen";
			Toast.makeText(getActivity(), l, Toast.LENGTH_LONG).show();
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/html");
			intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"gear.nfodev@gmail.com"} );
			intent.putExtra(Intent.EXTRA_SUBJECT, "HOC:");
			intent.putExtra(Intent.EXTRA_TEXT, "PROBLEM:");
			l=(lingua==false) ? "Mando email" : "Send Email";
			startActivity(Intent.createChooser(intent, l));
		}
      });
      
      return rootView;
	}
}


