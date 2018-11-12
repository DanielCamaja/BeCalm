package becalm.com.becalm;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import becalm.com.becalm.fragment.BlankFragment;
import becalm.com.becalm.fragment.FragmentPrueba;

public class Main6Activity extends AppCompatActivity {


    private TabLayout tabb1;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TextView textView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        tabb1 = (TabLayout) findViewById(R.id.tab1id);
        viewPager = (ViewPager) findViewById(R.id.viewpaperid);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        textView = (TextView) findViewById(R.id.carga);
        seekBar = (SeekBar) findViewById(R.id.seek);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView.setText(""+ progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        adapter.AddFragment(new FragmentPrueba(),"");
        adapter.AddFragment(new BlankFragment(),"");
        viewPager.setAdapter(adapter);
        tabb1.setupWithViewPager(viewPager);

        //tabb1.getTabAt(0).setIcon(R.drawable.borrar);


        tabb1.getTabAt(0).setIcon(R.drawable.becalm);
        tabb1.getTabAt(1).setIcon(R.drawable.borrar);



    }
}
