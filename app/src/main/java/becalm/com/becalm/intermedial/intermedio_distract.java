package becalm.com.becalm.intermedial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import becalm.com.becalm.Main4Activity;
import becalm.com.becalm.R;

public class intermedio_distract extends AppCompatActivity {

    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_distract);

        boton = (Button)findViewById(R.id.botondistract);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(intermedio_distract.this,Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}
