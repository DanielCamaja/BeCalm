package becalm.com.becalm.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import becalm.com.becalm.R;

public class UnderStress extends Fragment {

    private Button comenzar;
    MediaPlayer understress;
    View v;

    @Override
    public void onPause() {
        super.onPause();
        understress.stop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.stress_under, container, false);

        comenzar = (Button) v.findViewById(R.id.understressbutton);
        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                understress = MediaPlayer.create(getActivity(), R.raw.meditacion);
                if (understress.isPlaying()){
                    understress.pause();
                }else{
                    understress.start();
                }
            }

        });



        return v;
    }


}
