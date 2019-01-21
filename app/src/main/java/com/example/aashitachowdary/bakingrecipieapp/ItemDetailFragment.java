package com.example.aashitachowdary.bakingrecipieapp;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ItemDetailFragment extends Fragment implements ExoPlayer.EventListener {
    PlayerView PlayerView;
    TextView textView;
    SimpleExoPlayer player;
    ImageView Image;
    boolean playWhenReady;
    int currentWindow;
    Uri videoUrl;
    long playbackPosition=0;
    String link;
    String data;
    String thumbnailURL;
    public static final String CURRENTPOS="currentposition";
    public static final String PLAYBACK="play_back";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            data = getArguments().getString("fullDesc");
            link = getArguments().getString("link");
            thumbnailURL=getArguments().getString("thumbnailurl");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        textView = rootView.findViewById(R.id.item_detail_text);
        PlayerView = rootView.findViewById(R.id.exoplayer_id);
        Image=rootView.findViewById(R.id.image);
        textView.setText(data);
        if (TextUtils.isEmpty(link))
        {
            PlayerView.setVisibility(View.VISIBLE);
            Image.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(thumbnailURL))
            {
                link=thumbnailURL;
                PlayerView.setVisibility(View.VISIBLE);
                Image.setVisibility(View.GONE);
            }
            else {
                PlayerView.setVisibility(View.INVISIBLE);
                Image.setVisibility(View.VISIBLE);
            }

        }


        videoUrl=Uri.parse(link);

        if (savedInstanceState != null) {
            currentWindow= (int) savedInstanceState.getLong(CURRENTPOS);
            player.seekTo(currentWindow);
            boolean playWhenReady =savedInstanceState.getBoolean(PLAYBACK);
            player.setPlayWhenReady(playWhenReady);
        }
        initializePlayer(videoUrl);

        return rootView;
    }

    private void initializePlayer(Uri mediaUri) {

        if (player == null && mediaUri!=null) {
            PlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),R.drawable.load));
            LoadControl loadControl=new DefaultLoadControl();
            TrackSelector trackSelector=new DefaultTrackSelector();
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getActivity()),trackSelector,loadControl);

            PlayerView.setPlayer(player);
            String userAgent=Util.getUserAgent(getActivity(),"BakingRecipieApp");
            MediaSource mediaSource=new ExtractorMediaSource(mediaUri,new DefaultDataSourceFactory(getActivity(),userAgent),new DefaultExtractorsFactory(),null,null);

            player.prepare(mediaSource);

            player.setPlayWhenReady(true);
            player.seekTo(playbackPosition);
        }
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            playWhenReady = player.getPlayWhenReady();
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(videoUrl);
        }
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer(videoUrl);
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        PlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }



    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }


    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }


}
