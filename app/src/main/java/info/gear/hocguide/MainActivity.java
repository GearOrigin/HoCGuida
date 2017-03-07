package info.gear.hocguide;

import info.gear.hocguide.adapter.NavDrawerListAdapter;
import info.gear.hocguide.model.MyFile;
import info.gear.hocguide.model.MyFileCombo;
import info.gear.hocguide.model.NavDrawerItem;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import com.androidquery.service.MarketService;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
//DATI	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private boolean lingua=true;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	TypedArray immagini;
    TypedArray immaginiTorre;
    protected Map<Combo,ArrayList<Carta>> mappaCombo= new HashMap<Combo, ArrayList<Carta>>();
	private boolean exit=false;
	protected Integer[] databaseImmagini;
    protected Integer[] databaseImmaginiTorre;
	protected ArrayList<Carta> databaseCarta=new ArrayList<Carta>();
	protected Reader readCombo = null;
	protected int dimensione;
	private ArrayList<Combo> databaseCombo=new ArrayList<Combo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		Manca aggiunta a navdrawer
		manca il collegamento al cambio fragment
		manca modificare towerfragment

		 */
        
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		//Log.i("System.out", "MAIN");
		lingua=pref.getBoolean("opzioniLingua", true); // getting boolean
		//Log.i("System.out", "MAIN:lingua="+lingua);
		immagini=getResources().obtainTypedArray(R.array.immaginiCarte);
        immaginiTorre=getResources().obtainTypedArray(R.array.immaginiTorre);
		//Log.i("System.out", "MAIN:letteimmagini="+immagini.length());
		databaseImmagini=new Integer[immagini.length()];
        databaseImmaginiTorre=new Integer[immaginiTorre.length()];
        for (dimensione=0;dimensione<immaginiTorre.length();dimensione++)
        {
            databaseImmaginiTorre[dimensione]=immaginiTorre.getResourceId(dimensione,-1);
        }
		for (dimensione=0;dimensione<immagini.length();dimensione++)
		{	
			databaseImmagini[dimensione]=immagini.getResourceId(dimensione,-1);
		}
		//Log.i("System.out", "MAIN:tutto bene con immagini");
		Reader readCarte = null;
        
        try {
        	//si cambierà qua quando 2 file carte o 2 file combo
        	if (lingua==false)
        	{
        		readCombo= new java.io.InputStreamReader(getAssets().open("Combos.txt"));
        		readCarte = new java.io.InputStreamReader(getAssets().open("Carte2.txt"));
        	}
        	else
        	{
        		readCombo= new java.io.InputStreamReader(getAssets().open("Combos.txt"));
        		readCarte = new java.io.InputStreamReader(getAssets().open("Cards.txt"));
        	}
        } catch (IOException e) {
			Log.i("System.out", "Eccezione lettura file");
			e.printStackTrace();
		}
        MyFile fileCarte=new MyFile(readCarte,databaseImmagini);
        databaseCarta=fileCarte.getDatabaseCarte();
        //Log.i("System.out", "MAIN:databasecarta:"+databaseCarta.size());
        MyFileCombo fileCombo=new MyFileCombo(readCombo,databaseCarta,databaseImmagini);
        databaseCombo=fileCombo.getDatabaseCombo();



        for(Carta c: databaseCarta)
        {
            for(Combo comb: databaseCombo)
            {
                for(Carta cart : comb.getCarte())
                {
                    if(c.getNome().equalsIgnoreCase(cart.getNome()))
                    {
                        c.setCombo(comb);
                    }
                }
            }
        }






		mTitle = mDrawerTitle = getTitle();
		// load slide menu items
		navMenuTitles = (lingua==false) ? getResources().getStringArray(R.array.nav_drawer_itemsIta) : getResources().getStringArray(R.array.nav_drawer_items);
		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		//Log.i("System.out","Qui");
		// Homepage
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// ListOfCards
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), true, "41"));
		// Combos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), true, "89"));
		//Baia
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Option
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		//Creation
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5],navMenuIcons.getResourceId(5,-1), true, "NEW"));
		//Tower
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6],navMenuIcons.getResourceId(6,-1), true, "NEW"));
		
		// Recycle the typed array
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};	
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener 
		{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (lingua==false) getMenuInflater().inflate(R.menu.maineng, menu);
		else getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		//boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);	
		
		menu.findItem(R.id.FiltroStelleC).setVisible(false);
		menu.findItem(R.id.FiltroManaC).setVisible(false);
		menu.findItem(R.id.FiltroHpC).setVisible(false);
		menu.findItem(R.id.FiltroAtkC).setVisible(false);
		menu.findItem(R.id.FiltroStelleD).setVisible(false);
		menu.findItem(R.id.FiltroManaD).setVisible(false);
		menu.findItem(R.id.FiltroHpD).setVisible(false);
		menu.findItem(R.id.FiltroAtkD).setVisible(false);
		menu.findItem(R.id.FiltroAlfabeticoC).setVisible(false);
		menu.findItem(R.id.FiltroAlfabeticoD).setVisible(false);
        menu.findItem(R.id.CercoCarta).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}

	//NAVIGATION DISPLAY
	
	private void displayView(int position) {
		Fragment fragment = null;
			
		switch (position) {
		case 0:
			fragment = new HomeFragment(lingua);
			break;
		case 1:

	        fragment = new ListOfCardsFragment2(databaseCarta,databaseImmagini,lingua);
	        
			break;
		case 2:

			fragment = new CombosFragment(databaseCombo,databaseCarta,lingua);
			break;
		case 3:
			fragment=new ArenaFragment(lingua);
			break;
		case 4:
			fragment = new OptionsFragment(lingua);
			break;
        case 5:
            fragment=new CreationFragment(databaseCarta,databaseImmagini,lingua);
            break;
        case 6:
                fragment=new TowerFragment(lingua,databaseImmaginiTorre);
                break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
    public void onBackPressed() {
        if (exit)
            MainActivity.this.finish();
        else {
            Toast.makeText(this, (lingua==false) ? "Premere Back di nuovo per uscire" :"Press Back again to exit",Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }




}
