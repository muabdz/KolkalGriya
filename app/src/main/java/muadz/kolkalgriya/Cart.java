package muadz.kolkalgriya;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

import static android.R.attr.right;
import static muadz.kolkalgriya.R.id.info;

public class Cart extends AppCompatActivity implements View.OnClickListener{
public Button bUbah, bLanjut;
    int angka, i, Harga, Total, total;
    int[] pesanan={0,0,0,0,0,0,0};
    String ras, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        bUbah = (Button) findViewById(R.id.bUbah);
        bLanjut = (Button) findViewById(R.id.bLanjut);

        bUbah.setOnClickListener(this);
        bLanjut.setOnClickListener(this);

        Harga = 40000;
        Total = 0;
        for (i = 0;i<7;i++){
            switch (i) {
                case 0:
                    ras = "Leci";
                    adda();
                    break;
                case 1:
                    ras = "Anggur";
                    adda();
                    break;
                case 2:
                    ras = "Cocopandan";
                    adda();
                    break;
                case 3:
                    ras = "Teh Hijau";
                    adda();
                    break;
                case 4:
                    ras = "Gula Batu";
                    adda();
                    break;
                case 5:
                    ras = "Original";
                    adda();
                    break;
                case 6:
                    ras = "Pandan";
                    adda();
                    break;
            }
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        TextView tvTotal = new TextView(this);
        tvTotal.setLayoutParams(lp);
        tvTotal.setText("\n\nTotal: "+(Total*40000));
        tvTotal.setGravity(right);

    }

    public void adda(){
        //pesanan[i] = getPreferences(MODE_PRIVATE).getInt(ras,0);
        /*Intent bagikan = getIntent();
        pesanan[i] = bagikan.getIntExtra(ras, 0);
        Intent bagilagi = new Intent(Cart.this, Order.class);
        bagilagi.putExtra(ras,pesanan[i]);*/
        if (pesanan[i]>0){
            Total = Total+pesanan[i];
            String[] a= Arrays.toString(pesanan).split("[\\[\\]]")[1].split(", ");
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            TextView tvPro = new TextView(this);
            TextView tvHar = new TextView(this);
            TextView tvJum = new TextView(this);
            TextView tvTot = new TextView(this);

            tvPro.setLayoutParams(lp);
            tvPro.setText(ras);

            tvHar.setLayoutParams(lp);
            tvHar.setText("40000");

            tvJum.setLayoutParams(lp);
            tvJum.setText(a[i]);

            total = Harga*pesanan[i];
            b = Integer.toString(total);
            tvTot.setLayoutParams(lp);
            tvTot.setText(b);

            layout.addView(tvPro);
            layout.addView(tvHar);
            layout.addView(tvJum);
            layout.addView(tvTot);
            setContentView(layout);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bUbah:
                AlertDialog.Builder info = new AlertDialog.Builder(Cart.this);
                info.setMessage("Ubah Pesanan").setCancelable(true).setPositiveButton("Ubah", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for (int i=0;i<=6;i++){
                            angka = pesanan[i];
                            switch (i){
                                case 0:

                                case 1:

                                case 2:

                                case 3:

                                case 4:

                                case 5:

                                case 6:

                            }
                        }
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = info.create();
                final EditText jum = new EditText(Cart.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                jum.setLayoutParams(lp);
                jum.setHint("Jumlah yang dipesan");
                jum.setInputType(2);
                dialog.setView(jum);
                dialog.setTitle("Add to Cart");
                dialog.show();
                break;

            case R.id.bLanjut:
                Intent intent = new Intent(this, cobacart.class);
                startActivity(intent);
                break;
        }
    }

    /*public void ubahPesanan(int Jum, String ras){

    }*/
}
