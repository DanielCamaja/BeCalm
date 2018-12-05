package becalm.com.becalm.fragment;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import becalm.com.becalm.R;

public class HardStress extends Fragment {

    private Button hard;
    private MediaPlayer hardplay;


    @Override
    public void onPause() {
        super.onPause();
        hardplay.stop();
    }

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_hard_stress, container, false);

        hard = (Button) v.findViewById(R.id.hardbutton);
        hardplay = MediaPlayer.create(getActivity(),R.raw.meditation4);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hardplay.isPlaying()){
                    hardplay.pause();
                    hard.setBackground(getActivity().getDrawable(R.drawable.play));
                    Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
                }else{
                    hardplay.start();
                    hard.setBackground(getActivity().getDrawable(R.drawable.pause));
                    Toast.makeText(getActivity(), "Play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
