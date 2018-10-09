package becalm.com.becalm;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Main4Activity extends AppCompatActivity {

    private Button btn_color,btn_color1,btn_color2,btn_color3,btn_color4,btn_color5,btn_color6,btn_color7,btn_color8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btn_color = findViewById(R.id.button);
        btn_color1 = findViewById(R.id.btncolor);
        btn_color2 = findViewById(R.id.btncolor1);
        btn_color3 = findViewById(R.id.btncolor2);
        btn_color4 = findViewById(R.id.btncolor3);
        btn_color5 = findViewById(R.id.btncolor4);
        btn_color6 = findViewById(R.id.btncolor5);
        btn_color7 = findViewById(R.id.btncolor6);
        btn_color8 = findViewById(R.id.btncolor7);


        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color.setBackgroundColor(color);

            }
        });

        btn_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color1.setBackgroundColor(color);

            }
        });
        btn_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color2.setBackgroundColor(color);

            }
        });
        btn_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color3.setBackgroundColor(color);

            }
        });
        btn_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color4.setBackgroundColor(color);

            }
        });
        btn_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color5.setBackgroundColor(color);

            }
        });

        btn_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color6.setBackgroundColor(color);

            }
        });
        btn_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color7.setBackgroundColor(color);

            }
        });
        btn_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

                btn_color8.setBackgroundColor(color);

            }
        });
    }
}
