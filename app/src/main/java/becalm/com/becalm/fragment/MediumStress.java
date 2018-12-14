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

public class MediumStress extends Fragment {

    private Button mediumb;
    private MediaPlayer mediumplay;

    View v;

    @Override
    public void onPause() {
        super.onPause();
        if (mediumplay != null) {
            mediumplay.stop();
            mediumplay.release();
            mediumplay = null;
        }
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
