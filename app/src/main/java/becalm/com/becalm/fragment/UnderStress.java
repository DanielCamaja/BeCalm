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

public class UnderStress extends Fragment {

    private Button comenzar;
    MediaPlayer understress;
    View v;

    @Override
    public void onPause() {
        super.onPause();
        if (understress != null){
            understress.stop();
            understress.release();
            understress = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.stress_under, container, false);

        comenzar = (Button) v.findViewById(R.id.understressbutton);
        understress = MediaPlayer.create(getActivity(),R.raw.meditacion);
        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (understress.isPlaying()){
                    understress.pause();
                    comenzar.setBackground(getActivity().getDrawable(R.drawable.play));
                    Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
                }else{
                    understress.start();
                    comenzar.setBackground(getActivity().getDrawable(R.drawable.pause));
                    Toast.makeText(getActivity(), "Play", Toast.LENGTH_SHORT).show();
                }

            }

        });



        return v;
    }


}
