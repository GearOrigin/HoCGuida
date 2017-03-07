package info.gear.hocguide;


import android.app.Dialog;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import info.gear.hocguide.adapter.ViewHolderAdapterCreation;

public class CreationFragment extends Fragment {

    TabHost mytabhost;
    ListView list;
    private boolean lingua=false;
    private ArrayList<String> names=new ArrayList<String>();
    private String[] namesString;
    private ViewHolderAdapter2 adapter;
    private View rootView;
    private Carta[] carte;
    private ArrayList<Carta> databaseCarta=new ArrayList<Carta>();
    protected Integer[] databaseImmagini;
    private InterstitialAd interstitial;
    AdView mAdView;
    AdRequest adRequest2;
    private View linlaHeaderProgress;

    private int cont_admob=0;
    private Deck mioParty;
    private Carta c;
    private int num_team,num_carta;
    private Carta[] backupCarte;
    private boolean stocreando=false;

    public CreationFragment() {
    }
    public CreationFragment(ArrayList<Carta> databaseCarta, Integer[] databaseImmagini,boolean lingua)
    {

        this.lingua=lingua;
        this.databaseImmagini=databaseImmagini;
        this.databaseCarta=databaseCarta;
        mioParty=new Deck();
        mioParty.add(new Team());
        mioParty.add(new Team());
        mioParty.add(new Team());
	}


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        this.rootView = inflater.inflate(R.layout.fragment_creation, container, false);
        super.onCreate(savedInstanceState);



        mytabhost=(TabHost)rootView.findViewById(R.id.TabHost01);
    //settaggio tabs
        mytabhost.setup();
        TabHost.TabSpec spec;
        spec = mytabhost.newTabSpec("creation");
        String tab1=(lingua==false) ? "Carte" : "Cards" ;

        spec.setIndicator("PARTY");

        // specify layout of tab
        spec.setContent(R.id.Party);

        // adding tab in TabHost
        mytabhost.addTab(spec);
        // unito
        mytabhost.addTab(mytabhost.newTabSpec("cards").setIndicator(tab1).setContent(R.id.Cards));



        ImageView immagine11=(ImageView) rootView.findViewById(R.id.prm_prT);
        immagine11.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=0;num_carta=0;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine12=(ImageView) rootView.findViewById(R.id.sec_prT);
        immagine12.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=0;num_carta=1;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine13=(ImageView) rootView.findViewById(R.id.trz_prT);
        immagine13.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=0;num_carta=2;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine14=(ImageView) rootView.findViewById(R.id.qrt_prT);
        immagine14.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=0;num_carta=3;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine21=(ImageView) rootView.findViewById(R.id.prm_secT);
        immagine21.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=1;num_carta=0;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine22=(ImageView) rootView.findViewById(R.id.sec_secT);
        immagine22.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=1;num_carta=1;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine23=(ImageView) rootView.findViewById(R.id.trz_secT);
        immagine23.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=1;num_carta=2;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine24=(ImageView) rootView.findViewById(R.id.qrt_secT);
        immagine24.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=1;num_carta=3;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine31=(ImageView) rootView.findViewById(R.id.prm_trzT);
        immagine31.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=2;num_carta=0;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine32=(ImageView) rootView.findViewById(R.id.sec_trzT);
        immagine32.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=2;num_carta=1;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine33=(ImageView) rootView.findViewById(R.id.trz_trzT);
        immagine33.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=2;num_carta=2;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });
        ImageView immagine34=(ImageView) rootView.findViewById(R.id.qrt_trzT);
        immagine34.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
        immagine34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {num_team=2;num_carta=3;
                eventoImmagine((ImageView)view,num_team,num_carta);
            }
        });



        list=(ListView) rootView.findViewById(R.id.listcarte);
        linlaHeaderProgress =rootView.findViewById(R.id.linlaHeaderProgress);
        mAdView = (AdView) rootView.findViewById(R.id.bb);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        new Task().execute();

        return rootView;
    }



    private void eventoImmagine(final ImageView img,final int num_team, final int num_carta)
    {
        //Log.i("System.out",""+img.getDrawable().getConstantState()+"e la empty "+ rootView.getContext().getResources().getDrawable(R.drawable.empty_party).getConstantState());
        cont_admob++;
        if (cont_admob % 5==0)
        {
            adRequest2 = new AdRequest.Builder().build();
            // Begin loading your interstitial.
            interstitial.loadAd(adRequest2);
            interstitial.show();
        }
        if(img.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.empty_party).getConstantState())
        {
            Log.i("System.out","Sono dentro la funzione della carta iniziale");
            stocreando=true;
            mytabhost.setCurrentTab(1); //CARDS
            mytabhost.getTabWidget().getChildTabViewAt(0).setEnabled(false);
            adapter=new ViewHolderAdapterCreation(getActivity(),namesString, carte,lingua);
            list.setAdapter(adapter);
            c=null;
            list.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
                {
                    c=(Carta) adapterView.getItemAtPosition(position);


                    if(!mioParty.containOnDeck(c))
                    {
                        mioParty.getTeam(num_team).add(c, num_carta);
                        img.setImageDrawable(getResources().getDrawable(c.getImmagine()));
                        Toast.makeText(getActivity(),(lingua==false)? "Aggiunta carta "+c.getNome():"Added "+c.getNome(),Toast.LENGTH_SHORT).show();
                        update(mioParty);
                    }
                    else Toast.makeText(getActivity(),(lingua==false)? "Carta "+c.getNome()+" già nel deck!!!":""+c.getNome()+" is already in the party",Toast.LENGTH_SHORT).show();


                    mytabhost.getTabWidget().getChildTabViewAt(0).setEnabled(true);
                    mytabhost.setCurrentTab(0);
                    stocreando=false;
                    adapter=new ViewHolderAdapter2(getActivity(), carte,lingua);
                    list.setAdapter(adapter);

                }
            });
        }
        else
        {
            Log.i("System.out","dentro elimina carta");
            //elimina carta dal deck
            Carta c=mioParty.getTeam(num_team).getCard(num_carta);
            if (c!=null)
            {

                Toast.makeText(getActivity(),(lingua==false)? "Rimossa carta "+c.getNome():
                        ""+c.getNome()+" removed",Toast.LENGTH_SHORT).show();
                mioParty.getTeam(num_team).removeCarta(num_carta);
                img.setImageDrawable(getResources().getDrawable(R.drawable.empty_party));
               update(mioParty);
            }

            else {
                Toast.makeText(getActivity(),(lingua==false)? "ERRORE FATALE:cancello party...":
                        "FATAL ERROR:remove party...",Toast.LENGTH_SHORT).show();
                mioParty=new Deck();
            }

        }
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
            int cont=0;
            backupCarte=new Carta[databaseCarta.size()];
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
                        if (stocreando) CercoLeCarte(s,true);
                        else CercoLeCarte(s,false);
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

    public void CercoLeCarte(String s,boolean increation) {

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
        if (increation) adapter=new ViewHolderAdapterCreation(getActivity(),namesString, carte,lingua);
        else adapter=new ViewHolderAdapter2(getActivity(),carte,lingua);
        list.setAdapter(adapter);
    }

    public void update(Deck deck)
    {
        Log.i("System.out","sono dentro update");
        double attack=deck.getPartyATK();
        double HP=deck.getPartyHP();
        int Mana=deck.getPartyMana();

        int trovato=0;


        if(deck.getParty().size()==3)
        {
            if(deck.getTeam(0).teamCompleto() && deck.getTeam(1).teamCompleto() && deck.getTeam(2).teamCompleto()) {

                Log.i("System.out", "Sono dentro l if del size del deck");
                for (int i = 1; i < 3; i++) {
                    if (deck.getTeam(i).getStelleTot() != deck.getTeam(i - 1).getStelleTot()) {
                        trovato = 1;
                    }

                }

                if (trovato == 0) {

                    if (deck.getTeam(0).getStelleTot() == 3) {

                        attack = attack + (attack * 0.02);
                        HP = HP + (HP * 0.02);


                    }
                    if (deck.getTeam(0).getStelleTot() == 4) {
                        attack = attack + (attack * 0.03);
                        HP = HP + (HP * 0.03);

                    }
                    if (deck.getTeam(0).getStelleTot() == 5) {
                        attack = attack + (attack * 0.04);
                        HP = HP + (HP * 0.04);
                    }
                    if (deck.getTeam(0).getStelleTot() == 6) {
                        attack = attack + (attack * 0.05);
                        HP = HP + (HP * 0.05);
                    }
                }else
                {
                    attack=attack+(attack*((deck.getMinStelle()-1)/100));
                    HP=HP+(HP*((deck.getMinStelle()-1)/100));
                }

                    if (deck.getPartyHP() >= 10000 && deck.getPartyHP() < 20000) {
                        attack = attack + (attack * 0.01);
                    }
                    if (deck.getPartyHP() >= 20000 && deck.getPartyHP() < 50000) {
                        attack = attack + (attack * 0.02);
                    }
                    if (deck.getPartyHP() >= 50000 && deck.getPartyHP() < 100000) {
                        attack = attack + (attack * 0.03);
                    }
                    if (deck.getPartyHP() >= 100000 && deck.getPartyHP() < 150000) {
                        attack = attack + (attack * 0.04);
                    }
                    if (deck.getPartyHP() >= 150000) {
                        attack = attack + (attack * 0.05);

                    }
                    if (deck.getPartyATK() >= 3000 && deck.getPartyATK() < 6000) {
                        HP = HP + (HP * 0.01);
                    }
                    if (deck.getPartyATK() >= 6000 && deck.getPartyATK() < 15000) {
                        HP = HP + (HP * 0.02);
                    }
                    if (deck.getPartyATK() >= 15000 && deck.getPartyATK() < 30000) {
                        HP = HP + (HP * 0.03);
                    }
                    if (deck.getPartyATK() >= 30000 && deck.getPartyATK() < 45000) {
                        HP = HP + (HP * 0.04);
                    }
                    if (deck.getPartyATK() >= 45000) {
                        HP = HP + (HP * 0.05);
                    }

                    int trovatoTipo = 0;
                    for (int i = 1; i < 3; i++) {
                        if (deck.getTeam(i).tipoTeam() != deck.getTeam(i - 1).tipoTeam()) {
                            trovatoTipo = 1;
                        }

                    }

                    if (trovatoTipo == 0) {
                        if (deck.getTeam(0).equals('C')) {
                            HP = HP + (HP * 0.05);
                        }
                        if (deck.getTeam(0).equals('D')) {
                            attack = attack + (attack * 0.05);
                        }
                    }



            }

        }

        TextView attacco=(TextView) rootView.findViewById(R.id.ATKText);
        TextView vita=(TextView) rootView.findViewById(R.id.HPText);
        TextView MANAS=(TextView) rootView.findViewById(R.id.ManaText);
        TextView Combosss=(TextView) rootView.findViewById(R.id.TextComboss);



        attacco.setText("ATK:"+" "+(int)(attack+(attack*0.1)));
        vita.setText("HP:"+" "+(int)(HP+(HP*0.1)));

        if(deck.getTeam(0).teamCompleto() && deck.getTeam(1).teamCompleto() && deck.getTeam(2).teamCompleto())
        {
            MANAS.setText("Mana:"+" "+(Mana-30));
        }
        else if(deck.getTeam(0).teamCompleto() && deck.getTeam(1).teamCompleto() && !deck.getTeam(2).teamCompleto())
        {
            MANAS.setText("Mana:"+" "+(Mana-20));

        }
        else if(deck.getTeam(0).teamCompleto() && !deck.getTeam(1).teamCompleto() && !deck.getTeam(2).teamCompleto())
        {
            MANAS.setText("Mana:"+" "+(Mana-10));
        }
        else
        {
            MANAS.setText(("Mana:"+" "+Mana));
        }

        attacco.setTextColor(Color.GREEN);
        vita.setTextColor(Color.RED);
        MANAS.setTextColor(Color.BLUE);




        Map<Integer,ArrayList<Combo>> comboParty=new HashMap<Integer, ArrayList<Combo>>();

        comboParty=getComboParty();

        StringBuilder ComboBuiler = new StringBuilder();
        ComboBuiler.append("COMBO:"+"\n\n");

        Log.i("System.out",""+mioParty.getTeam(2).toString());
        Log.i("System.out",""+mioParty.getTeam(2).getAllCards().length);
        if(mioParty.getTeam(2).getCard(0)!=null)
        {
            Log.i("System.out",mioParty.getTeam(2).getCard(0).getNome());
        }
        for(int i=0;i<3;i++)
        {
            ComboBuiler.append("Team "+(i+1)+":"+"\n");


            for(Combo c : comboParty.get(i))
            {
                ComboBuiler.append(c.getNome()+"\n");
                ComboBuiler.append(c.getDescrizione()+"\n");
                ComboBuiler.append("--------------------"+"\n");

            }
        }


        Combosss.setText(ComboBuiler.toString());
        Combosss.setTextColor(Color.BLACK);




    }


    public Map<Integer,ArrayList<Combo>> getComboParty()
    {
        Map<Integer,ArrayList<Combo>> result= new HashMap<Integer, ArrayList<Combo>>();


        int trovata=0;

        for(int i=0;i<3;i++)
        {
            ArrayList<Combo> ComboIn=new ArrayList<Combo>();

            for(Carta c : mioParty.getTeam(i).getAllCards())
            {
                if(c!=null)
                {
                    for(Combo comb : c.getCombos())
                    {
                        for(Carta cartComb : comb.getCarte())
                        {

                            if(!mioParty.getTeam(i).getAllCardToArr().contains(cartComb))
                            {
                              trovata=1;
                            }

                        }
                        if(trovata==0)
                        {
                            if(!ComboIn.contains(comb))
                            {
                                ComboIn.add(comb);
                            }


                        }
                        trovata=0;
                    }
                }


            }

            Log.i("System.out","la i e: "+i);
              result.put(i,ComboIn);

        }


        return result;

    }

}
