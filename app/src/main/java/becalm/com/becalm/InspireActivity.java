package becalm.com.becalm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.ErrorCallback;

import java.util.List;

import androidx.fragment.app.FragmentActivity;

public class InspireActivity extends FragmentActivity {

    private static final String TAG = InspireActivity.class.getSimpleName();

    private static final String CLIENT_ID = "d8ac6bd313874183a554518bd511866f";
    private static final String REDIRECT_URI = "combecalm://callback";

    private static final String TRACK_URI = "spotify:track:1UBQ5GK8JaQjm5VbkBZY66";
    private static final String ALBUM_URI = "spotify:album:1x0uzT3ETlIYjPueTyNfnQ";
    private static final String ARTIST_URI = "spotify:user:orimuraichika01:playlist:3rDRbzQ9h5uWjjIZ7YqSln";
    private static final String PLAYLIST_URI = "spotify:user:orimuraichika01:playlist:1HDaj5Do65wq0RNPWFZa6k";

    private SpotifyAppRemote mSpotifyAppRemote;

    private TextView mRecentErrorView;
    private ImageView mImageView;

    private Button mConnect;
    private Button mToggleRepeatButton;
    private Button mToggleShuffleButton;

    private final ErrorCallback mErrorCallback = throwable -> logError(throwable, "Boom!");


    private List<View> mViews;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspire);

        mConnect = findViewById(R.id.authorize);
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(true);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    private void onConnected() {
        for (View input : mViews) {
            input.setEnabled(true);
        }
        mConnect.setEnabled(false);
    }

    private void connect(boolean showAuthView) {
        final int imageSize = (int) getResources().getDimension(R.dimen.image_size);

        SpotifyAppRemote.disconnect(mSpotifyAppRemote);

        SpotifyAppRemote.connect(
                getApplication(),
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .setPreferredImageSize(imageSize)
                        .showAuthView(showAuthView)
                        .build(),
                new Connector.ConnectionListener() {
                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        logMessage(String.format("", error));
                    }
                });
    }


    public void onPlayArtistButtonClicked(View view) {
        playUri(ARTIST_URI);
    }

    public void onPlayPlaylistButtonClicked(View view) {
        playUri(PLAYLIST_URI);
    }

    private void playUri(String uri) {
        mSpotifyAppRemote.getPlayerApi()
                .play(uri)
                .setResultCallback(empty -> logMessage("Play successful"))
                .setErrorCallback(mErrorCallback);
    }

    private void logError(Throwable t, String msg) {
        Toast.makeText(this, "Error: " + msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg, t);
    }

    private void logMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, msg);
    }

}
