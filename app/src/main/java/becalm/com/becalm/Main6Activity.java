package becalm.com.becalm;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import becalm.com.becalm.fragment.HardStress;
import becalm.com.becalm.fragment.MediumStress;
import becalm.com.becalm.fragment.UnderStress;
import becalm.com.becalm.fragment.Stress;

public class Main6Activity extends AppCompatActivity {


    private TabLayout tabb1;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        tabb1 = (TabLayout) findViewById(R.id.tab1id);
        viewPager = (ViewPager) findViewById(R.id.viewpaperid);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());



        adapter.AddFragment(new UnderStress(),"");
        adapter.AddFragment(new Stress(),"");
        adapter.AddFragment(new MediumStress(),"");
        adapter.AddFragment(new HardStress(),"");


        viewPager.setAdapter(adapter);
        tabb1.setupWithViewPager(viewPager);

        //tabb1.getTabAt(0).setIcon(R.drawable.borrar);


        tabb1.getTabAt(0).setIcon(R.drawable.under_stress);
        tabb1.getTabAt(1).setIcon(R.drawable.stress);
        tabb1.getTabAt(2).setIcon(R.drawable.medium_stress);
        tabb1.getTabAt(3).setIcon(R.drawable.hard_stress);



    }
}
