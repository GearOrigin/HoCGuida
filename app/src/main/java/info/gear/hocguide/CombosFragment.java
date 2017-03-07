package info.gear.hocguide;



import info.gear.hocguide.model.MyFileCombo;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class CombosFragment extends Fragment {
    //DATI
	private ListView list;
    private ArrayList<Carta> databaseCarta=new ArrayList<Carta>();
    private ArrayList<Combo> databaseCombo=new ArrayList<Combo>();
	private boolean lingua=false;
	private ViewHolderAdapterCombo adapter;
	ArrayList<String> nomiArr=new ArrayList<String>();
	ArrayList<String> effettiArr=new ArrayList<String>();
    Combo[] combos;
    private InterstitialAd interstitial;
    AdRequest adRequest2;
	AdView mAdView;
	private LinearLayout linlaHeaderProgress;
	private View rootView;
    private Combo[] backupcarte;

    public CombosFragment() {
    }

    public CombosFragment(ArrayList<Combo> databaseCombo, ArrayList<Carta> databaseCarta,boolean lingua)
    {
    	this.lingua=lingua;
    	this.databaseCarta=databaseCarta;
    	this.databaseCombo=databaseCombo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) 
	{
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_combos, container, false);
        super.onCreate(savedInstanceState);
        mAdView = (AdView) rootView.findViewById(R.id.bb2);
        list=(ListView) rootView.findViewById(R.id.listaCombo);
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgresscombo);
        
        new Task().execute();
        
        return rootView;
    }
    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
        	Log.i("System.out", "SIAMO IN PRE");
            linlaHeaderProgress.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
    	    super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) 
        {
        	Log.i("System.out", "SIAMO IN POST");
        	AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            interstitial = new InterstitialAd(rootView.getContext());
            interstitial.setAdUnitId("ca-app-pub-3970473814760857/4691718521");
            // Create ad request.
            adRequest2 = new AdRequest.Builder().build();
            // Begin loading your interstitial.
            interstitial.loadAd(adRequest2);
        	
        	
        	list.setAdapter(adapter);
            adapter.notifyDataSetChangedOrdineAlfa(true);
        	linlaHeaderProgress.setVisibility(View.GONE);
        	list.setVisibility(View.VISIBLE);
        	super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) 
        {
        	//CARTE E COMBO    
        	Log.i("System.out", "INIZIO BACKGROUND");
        	combos=new Combo[databaseCombo.size()];
            backupcarte=new Combo[databaseCombo.size()];
        	int cont=0;
    		for(Combo c:databaseCombo)
    		{
    			combos[cont]=c;
    			cont++;
    		}
        	backupcarte=combos;
            adapter = new ViewHolderAdapterCombo(getActivity(),combos,databaseCarta,lingua);
            Log.i("System.out", "BK:settato adapter");
            return true;
        }
    }
    
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
    	if (lingua==false) inflater.inflate(R.menu.combomenu, menu);
    	else inflater.inflate(R.menu.combomenueng, menu);
    	
    	
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId()) 
		{
			case R.id.FiltroCombo:
			{	
				adapter.notifyDataSetChangedOrd(false);
					interstitial.show();
					break;
			}
			
			case R.id.FiltroCombo2:
			{
				adapter.notifyDataSetChangedOrd(true);
				interstitial.show();
				break;
			}
			case R.id.NomeCombo:
			{
				adapter.notifyDataSetChangedOrdineAlfa(true);
				interstitial.show();
				break;
			}
            case R.id.CercoCombo:
            {
                SearchManager searchManager =(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s)
                    {
                        CercoLeCombo(s);
                        return false;
                    }
                });

                break;
		    }
        }
		return super.onOptionsItemSelected(item);
    }
    public void CercoLeCombo(String s) {

        if (s.trim().isEmpty())
        {
            combos=new Combo[backupcarte.length];
            combos=backupcarte;
        }
        else {
            ArrayList<Combo> filtrocombo = new ArrayList<Combo>();
            for (Combo combo : combos) {
                if (s.equalsIgnoreCase(splittaStringa(combo.getNome(), s.length()))) {
                    filtrocombo.add(combo);
                }
            }
            if (filtrocombo.isEmpty())
            {
                combos=new Combo[backupcarte.length];
                combos=backupcarte;
                Toast.makeText(getActivity(), lingua ? "Combo not found" : "Combo non trovata", Toast.LENGTH_SHORT).show();
            }
            else
            {
                combos= new Combo[filtrocombo.size()];
                for (int i = 0; i < (filtrocombo.size()); i++)
                    combos[i] = filtrocombo.get(i);
            }
        }
        adapter=new ViewHolderAdapterCombo(getActivity(),combos,databaseCarta,lingua);
        list.setAdapter(adapter);
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

