package muadz.kolkalgriya;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class Gallery extends AppCompatActivity {
String ket;
    int Src;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gridview = (GridView) findViewById(R.id.gvGallery);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position){
                    case 0:
                        Src = R.drawable.gal1;
                        ket = getString(R.string.gall1);
                        zum();
                        break;
                    case 1:
                        Src = R.drawable.gal2;
                        ket = getString(R.string.gall2);
                        zum();
                        break;
                    case 2:
                        Src = R.drawable.gal3;
                        ket = getString(R.string.gall3);
                        zum();
                        break;
                    case 3:
                        Src = R.drawable.gal4;
                        ket = getString(R.string.gall4);
                        zum();
                        break;
                    case 4:
                        Src = R.drawable.gal5;
                        ket = getString(R.string.gall5);
                        zum();
                        break;
                    case 5:
                        Src = R.drawable.gal6;
                        ket = getString(R.string.gall6);
                        zum();
                        break;
                    case 6:
                        Src = R.drawable.gal7;
                        ket = getString(R.string.gall7);
                        zum();
                        break;
                    case 7:
                        Src = R.drawable.gal8;
                        ket = getString(R.string.gall8);
                        zum();
                        break;
                    case 8:
                        Src = R.drawable.gal9;
                        ket = getString(R.string.gall9);
                        zum();
                        break;
                }
            }
        });
    }

    public void zum(){
        ImageView imeji = new ImageView(this);
        imeji.setImageResource(Src);
        imeji.setMaxHeight(800);
        imeji.setAdjustViewBounds(true);
        imeji.setPadding(8,8,8,8);
        AlertDialog.Builder infoo = new AlertDialog.Builder(Gallery.this);
        infoo.setMessage(ket).setCancelable(false).setPositiveButton("Kembali", new AlertDialog.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog dialog1 = infoo.create();
        dialog1.setView(imeji);
        dialog1.show();

    }
}
