package info.gear.hocguide;

import java.util.Random;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SupportFragment extends Fragment {
	
	private boolean lingua=false;

    public SupportFragment() {
    }

    public SupportFragment(boolean lingua){
		this.lingua=lingua;
	}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_support, container, false);
        TextView txt=(TextView) rootView.findViewById(R.id.support);
        String testo=(lingua==false) ? "Supportaci cliccando sui banner, ricorda di segnalarci eventuali errori usando il tasto nel menu Opzioni" : "Support us clicking on the banners, remember to signal us possible bugs with the button in Settings";
        txt.setText(testo);
        Random r=new Random();
        int j=r.nextInt(3);
        final ImageView floatingImage7;
        final ImageView floatingImage1;
        if (j<2)  
        {
        	floatingImage7=(ImageView)rootView.findViewById(R.id.imageView07);
        	floatingImage1= (ImageView)rootView.findViewById(R.id.imageView01);
        }
        else
        {
        	floatingImage1=(ImageView)rootView.findViewById(R.id.imageView07);
        	floatingImage7= (ImageView)rootView.findViewById(R.id.imageView01);
        }
        final ImageView floatingImage5;
        final ImageView floatingImage6;
        final ImageView floatingImage4 = (ImageView)rootView.findViewById(R.id.imageView04);
        final ImageView floatingImage3 = (ImageView)rootView.findViewById(R.id.imageView3);
        if (j<2)  
        {
        	floatingImage5= (ImageView)rootView.findViewById(R.id.imageView06);
        	floatingImage6= (ImageView)rootView.findViewById(R.id.imageView05);
        }
        else
        {
        	floatingImage5= (ImageView)rootView.findViewById(R.id.imageView05);
        	floatingImage6= (ImageView)rootView.findViewById(R.id.imageView06);
        }
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,0.0f, 1200.0f);
        TranslateAnimation animation2 = new TranslateAnimation(0.0f, 0.0f,0.0f, 1200.0f);
        TranslateAnimation animation3 = new TranslateAnimation(0.0f, 0.0f,0.0f, 1200.0f);
        TranslateAnimation animation4 = new TranslateAnimation(0.0f, 0.0f,0.0f, 1200.0f);
        TranslateAnimation animation5 = new TranslateAnimation(0.0f, 0.0f,0.0f, 1200.0f);
        TranslateAnimation animation6 = new TranslateAnimation(0.0f, 0.0f,0.0f, 1200.0f);
        
        
        int n=r.nextInt(3);
        animation.setDuration((n+1)*800);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(2);
        animation.setFillAfter(true);
        n=r.nextInt(2);
        animation2.setDuration((n+1)*700);
        animation2.setRepeatCount(Animation.INFINITE);
        animation2.setRepeatMode(2);
        animation2.setFillAfter(true);
        n=r.nextInt(2);
        animation3.setDuration((n+1)*760);
        animation3.setRepeatCount(Animation.INFINITE);
        animation3.setRepeatMode(2);
        animation3.setFillAfter(true);
        n=r.nextInt(3);
        animation4.setDuration((n+1)*780);
        animation4.setRepeatCount(Animation.INFINITE);
        animation4.setRepeatMode(2);
        animation4.setFillAfter(true);
        n=r.nextInt(2);
        animation5.setDuration((n+1)*720);
        animation5.setRepeatCount(Animation.INFINITE);
        animation5.setRepeatMode(2);
        animation5.setFillAfter(true);
        n=r.nextInt(2);
        animation6.setDuration((n+1)*770);
        animation6.setRepeatCount(Animation.INFINITE);
        animation6.setRepeatMode(2);
        animation6.setFillAfter(true);
        
        floatingImage1.startAnimation(animation);
        floatingImage3.startAnimation(animation6);
        floatingImage4.startAnimation(animation4);
        floatingImage5.startAnimation(animation2);
        floatingImage6.startAnimation(animation5);
        floatingImage7.startAnimation(animation3);

        return rootView;
       }
    
}
