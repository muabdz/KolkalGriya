package muadz.kolkalgriya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity implements View.OnClickListener{
public Button bGallery, bProfile, bProducts;
    String namaUser;
    private ProgressDialog pDialog;
    static Home Homie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Homie = this;
        setContentView(R.layout.activity_home);

        bProducts = (Button) findViewById(R.id.bProducts);
        bProfile = (Button) findViewById(R.id.bProfile);
        bGallery = (Button) findViewById(R.id.bGallery);

        bProducts.setOnClickListener(this);
        bProfile.setOnClickListener(this);
        bGallery.setOnClickListener(this);

        namaUser = PreferenceManager.getDefaultSharedPreferences(Home.this).getString("userid", "belumAda");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Mohon tunggu...");
        pDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.bProducts):
                showpDialog();
                String stats = PreferenceManager.getDefaultSharedPreferences(Home.this).getString("status","belumAda");

                Response.Listener<String> respon = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //boolean success = jsonResponse.getBoolean("success");

                            //if (success){
                                String statusBayar = jsonResponse.getString("status");
                                if (statusBayar.equals("belum")){
                                    Intent mov = new Intent(Home.this, Konfirmasi.class);
                                    startActivity(mov);}
                                else{
                                    PreferenceManager.getDefaultSharedPreferences(Home.this).edit().putString("status", "sudah").commit();
                                    Intent prod = new Intent(Home.this,Products.class);
                                    startActivity(prod);
                                }
                                //LOOP 2X
                           /* }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                                builder.setMessage("Terjadi Kesalahan")
                                        .setNegativeButton("Coba Lagi",null)
                                        .create()
                                        .show();
                            }*/

                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                            builder.setMessage("Terjadi Kesalahan")
                                    .setNegativeButton("Coba Lagi",null)
                                    .create()
                                    .show();
                            e.printStackTrace();
                        }

                    }
                };
                if (namaUser.equals("belumAda")){
                    Intent prod = new Intent(this,Products.class);
                    startActivity(prod);}
                else{
                    link Link = new link(namaUser, respon);
                    RequestQueue queue = Volley.newRequestQueue(Home.this);
                    queue.add(Link);
                    /*if (statusBayar.equals("belum")){
                        Intent mov = new Intent(this, Konfirmasi.class);
                        startActivity(mov);}
                    else{
                        Intent prod = new Intent(this,Products.class);
                        startActivity(prod);
                    }*/
                }
                hidepDialog();
                break;



            case (R.id.bProfile):
                Intent prof = new Intent(this,Profile.class);
                startActivity(prof);
                break;

            case (R.id.bGallery):
                Intent gal = new Intent(this,Gallery.class);
                startActivity(gal);
                break;
        }

    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public static Home getInstance(){
        return Homie;
    }
}
