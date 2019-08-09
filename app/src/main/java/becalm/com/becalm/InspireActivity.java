package becalm.com.becalm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.ContentApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.android.appremote.api.error.AuthenticationFailedException;
import com.spotify.android.appremote.api.error.CouldNotFindSpotifyApp;
import com.spotify.android.appremote.api.error.LoggedOutException;
import com.spotify.android.appremote.api.error.NotLoggedInException;
import com.spotify.android.appremote.api.error.OfflineModeException;
import com.spotify.android.appremote.api.error.SpotifyConnectionTerminatedException;
import com.spotify.android.appremote.api.error.SpotifyDisconnectedException;
import com.spotify.android.appremote.api.error.SpotifyRemoteServiceException;
import com.spotify.android.appremote.api.error.UnsupportedFeatureVersionException;
import com.spotify.android.appremote.api.error.UserNotAuthorizedException;
import com.spotify.protocol.client.ErrorCallback;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.Capabilities;
import com.spotify.protocol.types.Image;
import com.spotify.protocol.types.ListItem;
import com.spotify.protocol.types.PlaybackSpeed;
import com.spotify.protocol.types.PlayerContext;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Repeat;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;

public class InspireActivity extends FragmentActivity {

    private static final String TAG = InspireActivity.class.getSimpleName();

    private static final String CLIENT_ID = "dff85b1810ea44f99c2b39f6dfd5cef9";

    private static final int REQUEST_CODE = 1997;
    private static final String REDIRECT_URI = "BeCalmfirst://callback";

    private static final String TRACK_URI = "spotify:playlist:1HDaj5Do65wq0RNPWFZa6k";
    private static final String ALBUM_URI = "spotify:album:4nZ5wPL5XxSY2OuDgbnYdc";
    private static final String ARTIST_URI = "spotify:artist:3WrFJ7ztbogyGnTHbHJFl2";
    private static final String PLAYLIST_URI = "spotify:playlist:37i9dQZEVXbMDoHDwVN2tF";
    private static final String PODCAST_URI = "spotify:playlist:3rDRbzQ9h5uWjjIZ7YqSln";




    private static SpotifyAppRemote mSpotifyAppRemote;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    Button mConnectAuthorizeButton;
    AppCompatImageButton mPlayPauseButton;
    AppCompatSeekBar mSeekBar;
    AppCompatTextView mImageScaleTypeLabel;
    ImageView mCoverArtImageView;

    List<View> mViews;

    TrackProgressBar mTrackProgressBar;

    Subscription<PlayerState> mPlayerStateSubscription;
    Subscription<Capabilities> mCapabilitiesSubscription;

    private final ErrorCallback mErrorCallback = throwable -> logError(throwable, "Boom!");

    @SuppressLint("SetTextI18n")
    private final Subscription.EventCallback<PlayerState> mPlayerStateEventCallback = new Subscription.EventCallback<PlayerState>() {
        @Override
        public void onEvent(PlayerState playerState) {

            AuthenticationRequest.Builder builder =
                    new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

            builder.setScopes(new String[]{"streaming"});
            AuthenticationRequest request = builder.build();

            AuthenticationClient.openLoginActivity(InspireActivity.this, REQUEST_CODE, request);

            // Update progressbar
            if (playerState.playbackSpeed > 0) {
                mTrackProgressBar.unpause();
            } else {
                mTrackProgressBar.pause();
            }
            // Invalidate play / pause
            if (playerState.isPaused) {
                mPlayPauseButton.setImageResource(R.drawable.btn_play);
            } else {
                mPlayPauseButton.setImageResource(R.drawable.btn_pause);
            }


            // Get image from track
            mSpotifyAppRemote.getImagesApi()
                    .getImage(playerState.track.imageUri, Image.Dimension.LARGE)
                    .setResultCallback(bitmap -> {
                        mCoverArtImageView.setImageBitmap(bitmap);
                    });

            // Invalidate seekbar length and position
            mSeekBar.setMax((int) playerState.track.duration);
            mTrackProgressBar.setDuration(playerState.track.duration);
            mTrackProgressBar.update(playerState.playbackPosition);

            mSeekBar.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspire);

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(InspireActivity.this, REQUEST_CODE, request);


        mConnectAuthorizeButton = findViewById(R.id.connect_authorize_button);
        mPlayPauseButton = findViewById(R.id.play_pause_button);
        mCoverArtImageView = findViewById(R.id.image);


        mSeekBar = findViewById(R.id.seek_to);
        mSeekBar.setEnabled(false);
        mSeekBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        mSeekBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        mTrackProgressBar = new TrackProgressBar(mSeekBar);

        mViews = Arrays.asList(
                mPlayPauseButton,
                findViewById(R.id.play_podcast_button),
                findViewById(R.id.play_track_button),
                mSeekBar
                //findViewById(R.id.skip_prev_button),
                //findViewById(R.id.skip_next_button),
        );

        SpotifyAppRemote.setDebugMode(true);

        onDisconnected();
        onConnectAndAuthorizedClicked(null);
    }


    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
        onDisconnected();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }

    private void onConnected() {
        for (View input : mViews) {
            input.setEnabled(true);
        }
        mConnectAuthorizeButton.setEnabled(false);
        mConnectAuthorizeButton.setText(R.string.connected);

        //onSubscribedToPlayerStateButtonClicked(null);
        onSubscribedToPlayerContextButtonClicked(null);
    }

    private void onConnecting() {
        mConnectAuthorizeButton.setEnabled(false);
        mConnectAuthorizeButton.setText(R.string.connecting);
    }


    private void onDisconnected() {
        for (View view : mViews) {
            view.setEnabled(false);
        }
        mConnectAuthorizeButton.setEnabled(true);
        mCoverArtImageView.setImageResource(R.drawable.widget_placeholder);
        mConnectAuthorizeButton.setText(R.string.authorize);
    }


    public void onConnectAndAuthorizedClicked(View view) {
        onConnecting();
        connect(true);
    }

    private void connect(boolean showAuthView) {

        SpotifyAppRemote.disconnect(mSpotifyAppRemote);

        SpotifyAppRemote.connect(
                getApplication(),
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(showAuthView)
                        .build(),
                new Connector.ConnectionListener() {
                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        InspireActivity.this.onConnected();
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        if (error instanceof SpotifyRemoteServiceException) {
                            if (error.getCause() instanceof SecurityException) {
                                logError(error, "SecurityException");
                            } else if (error.getCause() instanceof IllegalStateException) {
                                logError(error, "IllegalStateException");
                            }
                        } else if (error instanceof NotLoggedInException) {
                            logError(error, "NotLoggedInException");
                        } else if (error instanceof AuthenticationFailedException) {
                            logError(error, "AuthenticationFailedException");
                        } else if (error instanceof CouldNotFindSpotifyApp) {
                            logError(error, "CouldNotFindSpotifyApp");
                        } else if (error instanceof LoggedOutException) {
                            logError(error, "LoggedOutException");
                        } else if (error instanceof OfflineModeException) {
                            logError(error, "OfflineModeException");
                        } else if (error instanceof UserNotAuthorizedException) {
                            logError(error, "UserNotAuthorizedException");
                        } else if (error instanceof UnsupportedFeatureVersionException) {
                            logError(error, "UnsupportedFeatureVersionException");
                        } else if (error instanceof SpotifyDisconnectedException) {
                            logError(error, "SpotifyDisconnectedException");
                        } else if (error instanceof SpotifyConnectionTerminatedException) {
                            logError(error, "SpotifyConnectionTerminatedException");
                        } else {
                            logError(error, String.format("Connection failed: %s", error));
                        }
                        InspireActivity.this.onDisconnected();
                    }
                });
    }

    public void onDisconnectClicked(View v) {
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
        onDisconnected();
    }


    public void onPlayPodcastButtonClicked(View view) {
        playUri(PODCAST_URI);
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


    public void showCurrentPlayerState(View view) {
        if (view.getTag() != null) {
            showDialog("PlayerState", gson.toJson(view.getTag()));
        }
    }


    public void onSetShuffleTrueButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .setShuffle(true)
                .setResultCallback(empty -> logMessage("Set shuffle true successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSetRepeatAllButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .setRepeat(Repeat.ALL)
                .setResultCallback(empty -> logMessage("Set repeat ALL successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSkipPreviousButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .skipPrevious()
                .setResultCallback(empty -> logMessage("Skip previous successful"))
                .setErrorCallback(mErrorCallback);
    }

    public void onPlayPauseButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(playerState -> {
            if (playerState.isPaused) {
                mSpotifyAppRemote.getPlayerApi()
                        .resume()
                        .setResultCallback(empty -> logMessage("Play current track successful"))
                        .setErrorCallback(mErrorCallback);
            } else {
                mSpotifyAppRemote.getPlayerApi()
                        .pause()
                        .setResultCallback(empty -> logMessage("Pause successful"))
                        .setErrorCallback(mErrorCallback);
            }
        });


    }

    public void onSkipNextButtonClicked(View view) {
        mSpotifyAppRemote.getPlayerApi()
                .skipNext()
                .setResultCallback(data -> {
                    logMessage("Skip next successful");
                })
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
                .setEventCallback(capabilities -> logMessage(String.format("Can play on demand: %s", capabilities.canPlayOnDemand)))
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

    public void onGetFitnessRecommendedContentItems(View view) {
        mSpotifyAppRemote.getContentApi()
                .getRecommendedContentItems(ContentApi.ContentType.FITNESS)
                .setResultCallback(listItems -> mSpotifyAppRemote.getContentApi()
                        .getChildrenOfItem(listItems.items[0], 3, 0)
                        .setResultCallback(childListItems -> {
                            showDialog("RecommendedContentItems", gson.toJson(childListItems));
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
                        })
                        .setErrorCallback(mErrorCallback)).setErrorCallback(mErrorCallback);
    }

    public void onConnectSwitchToLocal(View view) {
        mSpotifyAppRemote.getConnectApi()
                .connectSwitchToLocalDevice()
                .setResultCallback(empty -> logMessage("Success!"))
                .setErrorCallback(mErrorCallback);
    }

    public void onSubscribedToPlayerContextButtonClicked(View view) {

    }

    public void onSubscribedToPlayerStateButtonClicked(View view) {

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
                .setErrorCallback(throwable -> {
                    logError(throwable, "Subscribed to PlayerContext failed!");
                });
    }

    public void onEcho(View view) {
        mSpotifyAppRemote
                .call("com.spotify.echo", new Echo.Request("Hodor!"), Echo.Response.class)
                .setResultCallback(data -> logMessage(String.format("Echo to 'Hodor!' is '%s'", data.response)))
                .setErrorCallback(mErrorCallback);
    }

    private void logError(Throwable throwable, String msg) {
        Toast.makeText(this, "Error: " + msg, Toast.LENGTH_SHORT).show();
        Log.e(TAG, msg, throwable);
    }

    private void logMessage(String msg) {
        logMessage(msg, Toast.LENGTH_SHORT);
    }

    private void logMessage(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
        Log.d(TAG, msg);
    }

    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

    public void onImageClicked(View view) {
        if (mSpotifyAppRemote != null) {
            mSpotifyAppRemote.getPlayerApi()
                    .getPlayerState()
                    .setResultCallback(playerState -> {
                        PopupMenu menu = new PopupMenu(this, view);

                        menu.getMenu().add(720, 720, 0, "Large (720px)");
                        menu.getMenu().add(480, 480, 1, "Medium (480px)");
                        menu.getMenu().add(360, 360, 2, "Small (360px)");
                        menu.getMenu().add(240, 240, 3, "X Small (240px)");
                        menu.getMenu().add(144, 144, 4, "Thumbnail (144px)");

                        menu.show();

                        menu.setOnMenuItemClickListener(item -> {
                            mSpotifyAppRemote.getImagesApi()
                                    .getImage(playerState.track.imageUri, Image.Dimension.values()[item.getOrder()])
                                    .setResultCallback(bitmap -> {
                                        mCoverArtImageView.setImageBitmap(bitmap);
                                    });
                            return false;
                        });
                    })
                    .setErrorCallback(mErrorCallback);
        }
    }

    public void onImageScaleTypeClicked(View view) {
        if (mSpotifyAppRemote != null) {
            mSpotifyAppRemote.getPlayerApi()
                    .getPlayerState()
                    .setResultCallback(playerState -> {
                        PopupMenu menu = new PopupMenu(this, view);

                        menu.getMenu().add(0, ImageView.ScaleType.CENTER.ordinal(), 0, "CENTER");
                        menu.getMenu().add(1, ImageView.ScaleType.CENTER_CROP.ordinal(), 1, "CENTER_CROP");
                        menu.getMenu().add(2, ImageView.ScaleType.CENTER_INSIDE.ordinal(), 2, "CENTER_INSIDE");
                        menu.getMenu().add(3, ImageView.ScaleType.MATRIX.ordinal(), 3, "MATRIX");
                        menu.getMenu().add(4, ImageView.ScaleType.FIT_CENTER.ordinal(), 4, "FIT_CENTER");
                        menu.getMenu().add(4, ImageView.ScaleType.FIT_XY.ordinal(), 5, "FIT_XY");

                        menu.show();

                        menu.setOnMenuItemClickListener(item -> {
                            mCoverArtImageView.setScaleType(ImageView.ScaleType.values()[item.getItemId()]);
                            mImageScaleTypeLabel.setText(ImageView.ScaleType.values()[item.getItemId()].toString());
                            return false;
                        });
                    })
                    .setErrorCallback(mErrorCallback);
        }
    }


    private class TrackProgressBar {

        private static final int LOOP_DURATION = 500;
        private final SeekBar mSeekBar;
        private final Handler mHandler;


        private final SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mSpotifyAppRemote.getPlayerApi().seekTo(seekBar.getProgress())
                        .setErrorCallback(mErrorCallback);
            }
        };

        private final Runnable mSeekRunnable = new Runnable() {
            @Override
            public void run() {
                int progress = mSeekBar.getProgress();
                mSeekBar.setProgress(progress + LOOP_DURATION);
                mHandler.postDelayed(mSeekRunnable, LOOP_DURATION);
            }
        };

        private TrackProgressBar(SeekBar seekBar) {
            mSeekBar = seekBar;
            mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
            mHandler = new Handler();
        }

        private void setDuration(long duration) {
            mSeekBar.setMax((int) duration);
        }

        private void update(long progress) {
            mSeekBar.setProgress((int) progress);
        }

        private void pause() {
            mHandler.removeCallbacks(mSeekRunnable);
        }

        private void unpause() {
            mHandler.removeCallbacks(mSeekRunnable);
            mHandler.postDelayed(mSeekRunnable, LOOP_DURATION);
        }
    }

}