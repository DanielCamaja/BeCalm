package becalm.com.becalm;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    ImageButton btnplayfast, btnplayslow;
    MediaPlayer mp0, mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnplayfast = (ImageButton) findViewById(R.id.btnMusicFast);
        btnplayslow = (ImageButton) findViewById(R.id.btnMusicSlow);

        mp0 = MediaPlayer.create(this,R.raw.primero);
        mp1 = MediaPlayer.create(this,R.raw.segundo);
        btnplayfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp0.isPlaying()) {
                    mp0.pause();
                    btnplayfast.setBackgroundResource(R.drawable.fast_button);
                    Toast.makeText(Main3Activity.this, "pausa", Toast.LENGTH_SHORT).show();
                } else {
                    mp0.start();
                    btnplayfast.setBackgroundResource(R.drawable.fast_button);
                }
            }
        });


        btnplayslow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp1.isPlaying()){
                    mp1.pause();
                    btnplayslow.setBackgroundResource(R.drawable.slow_button);
                    Toast.makeText(Main3Activity.this, "pausa", Toast.LENGTH_SHORT).show();
                }else{
                    mp1.start();
                    btnplayslow.setBackgroundResource(R.drawable.slow_button);
                }


            }
        });
    }

}