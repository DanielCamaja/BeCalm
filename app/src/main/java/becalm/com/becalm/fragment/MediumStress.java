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

public class MediumStress extends Fragment {

    private Button mediumb;
    private MediaPlayer mediumplay;

    View v;

    @Override
    public void onPause() {
        super.onPause();
        mediumplay.stop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_medium_stress, container, false);

        mediumb = (Button) v.findViewById(R.id.buttonmedium);
        mediumplay = MediaPlayer.create(getActivity(),R.raw.meditation3);
        mediumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediumplay.isPlaying()){
                    mediumplay.pause();
                    mediumb.setBackground(getActivity().getDrawable(R.drawable.play));
                    Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
                }else{
                    mediumplay.start();
                    mediumb.setBackground(getActivity().getDrawable(R.drawable.pause));
                    Toast.makeText(getActivity(), "Play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
