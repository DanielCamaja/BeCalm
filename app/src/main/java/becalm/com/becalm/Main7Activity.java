package becalm.com.becalm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main7Activity extends AppCompatActivity {

    TextView resultado;

    int lastScore;
    int best1, best2, best3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        resultado  = findViewById(R.id.resultado);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore", 0);


        resultado.setText("SCORE: " + lastScore);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Main7Activity.this, DistractActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    public void regresar(View view) {
        Intent intent01 = new Intent(this, DistractActivity.class);
        startActivity(intent01);
    }
}