package becalm.com.becalm;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class FocusActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton anaranjado;
    ImageButton rojo;
    ImageButton salmon;
    ImageButton verde_lima;
    ImageButton verde_acua ;
    ImageButton azul_marino;
    ImageButton azul_verdoso;
    ImageButton amariilo_mostaza;

    private static Lienzo lienzo;
    //private ImageView lienzo;
    float small;
    float medium;
    float big;
    float pdefecto;

    ImageButton trazo;
    ImageButton anyadir;
    ImageButton borrar;
    ImageButton guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(FocusActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        anaranjado = (ImageButton)findViewById(R.id.colornaranja);
        rojo = (ImageButton)findViewById(R.id.colorrojo);
        salmon = (ImageButton)findViewById(R.id.colorsalmon);
        verde_lima = (ImageButton)findViewById(R.id.colorverdelima);
        verde_acua = (ImageButton)findViewById(R.id.colorverdeacua);
        azul_marino = (ImageButton)findViewById(R.id.colorazulmarino);
        azul_verdoso = (ImageButton)findViewById(R.id.colorazulverdoso);
        amariilo_mostaza = (ImageButton)findViewById(R.id.coloramarrillomostaza);

        trazo = (ImageButton)findViewById(R.id.trazo);
        anyadir = (ImageButton)findViewById(R.id.anyadir);
        borrar = (ImageButton)findViewById(R.id.borrar);
        guardar = (ImageButton)findViewById(R.id.guardar);

        anaranjado.setOnClickListener(this);
        rojo.setOnClickListener(this);
        salmon.setOnClickListener(this);
        verde_lima.setOnClickListener(this);
        verde_acua.setOnClickListener(this);
        azul_marino.setOnClickListener(this);
        azul_verdoso.setOnClickListener(this);
        amariilo_mostaza.setOnClickListener(this);


        trazo.setOnClickListener(this);
        anyadir.setOnClickListener(this);
        borrar.setOnClickListener(this);
        guardar.setOnClickListener(this);

        lienzo = (Lienzo)findViewById(R.id.lienzo);
        //lienzo =(ImageView) findViewById(R.id.lienzo);


        small= 10;
        medium= 20;
        big= 30;

        pdefecto= medium;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String color = null;


        switch (v.getId()){
            case R.id.colornaranja:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorrojo:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorsalmon:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorverdelima:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorverdeacua:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorazulmarino:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorazulverdoso:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.coloramarrillomostaza:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.trazo:
                final Dialog tamanyopunto = new Dialog(this);
                tamanyopunto.setTitle("Tamaño del punto:");
                tamanyopunto.setContentView(R.layout.tamanyo_punto);
                //listen for clicks on tamaños de los botones
                TextView smallBtn = (TextView)tamanyopunto.findViewById(R.id.tpequenyo);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(false);
                        Lienzo.setTamanyoPunto(small);

                        tamanyopunto.dismiss();
                    }
                });
                TextView mediumBtn = (TextView)tamanyopunto.findViewById(R.id.tmediano);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(false);
                        Lienzo.setTamanyoPunto(medium);

                        tamanyopunto.dismiss();
                    }
                });
                TextView largeBtn = (TextView)tamanyopunto.findViewById(R.id.tgrande);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(false);
                        Lienzo.setTamanyoPunto(big);

                        tamanyopunto.dismiss();
                    }
                });
                //show and wait for user interaction
                tamanyopunto.show();


                break;
            case R.id.anyadir:


                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("New Draw");
                newDialog.setMessage("Begin new drawing (will you lose the current drawing)?");
                newDialog.setPositiveButton("Agree", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        lienzo.NuevoDibujo();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                newDialog.show();


                break;
            case R.id.borrar:

                final Dialog borrarpunto = new Dialog(this);
                borrarpunto.setTitle("Tamaño de borrado:");
                borrarpunto.setContentView(R.layout.tamanyo_punto);
                //listen for clicks on tamaños de los botones
                TextView smallBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tpequenyo);
                smallBtnBorrar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(true);
                        Lienzo.setTamanyoPunto(small);

                        borrarpunto.dismiss();
                    }
                });
                TextView mediumBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tmediano);
                mediumBtnBorrar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(true);
                        Lienzo.setTamanyoPunto(medium);

                        borrarpunto.dismiss();
                    }
                });
                TextView largeBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tgrande);
                largeBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lienzo.setBorrado(true);
                        Lienzo.setTamanyoPunto(big);

                        borrarpunto.dismiss();
                    }
                });
                //show and wait for user interaction
                borrarpunto.show();


                break;
            case R.id.guardar:

                AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(this);
                salvarDibujo.setTitle("Save Drawing");
                salvarDibujo.setMessage("Save Drawing to the gallery? ");
                salvarDibujo.setPositiveButton("Agree", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){


                        //Salvar dibujo
                        lienzo.setDrawingCacheEnabled(true);
                        //attempt to save
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), lienzo.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        //Mensaje de todo correcto
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "Drawing saved in the gallery!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Error! The image could not be saved.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        lienzo.destroyDrawingCache();


                    }
                });
                salvarDibujo.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                salvarDibujo.show();

                break;
            default:

                break;
        }
    }

}