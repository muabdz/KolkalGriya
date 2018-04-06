package muadz.kolkalgriya;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Konfirmasi extends AppCompatActivity {
String total, userid, tempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        total = Integer.toString(PreferenceManager.getDefaultSharedPreferences(Konfirmasi.this).getInt("total", 0));
        userid = PreferenceManager.getDefaultSharedPreferences(Konfirmasi.this).getString("userid", "kosong");
        tempo = PreferenceManager.getDefaultSharedPreferences(Konfirmasi.this).getString("tempo", "kosong");

        TextView tvTotal = (TextView) findViewById(R.id.tvTotal);
        TextView tvBerita = (TextView) findViewById(R.id.tvBerita);
        TextView tvTempo = (TextView) findViewById(R.id.tvTempo);

        tvTotal.append(total);
        tvBerita.setText(userid);
        tvTempo.setText(tempo);
    }
}
