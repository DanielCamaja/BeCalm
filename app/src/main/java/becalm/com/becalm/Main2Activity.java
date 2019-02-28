package becalm.com.becalm;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.Trace;

import androidx.appcompat.app.AppCompatActivity;
import becalm.com.becalm.intermedial.IntermedioFocus;
import becalm.com.becalm.intermedial.IntermedioRelax;
import becalm.com.becalm.intermedial.intermedio;
import becalm.com.becalm.intermedial.intermedio_distract;

public class Main2Activity extends AppCompatActivity {


    MediaPlayer mp2;
    private Button btn1,btn2,btn3,btn4;

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        Trace myTrace = FirebasePerformance.getInstance().newTrace("test_trace");
        myTrace.start();
        btn1 = (Button) findViewById(R.id.inspire);
        btn2 = (Button) findViewById(R.id.distrac);
        btn3 = (Button) findViewById(R.id.focus);
        btn4 = (Button) findViewById(R.id.relax);


        myTrace.stop();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,intermedio.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,intermedio_distract.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,IntermedioFocus.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,IntermedioRelax.class);
                startActivity(intent);
            }
        });

    }

    private void playMusic(){
        mp2 = MediaPlayer.create(this,R.raw.cancion_inicial);
        if (mp2.isPlaying()){
            mp2.pause();
        }else{
            mp2.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp2.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
