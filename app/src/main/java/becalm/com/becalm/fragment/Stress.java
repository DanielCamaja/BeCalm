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
import android.widget.Toast;

import becalm.com.becalm.R;

public class Stress extends Fragment {

    private Button stressboton;
    private MediaPlayer stresssong;

    View v;

    @Override
    public void onPause() {
        super.onPause();
        stresssong.stop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.stress, container, false);

        stressboton = (Button) v.findViewById(R.id.stressbutton);
        stresssong = MediaPlayer.create(getActivity(),R.raw.meditation2);
        stressboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stresssong.isPlaying()){
                    stresssong.pause();
                    stressboton.setBackground(getActivity().getDrawable(R.drawable.play));
                    Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
                }else{
                    stresssong.start();
                    stressboton.setBackground(getActivity().getDrawable(R.drawable.pause));
                    Toast.makeText(getActivity(), "Play", Toast.LENGTH_SHORT).show();
                }

            }

        });


        return v;
    }

}
