package muadz.kolkalgriya;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order extends AppCompatActivity implements View.OnClickListener{
EditText etNama, etAlamat,etNohp, etEmail;
    private ProgressDialog pDialog;
    int jumlah, Total;
    int[] oodaa ={0,0,0,0,0,0,0};
    String tgl_trans, id_pro, id_user, status, id_trans, tempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        etNama = (EditText) findViewById(R.id.etNama);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etNohp = (EditText) findViewById(R.id.etNohp);
        etEmail = (EditText) findViewById(R.id.etEmail);
        Button bPesan = (Button) findViewById(R.id.bPesan);

        bPesan.setOnClickListener(this);


        oodaa[0] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("Leci",0);
        oodaa[1] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("Anggur",0);
        oodaa[2] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("Cocopandan",0);
        oodaa[3] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("TehHijau",0);
        oodaa[4] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("GulaBatu",0);
        oodaa[5] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("Original",0);
        oodaa[6] = PreferenceManager.getDefaultSharedPreferences(Order.this).getInt("Pandan",0);

        Intent intento = getIntent();
        Total = intento.getIntExtra("total", 0);

        /*Intent bagikan = getIntent();
        oodaa[0] = bagikan.getIntExtra("Leci",0);
        oodaa[1] = bagikan.getIntExtra("Anggur",0);
        oodaa[2] = bagikan.getIntExtra("Cocopandan",0);
        oodaa[3] = bagikan.getIntExtra("Teh Hijau",0);
        oodaa[4] = bagikan.getIntExtra("Gula Batu",0);
        oodaa[5] = bagikan.getIntExtra("Original",0);
        oodaa[6] = bagikan.getIntExtra("Pandan",0);*/

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Mohon tunggu...");
        pDialog.setCancelable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bPesan:

                final String nama = etNama.getText().toString();
                final String alamat = etAlamat.getText().toString();
                final String nohp = etNohp.getText().toString();
                final String email = etEmail.getText().toString();
                if (nama == null || alamat == null ||email == null ){
                AlertDialog.Builder infoo = new AlertDialog.Builder(Order.this);
                infoo.setMessage("Masukkan data dengan benar").setCancelable(false).setPositiveButton("Coba Lagi", new AlertDialog.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
                AlertDialog dialog1 = infoo.create();
                dialog1.setTitle("Error");
                dialog1.show();
                }else if (nohp.length()<10){
                    AlertDialog.Builder infoo = new AlertDialog.Builder(Order.this);
                    infoo.setMessage("Masukkan Nomor Handphone dengan benar").setCancelable(false).setPositiveButton("Coba Lagi", new AlertDialog.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog1 = infoo.create();
                    dialog1.setTitle("Error");
                    dialog1.show();
                }else{
                    AlertDialog.Builder info = new AlertDialog.Builder(Order.this);
                    info.setMessage("Apakah Anda Yakin?").setCancelable(true).setPositiveButton("Ya", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                Order.this.showpDialog();
                                Response.Listener<String> respon = new Response.Listener<String>(){

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);

                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("Leci", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("Anggur", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("Cocopandan", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("TehHijau", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("GulaBatu", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("Original", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("Pandan", 0).commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putString("status", "belum").commit();
                                            PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putInt("total", Total).commit();
                                            Order.this.hidepDialog();
                                            Intent intent = new Intent(Order.this, Konfirmasi.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            Order.this.startActivity(intent);
                                            Order.this.finish();
                                            cobacart.getInstance().finish();
                                            Products.getInstance().finish();
                                            Home.getInstance().recreate();
                                            Toast.makeText(Order.this,
                                                    "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Order.this.hidepDialog();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Order.this);
                                            builder.setMessage("Pemesanan gagal")
                                                    .setNegativeButton("Coba Lagi",null)
                                                    .create()
                                                    .show();
                                        }

                                    }

                                };


                                for(int i=0; i<7; i++){
                                    if (oodaa[i]>0){
                                        switch (i){
                                            case 0:
                                                id_pro = "p00011";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                            case 1:
                                                id_pro = "p00012";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                            case 2:
                                                id_pro = "p00013";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                            case 3:
                                                id_pro = "p00017";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                            case 4:
                                                id_pro = "p00016";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                            case 5:
                                                id_pro = "p00015";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                            case 6:
                                                id_pro = "p00014";
                                                cobaan(i, nama, nohp, alamat, email, respon);
                                                break;
                                        }
                                        PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putString("status", status).apply();
                                    }
                                }
                            }catch (NullPointerException e){
                                AlertDialog.Builder infoo = new AlertDialog.Builder(Order.this);
                                infoo.setMessage("Masukkan data dengan benar").setCancelable(false).setPositiveButton("Coba Lagi", new AlertDialog.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog1 = infoo.create();
                                dialog1.setTitle("Error");
                                Order.this.hidepDialog();;
                                dialog1.show();
                            }
                        }
                    }).setNegativeButton("Tidak", new AlertDialog.OnClickListener(){
                        public void onClick(DialogInterface dialog2, int id){
                            dialog2.cancel();
                        }
                    });
                    AlertDialog dialog = info.create();
                    dialog.setTitle("Konfirmasi");
                    Order.this.hidepDialog();
                    dialog.show();
                }
            break;}

        }

    public void cobaan(int i, String nama, String nohp, String alamat, String email, Response.Listener<String> respon){
        id_user = nama+nohp.substring(nohp.length()/2,(nohp.length()/2)+2);
        PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putString("userid", id_user).commit();
        jumlah = oodaa[i];
        status = "belum";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat dff = new SimpleDateFormat("ddMMHHmmss");
        tgl_trans = df.format(c.getTime());
        id_trans = dff.format(c.getTime())+id_pro.substring(5,6);
        c.add(Calendar.DATE, 4);
        tempo = df.format(c.getTime());
        PreferenceManager.getDefaultSharedPreferences(Order.this).edit().putString("tempo", tempo).commit();
        trans registerRequest = new trans(id_trans, tgl_trans, id_pro, jumlah, id_user,nama,nohp,alamat,email, status, respon);
        RequestQueue queue = Volley.newRequestQueue(Order.this);
        queue.add(registerRequest);

    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
