package becalm.com.becalm;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {


    MediaPlayer mp0, mp1;
    int pauset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


    }

    public void slowmusic(View view) {
     if (mp0 == null){
         mp0 = MediaPlayer.create(this, R.raw.primero);
         mp0.start();
         Toast.makeText(this, "inico", Toast.LENGTH_SHORT).show();
     }else if (!mp0.isPlaying()){
         mp0.seekTo(pauset);
         mp0.start();
         Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
     }

    }

    public void fastmusic(View view) {
        if (mp1 == null){
            mp1 = MediaPlayer.create(this, R.raw.segundo);
            mp1.start();
            Toast.makeText(this, "inico", Toast.LENGTH_SHORT).show();
        }else if (!mp1.isPlaying()){
            mp1.seekTo(pauset);
            mp1.start();
            Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause(View view) {
        if (mp1 !=null){
            mp1.pause();
            pauset = mp1.getCurrentPosition();
            Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        }else if (mp0 !=null){
            mp0.pause();
            pauset = mp0.getCurrentPosition();
            Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View view) {

        if (mp1 !=null){
            mp1.stop();
            mp1 = null;
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        }else if (mp0 !=null){
            mp0.stop();
            mp0 = null;
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        }
    }

}