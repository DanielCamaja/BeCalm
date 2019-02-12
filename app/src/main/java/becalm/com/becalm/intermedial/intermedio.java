package becalm.com.becalm.intermedial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import becalm.com.becalm.InspireActivity;
import becalm.com.becalm.R;

public class intermedio extends AppCompatActivity {

    Button btnir;
    private VideoView video;
    MediaController mediac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_inspire);


        btnir = (Button) findViewById(R.id.intermedialboton);
        btnir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(intermedio.this, InspireActivity.class);
                startActivity(intent);
            }
        });
    }
}
