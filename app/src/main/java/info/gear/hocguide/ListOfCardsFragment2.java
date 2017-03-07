package info.gear.hocguide;


import com.google.android.gms.ads.*;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import info.gear.hocguide.Carta;
import java.util.ArrayList;

public class ListOfCardsFragment2 extends Fragment{
    
	//DATI
	ListView list;
	private boolean lingua=false;
    private ArrayList<String> names=new ArrayList<String>();
    private String[] namesString;
    private ViewHolderAdapter2 adapter;
    private View rootView;
    private Carta[] carte;
    private Carta[] backupCarte;
    private ArrayList<Carta> databaseCarta=new ArrayList<Carta>();
    protected Integer[] databaseImmagini;
    private InterstitialAd interstitial;
    AdView mAdView;
    AdRequest adRequest2;
	private View linlaHeaderProgress;

    public ListOfCardsFragment2() {
    }

    //COSTRUTTORE
    public ListOfCardsFragment2(ArrayList<Carta> databaseCarta, Integer[] databaseImmagini,boolean lingua) 
    {
  	  	this.lingua=lingua;
  	  	this.databaseImmagini=databaseImmagini;
  	  	this.databaseCarta=databaseCarta;
	}
    
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

	//FRAGMENT
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         
        rootView = inflater.inflate(R.layout.fragment_list_of_cards, container, false);
        super.onCreate(savedInstanceState);
        
        list=(ListView) rootView.findViewById(R.id.listcarte);
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);
        
        mAdView = (AdView) rootView.findViewById(R.id.bb);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
              
        new Task().execute();
               
        return rootView;
    }


    class Task extends AsyncTask<String, Integer, Boolean> {
            @Override
            protected void onPreExecute() {
            	
                linlaHeaderProgress.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
        	
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean result) 
            {
            	
            	list.setAdapter(adapter);
            	adapter.notifyDataSetChangedOrdineAlfa(true);
            	linlaHeaderProgress.setVisibility(View.GONE);
            	list.setVisibility(View.VISIBLE);
            	interstitial = new InterstitialAd(rootView.getContext());
            	interstitial.setAdUnitId("ca-app-pub-3970473814760857/4691718521");
            	// Create ad request.
            	adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2);  
            	super.onPostExecute(result);
            	
            }

            @Override
            protected Boolean doInBackground(String... params) 
            {
            	carte=new Carta[databaseCarta.size()];
                backupCarte=new Carta[databaseCarta.size()];
            	int cont=0;
        		for(Carta c:databaseCarta)
        		{
        			carte[cont]=c;
        			cont++;
        		}
        		backupCarte=carte;
        		//INIZIALIZZA LA VIEW
        		adapter=new ViewHolderAdapter2(getActivity(), carte,lingua);
        		
                return true;
            }
        }

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
    	if (lingua==false) inflater.inflate(R.menu.main, menu);
    	else inflater.inflate(R.menu.maineng, menu);
    	
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId()) 
		{
			case R.id.FiltroStelleC:
			{	
					adapter.notifyDataSetChangedStars(true);
					adRequest2 = new AdRequest.Builder().build();
	            	// Begin loading your interstitial.
	            	interstitial.loadAd(adRequest2); 
					interstitial.show();
					break;
			}
			case R.id.FiltroManaC:
			{
				adapter.notifyDataSetChangedMana(true);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				break;
			}
			case R.id.FiltroHpC:
			{
				adapter.notifyDataSetChangedHp(true);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				Toast.makeText(getActivity(),(this.lingua==false) ? "Possibile database nel sito non aggiornato": "Possible database not adjourned on the site", Toast.LENGTH_SHORT).show();
				break;
			}
			case R.id.FiltroAtkC:
			{
				adapter.notifyDataSetChangedAtk(true);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				Toast.makeText(getActivity(),(this.lingua==false) ? "Possibile database nel sito non aggiornato": "Possible database not adjourned on the site", Toast.LENGTH_SHORT).show();
				break;
			}
			case R.id.FiltroStelleD:
			{	
					adapter.notifyDataSetChangedStars(false);
					adRequest2 = new AdRequest.Builder().build();
	            	// Begin loading your interstitial.
	            	interstitial.loadAd(adRequest2); 
					interstitial.show();
					break;
			}
			case R.id.FiltroManaD:
			{
				adapter.notifyDataSetChangedMana(false);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				break;
			}
			case R.id.FiltroHpD:
			{
				adapter.notifyDataSetChangedHp(false);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				Toast.makeText(getActivity(),(this.lingua==false) ? "Possibile database nel sito non aggiornato": "Possible database not adjourned on the site", Toast.LENGTH_SHORT).show();
				break;
			}
			case R.id.FiltroAtkD:
			{
				adapter.notifyDataSetChangedAtk(false);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				Toast.makeText(getActivity(),(this.lingua==false) ? "Possibile database nel sito non aggiornato": "Possible database not adjourned on the site", Toast.LENGTH_SHORT).show();
				break;
			}
			case R.id.FiltroAlfabeticoC:
			{
				adapter.notifyDataSetChangedOrdineAlfa(true);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				break;
			}
			case R.id.FiltroAlfabeticoD:
			{
				adapter.notifyDataSetChangedOrdineAlfa(false);
				adRequest2 = new AdRequest.Builder().build();
            	// Begin loading your interstitial.
            	interstitial.loadAd(adRequest2); 
				interstitial.show();
				break;
			}
            case R.id.CercoCarta:
            {


                SearchManager searchManager =(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
                {


                    @Override
                    public boolean onQueryTextSubmit(String s) {

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s)
                    {
                        CercoLeCarte(s);
                        return false;
                    }
                });

                break;
            }
		}
		return super.onOptionsItemSelected(item);
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

    public void CercoLeCarte(String s) {

        if (s.trim().isEmpty())
        {
            carte=new Carta[backupCarte.length];
            carte=backupCarte;
        }
        else {
            ArrayList<Carta> filtrocarte = new ArrayList<Carta>();
            for (Carta card : carte) {
                if (s.equalsIgnoreCase(splittaStringa(card.getNome(), s.length()))) {
                    filtrocarte.add(card);
                }
            }
            if (filtrocarte.isEmpty())
            {
                carte=new Carta[backupCarte.length];
                carte=backupCarte;
                Toast.makeText(getActivity(),lingua ? "Cards not found" : "Carte non trovate", Toast.LENGTH_SHORT).show();
            }
            else
            {
                carte = new Carta[filtrocarte.size()];
                for (int i = 0; i < (filtrocarte.size()); i++)
                    carte[i] = filtrocarte.get(i);
            }
        }
        adapter=new ViewHolderAdapter2(getActivity(), carte,lingua);
        list.setAdapter(adapter);
    }
}
