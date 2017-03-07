package info.gear.hocguide;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SiteFragment extends Fragment {
	
	public SiteFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.activity_main, container, false);

       String pagina="http://heroes-of-camelot.wikia.com/wiki/Heroes_of_Camelot_Wiki";
      
       Uri uri = Uri.parse(pagina);
       Intent intent = new Intent(Intent.ACTION_VIEW, uri);
       startActivity(intent);      
      
       
       return rootView;
    }
}
