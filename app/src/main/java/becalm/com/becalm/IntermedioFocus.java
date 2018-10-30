package becalm.com.becalm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class IntermedioFocus extends AppCompatActivity {

    private Button botonfocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_focus);


        botonfocus = (Button)findViewById(R.id.focusboton);
        botonfocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntermedioFocus.this,Main5Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
