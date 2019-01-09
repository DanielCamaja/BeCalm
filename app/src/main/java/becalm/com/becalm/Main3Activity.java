package becalm.com.becalm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.ContentApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.ErrorCallback;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.Capabilities;
import com.spotify.protocol.types.ListItem;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Repeat;
import com.spotify.protocol.types.Shuffle;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.fragment.app.FragmentActivity;

public class Main3Activity extends FragmentActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

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

    private TrackProgressBar mTrackProgressBar;

    private Subscription<PlayerState> mPlayerStateSubscription;
    private Subscription<PlayerContext> mPlayerContextSubscription;
    private Subscription<Capabilities> mCapabilitiesSubscription;

    private class TrackProgressBar {

        private static final int LOOP_DURATION = 500;
        private final Handler mHandler;

        private TrackProgressBar(SeekBar seekBar) {
            mHandler = new Handler();
        }

    }

    @SuppressLint("SetTextI18n")
    private final Subscription.EventCallback<PlayerState> mPlayerStateEventCallback = new Subscription.EventCallback<PlayerState>() {
        @Override
        public void onEvent(PlayerState data) {
            mToggleRepeatButton.setText(getString(R.string.toggle_repeat_button) + " " + data.playbackOptions.repeatMode);
            mToggleShuffleButton.setText(getString(R.string.toggle_shuffle_button) + " " + data.playbackOptions.isShuffling);


            if (data.track != null) {

                mSpotifyAppRemote.getImagesApi()
                        .getImage(data.track.imageUri)
                        .setResultCallback(bitmap -> mImageView.setImageBitmap(bitmap));


            }
        }
    };


    private List<View> mViews;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mConnect = findViewById(R.id.connect);
        mToggleShuffleButton = findViewById(R.id.toggle_shuffle_button);
        mToggleRepeatButton = findViewById(R.id.toggle_repeat_button);


        mViews = Arrays.asList(
                findViewById(R.id.disconnect),
                findViewById(R.id.show_player_state_button),
                findViewById(R.id.play_track_button),
                findViewById(R.id.play_album_button),
                findViewById(R.id.play_artist_button),
                findViewById(R.id.play_playlist_button),
                findViewById(R.id.pause_button),
                findViewById(R.id.resume_button),
                findViewById(R.id.skip_prev_button),
                findViewById(R.id.skip_next_button),
                mToggleShuffleButton,
                mToggleRepeatButton,
                findViewById(R.id.set_shuffle_button),
                findViewById(R.id.set_repeat_button),
                findViewById(R.id.subscribe_to_capabilities),
                findViewById(R.id.get_collection_state),
                findViewById(R.id.remove_uri),
                findViewById(R.id.save_uri),
                findViewById(R.id.get_fitness_child),
                findViewById(R.id.connect_switch_to_local),
                findViewById(R.id.subscribe_to_player_state),
                findViewById(R.id.subscribe_to_player_context),
                findViewById(R.id.echo));

        SpotifyAppRemote.setDebugMode(true);

        onDisconnected();
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

    private void onDisconnected() {
        for (View view : mViews) {
            view.setEnabled(false);
        }
        mConnect.setEnabled(true);
    }

    public void onConnectClicked(View v) {
        connect(false);
    }


    public void onConnectAndAuthorizedClicked(View view) {
        connect(true);
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
                        Main3Activity.this.onConnected();
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        logMessage(String.format("Connection failed: %s", error));
                        Main3Activity.this.onDisconnected();
                    }
                });
    }

    public void onDisconnectClicked(View v) {
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
        onDisconnected();
    }

    public void onResumeButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .resume()
                .setResultCallback(empty -> logMessage("Resume successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onPauseButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .pause()
                .setResultCallback(empty -> logMessage("Pause successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onPlayTrackButtonClicked(View view) {
        playUri(TRACK_URI);
    }

    public void onPlayAlbumButtonClicked(View view) {
        playUri(ALBUM_URI);
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

    public void onShowPlayerStateButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .getPlayerState()
                .setResultCallback(playerState -> logMessage("Got current track" + playerState))
                .setErrorCallback(mErrorCallback);
    }

    public void onToggleShuffleButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .toggleShuffle()
                .setResultCallback(empty -> logMessage("Toggle shuffle successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onToggleRepeatButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .toggleRepeat()
                .setResultCallback(empty -> logMessage("Toggle repeat successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSetShuffleTrueButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .setShuffle(true)
                .setResultCallback(empty -> logMessage("Toggle shuffle successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSetRepeatAllButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .setRepeat(Repeat.ALL)
                .setResultCallback(empty -> logMessage("Toggle repeat successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSkipPreviousButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .skipPrevious()
                .setResultCallback(empty -> logMessage("Skip previous successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSkipNextButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .skipNext()
                .setResultCallback(empty -> logMessage("Skip next successful"))
                .setErrorCallback(mErrorCallback);
    }

    @SuppressLint("SetTextI18n")
    public void onSubscribeToCapabilities(View view) {

        if (mCapabilitiesSubscription != null && !mCapabilitiesSubscription.isCanceled()) {
            mCapabilitiesSubscription.cancel();
            mCapabilitiesSubscription = null;
        }

        mCapabilitiesSubscription = (Subscription<Capabilities>) mSpotifyAppRemote.getUserApi()
                .subscribeToCapabilities()
                .setErrorCallback(mErrorCallback);

        mSpotifyAppRemote.getUserApi()
                .getCapabilities()
                .setResultCallback(capabilities -> logMessage(String.format("Can play on demand: %s", capabilities.canPlayOnDemand)))
                .setErrorCallback(mErrorCallback);
    }

    public void onGetCollectionState(View view) {
        mSpotifyAppRemote.getUserApi()
                .getLibraryState(TRACK_URI)
                .setResultCallback(libraryState -> logMessage(String.format(
                        "Item is in collection: %s\nCan be added to collection: %s",
                        libraryState.isAdded,
                        libraryState.canAdd
                )))
                .setErrorCallback(t -> logError(t, "Error:" + t.getMessage()));
    }

    public void onRemoveUri(View view) {
        mSpotifyAppRemote.getUserApi()
                .removeFromLibrary(TRACK_URI)
                .setResultCallback(empty -> logMessage("Remove from collection successful"))
                .setErrorCallback(throwable -> logError(throwable, "Error:" + throwable.getMessage()));
    }

    public void onSaveUri(View view) {
        mSpotifyAppRemote.getUserApi()
                .addToLibrary(TRACK_URI)
                .setResultCallback(empty -> logMessage("Add to collection successful"))
                .setErrorCallback(throwable -> logError(throwable, "Error:" + throwable.getMessage()));
    }

    public void onGetFitnessChild(View view) {
        mSpotifyAppRemote.getContentApi()
                .getRecommendedContentItems(ContentApi.ContentType.FITNESS)
                .setResultCallback(listItems -> mSpotifyAppRemote.getContentApi()
                        .getChildrenOfItem(listItems.items[0], 3, 0)
                        .setResultCallback(childListItems -> {
                            logMessage("Got Items: " + childListItems);
                            ListItem item = null;
                            for (int i = 0; i < childListItems.items.length; ++i) {
                                item = childListItems.items[i];
                                if (item.playable) {
                                    logMessage(String.format("Trying to play %s", item.title));
                                    break;
                                } else {
                                    item = null;
                                }
                            }
                            mSpotifyAppRemote.getContentApi()
                                    .playContentItem(item)
                                    .setResultCallback(empty -> logMessage("Content item played!"))
                                    .setErrorCallback(mErrorCallback);
                        })
                        .setErrorCallback(mErrorCallback)).setErrorCallback(mErrorCallback);
    }

    public void onConnectSwitchToLocal(View view){
        mSpotifyAppRemote.getConnectApi()
                .connectSwitchToLocalDevice()
                .setResultCallback(empty -> logMessage("Success!"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSubscribedToCurrentTrackButtonClicked(View view) {

        if (mPlayerStateSubscription != null && !mPlayerStateSubscription.isCanceled()) {
            mPlayerStateSubscription.cancel();
            mPlayerStateSubscription = null;
        }

        mPlayerStateSubscription = (Subscription<PlayerState>) mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(mPlayerStateEventCallback)
                .setLifecycleCallback(new Subscription.LifecycleCallback() {
                    @Override
                    public void onStart() {
                        logMessage("Event: start");
                    }

                    @Override
                    public void onStop() {
                        logMessage("Event: end");
                    }
                })
                .setErrorCallback(mErrorCallback);
    }

    public void onSubscribedToPlayerContextButtonClicked(View view) {
        if (mPlayerContextSubscription != null && !mPlayerContextSubscription.isCanceled()) {
            mPlayerContextSubscription.cancel();
            mPlayerContextSubscription = null;
        }

        mPlayerContextSubscription = (Subscription<PlayerContext>) mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerContext()
                .setErrorCallback(mErrorCallback);
    }

    public void onEcho(View view) {
        mSpotifyAppRemote
                .call("com.spotify.echo", new Echo.Request("Hodor!"), Echo.Response.class)
                .setResultCallback(data -> logMessage(String.format("Echo to 'Hodor!' is '%s'", data.response)))
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
