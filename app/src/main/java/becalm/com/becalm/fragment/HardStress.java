package becalm.com.becalm.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import becalm.com.becalm.R;

public class HardStress extends Fragment {

    private Button hard;
    private MediaPlayer hardplay;


    @Override
    public void onPause() {
        super.onPause();
        if (hardplay != null) {
            hardplay.stop();
            hardplay.release();
            hardplay = null;
        }
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
