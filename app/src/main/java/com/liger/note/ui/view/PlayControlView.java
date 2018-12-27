package com.liger.note.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.liger.note.R;
import com.liger.note.media.MusicFactory;

/**
 * @author Liger
 * @date 2018/12/27 00:57
 */
public class PlayControlView extends RelativeLayout implements View.OnClickListener {

    private TextView startTv, endTv;
    private ImageView controlIv, preIv, nextIv;
    private SeekBar mSeekBar;

    public PlayControlView(Context context) {
        this(context, null);
    }

    public PlayControlView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PlayControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_play_control, this);
        startTv = findViewById(R.id.start_tv);
        endTv = findViewById(R.id.end_tv);
        controlIv = findViewById(R.id.iv_play_control);
        preIv = findViewById(R.id.iv_play_pre);
        nextIv = findViewById(R.id.iv_play_next);
        mSeekBar = findViewById(R.id.seek_bar);
        controlIv.setOnClickListener(this);
        preIv.setOnClickListener(this);
        nextIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play_control:
                MusicFactory.getMusicControlManager().switchPlay();
                break;
            case R.id.iv_play_pre:
                MusicFactory.getMusicControlManager().playPre();
                break;
            case R.id.iv_play_next:
                MusicFactory.getMusicControlManager().playNext();
                break;
            default:
                break;
        }
    }
}
