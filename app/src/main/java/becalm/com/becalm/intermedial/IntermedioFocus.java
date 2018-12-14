package becalm.com.becalm.intermedial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import becalm.com.becalm.Main5Activity;
import becalm.com.becalm.R;

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
