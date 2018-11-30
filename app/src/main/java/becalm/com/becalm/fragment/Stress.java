package becalm.com.becalm.fragment;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import becalm.com.becalm.R;

public class Stress extends Fragment {

    private Button stressboton;
    MediaPlayer stresssong;

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.stress, container, false);

        stressboton = (Button) v.findViewById(R.id.stressbutton);
        stressboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stresssong = MediaPlayer.create(getActivity(), R.raw.meditation2);
                if (stresssong.isPlaying()){
                    stresssong.pause();
                }else{
                    stresssong.start();
                }
            }

        });


        return v;
    }

}
