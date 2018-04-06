package muadz.kolkalgriya;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Products extends AppCompatActivity implements View.OnClickListener{
public Button bLeci, bCocopandan, bAnggur, bOriginal, bTeh, bGula, bPandan, bCart;
    public String rasa;
    int[] pesan={0,0,0,0,0,0,0};
    int jumlah = 0;
    static Products killedTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        killedTwo = this;
        setContentView(R.layout.activity_products);

        bLeci = (Button) findViewById(R.id.bLeci);
        bPandan = (Button) findViewById(R.id.bPandan);
        bGula = (Button) findViewById(R.id.bGula);
        bCocopandan = (Button) findViewById(R.id.bCocopandan);
        bAnggur = (Button) findViewById(R.id.bAnggur);
        bOriginal = (Button) findViewById(R.id.bOriginal);
        bTeh = (Button) findViewById(R.id.bTeh);
        bCart = (Button) findViewById(R.id.bCart);

        bLeci.setOnClickListener(this);
        bGula.setOnClickListener(this);
        bAnggur.setOnClickListener(this);
        bCocopandan.setOnClickListener(this);
        bOriginal.setOnClickListener(this);
        bPandan.setOnClickListener(this);
        bTeh.setOnClickListener(this);
        bCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /*String uji = PreferenceManager.getDefaultSharedPreferences(Products.this).getString("status", "gatau");
        if (uji.equalsIgnoreCase("belum")){
            Intent intent = new Intent(Products.this, Konfirmasi.class);
            Products.this.startActivity(intent);
        }else{*/
            PreferenceManager.getDefaultSharedPreferences(Products.this).edit().putInt("total", 0).commit();
        switch (v.getId()){
            case R.id.bLeci:
                rasa = "Leci";
                add();
                pesan[0]=jumlah;
                break;
            case R.id.bAnggur:
                rasa = "Anggur";
                add();
                pesan[1]=jumlah;
                break;
            case R.id.bCocopandan:
                rasa = "Cocopandan";
                add();
                pesan[2]=jumlah;
                break;
            case R.id.bTeh:
                rasa = "TehHijau";
                add();
                pesan[3]=jumlah;
                break;
            case R.id.bGula:
                rasa = "GulaBatu";
                add();
                pesan[4]=jumlah;
                break;
            case R.id.bOriginal:
                rasa = "Original";
                add();
                pesan[5]=jumlah;
                break;
            case R.id.bPandan:
                rasa = "Pandan";
                add();
                pesan[6]=jumlah;
                break;

            case R.id.bCart:
                Intent caruto = new Intent(this, cobacart.class);
                startActivity(caruto);
                break;

        }}

    //}
    public void add(){
        final EditText jum = new EditText(Products.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        jum.setLayoutParams(lp);
        jum.setHint("Jumlah yang dipesan");
        jum.setInputType(InputType.TYPE_CLASS_NUMBER);

        AlertDialog.Builder info = new AlertDialog.Builder(Products.this);
        info.setMessage("Pesan "+rasa).setCancelable(true).setPositiveButton("Add", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try{
                jumlah = Integer.parseInt(jum.getText().toString());
                    PreferenceManager.getDefaultSharedPreferences(Products.this).edit().putInt(rasa, jumlah).commit();
                    //Intent berbagi = new Intent(Products.this,Order.class);
                    //berbagi.putExtra(rasa, jumlah);
                    //startActivity(berbagi);
                    Toast.makeText(Products.this,
                            "Pesanan Ditambahkan", Toast.LENGTH_SHORT).show();

                }catch (NumberFormatException e){
                    AlertDialog.Builder infoo = new AlertDialog.Builder(Products.this);
                    infoo.setMessage("Masukkan jumlah pesanan").setCancelable(false).setPositiveButton("Coba Lagi", new AlertDialog.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog1 = infoo.create();
                    dialog1.setTitle("Error");
                    dialog1.show();
                }
            }
        }).setNegativeButton("Cancel", new AlertDialog.OnClickListener(){
            public void onClick(DialogInterface dialog2, int id){
                dialog2.cancel();
            }
        });
        AlertDialog dialog = info.create();
        dialog.setView(jum);
        dialog.setTitle("Add to Cart");
        dialog.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {
            Intent intent = new Intent(this, cobacart.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Products getInstance(){
        return killedTwo;
    }
}
