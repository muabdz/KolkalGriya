package muadz.kolkalgriya;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements View.OnClickListener{
ImageView iPro, iPro2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        iPro = (ImageView) findViewById(R.id.iPro);
        iPro2 = (ImageView) findViewById(R.id.iPro2);

        iPro.setOnClickListener(this);
        iPro2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iPro:
                Toast.makeText(Profile.this,
                        "Varian rasa Kolkal Griya", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iPro2:
                Toast.makeText(Profile.this,
                        "Eny, Owner Kolkal Griya", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
