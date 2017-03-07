package info.gear.hocguide;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;

import info.gear.hocguide.adapter.TowerAdapter;
import info.gear.hocguide.model.MyTower;
import info.gear.hocguide.model.Tower;

/**
 * Created by marco on 27/03/2015.
 */
public class TowerFragment extends Fragment {

    private boolean lingua=false;
    protected Integer[] databaseImmaginiTorre;
    private Tower[] torreIntera;
    private TowerAdapter adapter;

    public TowerFragment() {
    }

    public TowerFragment(boolean lingua, Integer[] databaseImmaginiTorre) {
        this.lingua=lingua;
        this.databaseImmaginiTorre=databaseImmaginiTorre;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyTower tt;
        try
        {
            if (lingua)
                tt=new MyTower(new java.io.InputStreamReader(getActivity().getAssets().open("Tower.txt")),databaseImmaginiTorre);
            else
                tt=new MyTower(new java.io.InputStreamReader(getActivity().getAssets().open("Torre.txt")),databaseImmaginiTorre);
        }
        catch (IOException e) {throw new IllegalArgumentException("Errore data torre");}

        torreIntera=tt.getDataTorreInArray();

        final View rootView = inflater.inflate(R.layout.fragment_tower, container, false);
        ListView listaTorre = (ListView) rootView.findViewById(R.id.listViewtower);

        adapter=new TowerAdapter(getActivity(),torreIntera,lingua);
        listaTorre.setAdapter(adapter);
        listaTorre.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Tower c =(Tower) adapterView.getItemAtPosition(i);
                if (!c.isMyFuckingTreasure())
                {
                    Dialog dialog = new Dialog(rootView.getContext());
                    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    // layout to display
                    dialog.setContentView(R.layout.dialog_layout);

                    // set color transpartent
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ImageView image=(ImageView) dialog.findViewById(R.id.dialog);
                    image.setImageDrawable(getResources().getDrawable(c.getImmagineGiusta()));
                    dialog.show();
                }
            }
        });
        return rootView;
    }
}
