package muadz.kolkalgriya;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class cobacart extends AppCompatActivity implements View.OnClickListener{
    int totaly,totale;
    TextView tvTot;
    private ListView listView;
    static cobacart killedOne;
    /*private String rasas2[] = {
            "Leci",
            "Anggur",
            "Cocopandan",
            "TehHijau",
            "GulaBatu",
            "Original",
            "Pandan"
    };
    String juml2[] = {
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "0"
    };
    private int imgrasa2[] = {
            R.drawable.leci,
            R.drawable.anggur,
            R.drawable.cocopandan,
            R.drawable.tehhijau,
            R.drawable.gulabatu,
            R.drawable.original,
            R.drawable.pandan
    };*/
    private String rasas[] = {
            "Leci",
            "Anggur",
            "Cocopandan",
            "TehHijau",
            "GulaBatu",
            "Original",
            "Pandan"
    };

    private String kets = "500g\nRp 40.000";


    private int imgrasa[] = {
            R.drawable.leci,
            R.drawable.anggur,
            R.drawable.cocopandan,
            R.drawable.tehhijau,
            R.drawable.gulabatu,
            R.drawable.original,
            R.drawable.pandan
    };
    String juml[] = {
            "0",
            "0",
            "0",
            "0",
            "0",
            "0",
            "0"
    };
    String Leci, Anggur, Cocopandan, TehHijau, GulaBatu, Original, Pandan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobacart);
        killedOne = this;
        tvTot = (TextView) findViewById(R.id.tvTot);
        Button bLanjutkan = (Button) findViewById(R.id.bLanjutkan);
        bLanjutkan.setOnClickListener(this);
        Leci = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("Leci",0));
        Anggur = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("Anggur",0));
        Cocopandan = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("Cocopandan",0));
        TehHijau = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("TehHijau",0));
        GulaBatu = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("GulaBatu",0));
        Original = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("Original",0));
        Pandan = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt("Pandan",0));

        totaly = (Integer.parseInt(Leci)+
                Integer.parseInt(Anggur)+
                Integer.parseInt(Cocopandan)+
                Integer.parseInt(TehHijau)+
                Integer.parseInt(GulaBatu)+
                Integer.parseInt(Original)+
                Integer.parseInt(Pandan))*40000;
        tvTot.setText(totaly+"");

        juml[0] = Leci;
        juml[1] = Anggur;
        juml[2] = Cocopandan;
        juml[3] = TehHijau;
        juml[4] = GulaBatu;
        juml[5] = Original;
        juml[6] = Pandan;
        /*int b=0;
        int c=6;
        for (int a = 0; a<7;a++){
            if (Integer.parseInt(juml[a])>0) {
                juml2[b] = juml[a];
                rasas2[b] = rasas[a];
                imgrasa2[b] = imgrasa[a];
                b++;
            }else{
                juml2[c] = juml[a];
                rasas2[c] = rasas[a];
                imgrasa2[c] = imgrasa[a];
                c--;
            }
        }*/



        final ListBrg listBrg = new ListBrg(this, rasas, kets, imgrasa, juml);

        listView = (ListView) findViewById(R.id.lvCart);
        listView.setAdapter(listBrg);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                switch (i){
                    case 0:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;
                    case 1:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;
                    case 2:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;
                    case 3:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;
                    case 4:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;
                    case 5:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;
                    case 6:
                        add(i);
                        //add2();
                        listBrg.notifyDataSetChanged();
                        break;

                }

            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLanjutkan:
                totale = Integer.parseInt(tvTot.getText().toString());
                final Intent intento = new Intent(this, Order.class);
                if (totale > 0) {
                    AlertDialog.Builder info = new AlertDialog.Builder(cobacart.this);
                    info.setMessage("Apakah Anda Yakin?").setCancelable(true).setPositiveButton("Ya", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            intento.putExtra("total", totale);
                            startActivity(intento);
                            finish();
                        }
                    }).setNegativeButton("Tidak", new AlertDialog.OnClickListener(){
                        public void onClick(DialogInterface dialog2, int id){
                            dialog2.cancel();
                        }
                    });
                    AlertDialog dialog = info.create();
                    dialog.setTitle("Konfirmasi");
                    dialog.show();
                }else {
                    AlertDialog.Builder infoo = new AlertDialog.Builder(cobacart.this);
                    infoo.setMessage("Keranjang Kosong").setCancelable(false).setPositiveButton("Coba Lagi", new AlertDialog.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog1 = infoo.create();
                    dialog1.setTitle("Error");
                    dialog1.show();
                }
                break;
        }
    }
    public void add(final int i) {
        final EditText jum = new EditText(cobacart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        jum.setLayoutParams(lp);
        jum.setHint("Ubah jumlah yang dipesan");
        jum.setInputType(InputType.TYPE_CLASS_NUMBER);

        AlertDialog.Builder info = new AlertDialog.Builder(cobacart.this);
        info.setMessage(rasas[i]).setCancelable(true).setPositiveButton("Ubah", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    int jumlah = Integer.parseInt(jum.getText().toString());
                    PreferenceManager.getDefaultSharedPreferences(cobacart.this).edit().putInt(rasas[i], jumlah).commit();
                    /*if (Integer.parseInt(juml2[i])==0) {
                        int z=i+1;
                        while(z<7){
                            juml2[z-1] = juml[z];
                            rasas2[z-1] = rasas[z];
                            imgrasa2[z-1] = imgrasa[z];
                            z++;
                        }
                    }*/
                    totaly(i, jumlah
                            /*PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(Leci,0),
                            PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(Anggur,0),
                            PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(Cocopandan,0),
                            PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(TehHijau,0),
                            PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(GulaBatu,0),
                            PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(Original,0),
                            PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(Pandan,0)*/
                            );
                    juml[i] = Integer.toString(PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[i],0));
                    Toast.makeText(cobacart.this,
                            "Pesanan Diubah", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    AlertDialog.Builder infoo = new AlertDialog.Builder(cobacart.this);
                    infoo.setMessage("Masukkan jumlah pesanan").setCancelable(false).setPositiveButton("Coba Lagi", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog1 = infoo.create();
                    dialog1.setTitle("Error");
                    dialog1.show();
                }
            }
        }).setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog2, int id) {
                dialog2.cancel();
            }
        });
        AlertDialog dialog = info.create();
        dialog.setView(jum);
        dialog.setTitle("Edit Cart");
        dialog.show();
    }
   TextView totaly(int i, int jumlah){
       PreferenceManager.getDefaultSharedPreferences(cobacart.this).edit().putInt(rasas[i], jumlah).commit();
       int Lecis = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[0],0);
       int Anggurs = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[1],0);
       int Cocopandans = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[2],0);
       int TehHijaus = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[3],0);
       int GulaBatus = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[4],0);
       int Originals = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[5],0);
       int Pandans = PreferenceManager.getDefaultSharedPreferences(cobacart.this).getInt(rasas[6],0);
        totaly = (Lecis+
                Anggurs+
                Cocopandans+
                TehHijaus+
                GulaBatus+
                Originals+
                Pandans)*40000;
        tvTot.setText(totaly+"");
     return tvTot;
    }
    public static cobacart getInstance(){
        return killedOne;
    }

    /*TextView totaly(int Leci,
                    int Anggur,
                    int Cocopandan,
                    int TehHijau,
                    int GulaBatu,
                    int Original,
                    int Pandan){
        totaly = (Leci+
                Anggur+
                Cocopandan+
                TehHijau+
                GulaBatu+
                Original+
                Pandan)*40000;
        tvTot.setText(totaly+"");
        return tvTot;
    }*/
   /* public void add2(){
        int b=0;
        int c=6;
        for (int a = 0; a<7;a++){
            if (Integer.parseInt(juml[a])>0) {
                juml2[b] = juml[a];
                rasas2[b] = rasas[a];
                imgrasa2[b] = imgrasa[a];
                b++;
            }else{
                juml2[c] = juml[a];
                rasas2[c] = rasas[a];
                imgrasa2[c] = imgrasa[a];
                c--;
            }
        }
    }*/
}
