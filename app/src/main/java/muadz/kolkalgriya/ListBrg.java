package muadz.kolkalgriya;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBrg extends ArrayAdapter<String> {
    private String[] rasas;
    private String kets;
    private int[] imgrasa;
    private Activity context;
    private String[] jum;

    public ListBrg(Activity context, String[] rasas, String kets, int[] imgrasa, String[] jum) {
        super(context, R.layout.listbrg, rasas);
        this.context = context;
        this.rasas = rasas;
        this.kets = kets;
        this.imgrasa = imgrasa;
        this.jum = jum;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listbrg, null, true);
        TextView tvNama = (TextView) listViewItem.findViewById(R.id.tvNama);
        TextView tvKet = (TextView) listViewItem.findViewById(R.id.tvKet);
        ImageView ivRasa = (ImageView) listViewItem.findViewById(R.id.ivRasa);
        TextView tvJum = (TextView) listViewItem.findViewById(R.id.tvJum);
        //if (PreferenceManager.getDefaultSharedPreferences(context).getInt(rasas[position],0)!=0){
        tvNama.setText(rasas[position]);
        tvKet.setText(kets);
        tvJum.setText(jum[position]);
        ivRasa.setImageResource(imgrasa[position]);
        /*}else{
            listViewItem.setVisibility(View.GONE);
        }*/
        return  listViewItem;
    }
}
