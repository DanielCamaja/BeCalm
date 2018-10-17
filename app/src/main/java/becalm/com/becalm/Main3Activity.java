package becalm.com.becalm;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    Button btnplay, btnplay2;
    MediaPlayer mp, mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnplay = (Button) findViewById(R.id.btnMusciCcalm);
        btnplay2 = (Button) findViewById(R.id.btnMusicUpbeat);


        mp = MediaPlayer.create(this,R.raw.primero);
        mp2 = MediaPlayer.create(this,R.raw.segundo);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()){
                    mp.pause();
                    btnplay.setBackgroundResource(R.drawable.fastbutton);
                    Toast.makeText(Main3Activity.this, "pausa", Toast.LENGTH_SHORT).show();
                }else{
                    mp.start();
                    btnplay.setBackgroundResource(R.drawable.fastbutton);
                }

            }
        });

        btnplay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp2.isPlaying()){
                    mp2.pause();
                    btnplay2.setBackgroundResource(R.drawable.slowbutton);
                    Toast.makeText(Main3Activity.this, "pausa", Toast.LENGTH_SHORT).show();
                }else{
                    mp2.start();
                    btnplay2.setBackgroundResource(R.drawable.slowbutton);
                }


            }
        });
    }

}
