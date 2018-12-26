package com.liger.note.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liger.note.R;
import com.liger.note.model.Music;
import com.liger.note.sdk.MusicFactory;
import com.liger.note.sdk.callback.MusicChangedListener;
import com.liger.note.ui.PlayerActivity;

/**
 * @author Liger
 * @date 2018/12/25 00:09
 */
public class MiniPlayerView extends RelativeLayout implements View.OnClickListener {

    private static final String TAG = "MiniPlayerView";
    private Context mContext;
    private PlayCallback mPlayCallback;
    private TextView mNameTv;

    public MiniPlayerView(Context context) {
        this(context, null);
    }

    public MiniPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MiniPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        inflate(context, R.layout.view_mini_player, this);
        findViewById(R.id.view_mini_rl).setOnClickListener(this);
        findViewById(R.id.iv_control).setOnClickListener(this);

        mNameTv = findViewById(R.id.view_mini_song_name_tv);
        mPlayCallback = new PlayCallback();
        MusicFactory.getMusicControlManager().addMusicChangedListener(mPlayCallback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_mini_rl:
                skipToPlayer();
                break;
            case R.id.iv_control:
                MusicFactory.getMusicControlManager().switchPlay();
            default:
                break;
        }
    }

    private void skipToPlayer() {
        PlayerActivity.launch(mContext);
    }

    public void onDestroy() {
        MusicFactory.getMusicControlManager().removeMusicChangedListener(mPlayCallback);
    }

    private class PlayCallback implements MusicChangedListener {

        @Override
        public void onPlay(Music music) {
            mNameTv.setText(music.getName());
        }

        @Override
        public void onPause() {
        }

        @Override
        public void onStop() {
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onRelease() {
        }

        @Override
        public void onError() {
        }

        @Override
        public void onSeekTo(int position) {

        }

        @Override
        public void onPlaying(boolean isPlaying) {

        }
    }
}
