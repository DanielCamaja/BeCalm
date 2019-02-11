package becalm.com.becalm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Main4Activity extends AppCompatActivity {

    private Button btn_color,btn_color1,btn_color2,btn_color3,btn_color4,btn_color5,btn_color6,btn_color7,btn_color8,btn_color10,btn_color11,btn_color12,btn_color9;
    MediaPlayer mp,mp2;
    TextView tv_score;
    TextView resultado;
    int lastScore;
    int best1, best2, best3;

    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        time time = new time();
        time.execute();


        resultado  = (TextView)findViewById(R.id.resultado);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

        if (lastScore > best3){
            best3 = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }
        if (lastScore > best2){
            int temp = best2;
            best2 = lastScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }

        if (lastScore > best1){
            int temp = best1;
            best1 = lastScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }






        tv_score = (TextView)findViewById(R.id.textscore);
        tv_score.setText("SCORE: " + score);

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
                score++;
                tv_score.setText("SCORE: " + score);
                movimiento();


            }

        });
        btn_color10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color10);

            }
        });
        btn_color11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color11);

            }
        });
        btn_color12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colorRandomForButton(btn_color12);

            }
        });


    }

    private void colorRandomForButton(Button btn_color4) {

        Random random = new Random();
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lastScore", score);
        editor.apply();

        Intent intent = new Intent(Main4Activity.this, Main7Activity.class);
        startActivity(intent);

        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
        mp = MediaPlayer.create(this,R.raw.tryagain);
        if (mp.isPlaying()){
            mp.pause();
        }else{
            mp.start();
        }
    }

    private void movimiento(){

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

        int randX = (int)(Math.random()*3);
        int randY = (int)(Math.random()*4);
        btn_color9.setX(randX*cellSizeX);
        btn_color9.setY(randY*cellSizeY);




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
    public void hilo(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void ejecutar(){
        time time = new time();
        time.execute();
    }

    public class time extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i=1; i<3;i++)
            {
                hilo();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            ejecutar();
            movimiento();
        }
    }







}

