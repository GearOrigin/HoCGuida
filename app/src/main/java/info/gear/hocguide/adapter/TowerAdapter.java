package info.gear.hocguide.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.gear.hocguide.R;
import info.gear.hocguide.model.Tower;

/**
 * Created by marco on 27/03/2015.
 */
public class TowerAdapter extends ArrayAdapter {

    //DATI
    private final Activity context;
    private final Tower[] torre;
    private Tower selezionata;
    private boolean lingua=false;

    public TowerAdapter(Activity context, Tower[] torre, boolean lingua)
    {
        super(context, R.layout.simplerow,torre);
        this.lingua=lingua;
        this.context = context;
        this.torre=torre;
    }


    static class ViewHolder {
        TextView txtPiano;
        TextView altreInfo;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        selezionata=this.torre[position];

        if (convertView == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            convertView= inflater.inflate(R.layout.simplerowtorre, null, true);

            holder = new ViewHolder();
            holder.txtPiano = (TextView) convertView.findViewById(R.id.textPiano);
            holder.altreInfo = (TextView) convertView.findViewById(R.id.altreInfo);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtPiano.setText(""+(selezionata.getNum_piano()+1));
        holder.altreInfo.setText(settaInfo(selezionata));


        return convertView;
    }

    private String settaInfo(Tower selezionata) {
        if (lingua==false) {
            StringBuilder sb = new StringBuilder("");
            sb.append("" + selezionata.getNome().toUpperCase() + "\n");
            sb.append("Prezzo: " + selezionata.getPrezzo() + "\n");
            sb.append("Premi: " + selezionata.getListaRewards());
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder("");
            sb.append("" + selezionata.getNome().toUpperCase() + "\n");
            sb.append("Entry: " + selezionata.getPrezzo() + "\n");
            sb.append("Possible Rewards:"+selezionata.getListaRewards());
            return sb.toString();
        }
    }
    private ArrayList<String> splittaStringa(String s) {
        ArrayList<String> result=new ArrayList<String>() ;
        String[] temp=s.split(",");
        for(String ss:temp) result.add(ss);
        return  result;
    }


}