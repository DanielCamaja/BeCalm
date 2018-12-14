package becalm.com.becalm;

import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    private Button btn_color,btn_color1,btn_color2,btn_color3,btn_color4,btn_color5,btn_color6,btn_color7,btn_color8,btn_color10,btn_color11,btn_color12,btn_color9;

    MediaPlayer mp,mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btn_color = findViewById(R.id.button0);
        btn_color1 = findViewById(R.id.button1);
        btn_color2 = findViewById(R.id.btncolor2);
        btn_color3 = findViewById(R.id.btncolor3);
        btn_color4 = findViewById(R.id.btncolor4);
        btn_color5 = findViewById(R.id.btncolor5);
        btn_color6 = findViewById(R.id.btncolor6);
        btn_color7 = findViewById(R.id.btncolor7);
        btn_color8 = findViewById(R.id.btncolor8);
        btn_color10 = findViewById(R.id.btncolor10);
        btn_color11 = findViewById(R.id.btncolor11);
        btn_color12 = findViewById(R.id.btncolor12);
        btn_color9 = findViewById(R.id.btncolor9);


        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        int width = size.x;
        int Height = size.y;
        final int cellSizeX = (int)(width / 3.0);
        final int cellSizeY = (int)(Height / 4.19);
        btn_color.setWidth(cellSizeX);
        btn_color.setHeight(cellSizeY);
        btn_color1.setWidth(cellSizeX);
        btn_color1.setHeight(cellSizeY);
        btn_color2.setWidth(cellSizeX);
        btn_color2.setHeight(cellSizeY);
        btn_color3.setWidth(cellSizeX);
        btn_color3.setHeight(cellSizeY);
        btn_color4.setWidth(cellSizeX);
        btn_color4.setHeight(cellSizeY);
        btn_color5.setWidth(cellSizeX);
        btn_color5.setHeight(cellSizeY);
        btn_color6.setWidth(cellSizeX);
        btn_color6.setHeight(cellSizeY);
        btn_color7.setWidth(cellSizeX);
        btn_color7.setHeight(cellSizeY);
        btn_color8.setWidth(cellSizeX);
        btn_color8.setHeight(cellSizeY);
        btn_color10.setWidth(cellSizeX);
        btn_color10.setHeight(cellSizeY);
        btn_color11.setWidth(cellSizeX);
        btn_color11.setHeight(cellSizeY);
        btn_color12.setWidth(cellSizeX);
        btn_color12.setHeight(cellSizeY);


        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color);
            }
        });

        btn_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color1);

            }
        });
        btn_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color2);

            }
        });
        btn_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color3);

            }
        });
        btn_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color4);

            }
        });
        btn_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color5);

            }
        });

        btn_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color6);

            }
        });
        btn_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color7);

            }
        });
        btn_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color8);

            }
        });
        btn_color9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //colorRandomForButton(btn_color7);
                int randX = (int)(Math.random()*3);
                int randY = (int)(Math.random()*4);
                btn_color9.setX(randX*cellSizeX);
                btn_color9.setY(randY*cellSizeY);

            }
        });
        btn_color10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color10);

            }
        }); btn_color11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color11);

            }
        }); btn_color12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color12);

            }
        });
    }

    private void colorRandomForButton(Button btn_color4) {
        Random random = new Random();
    //int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(257));
      //  btn_color4.setBackgroundColor(color);

        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
        mp = MediaPlayer.create(this,R.raw.tryagain);
        if (mp.isPlaying()){
            mp.pause();
        }else{
            mp.start();
        }
    }


    private void playMusic(){
        mp2 = MediaPlayer.create(this,R.raw.in_the_hall_of_the_mountain_king);
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
}
