package becalm.com.becalm.intermedial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import becalm.com.becalm.Main6Activity;
import becalm.com.becalm.R;

public class IntermedioRelax extends AppCompatActivity {

    private Button btnrelax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_relax);
        btnrelax =  (Button) findViewById(R.id.intermedialboton);
        btnrelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntermedioRelax.this, Main6Activity.class);
                startActivity(intent);
            }
        });

    }
}
