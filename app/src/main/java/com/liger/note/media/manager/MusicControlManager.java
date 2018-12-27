package com.liger.note.media.manager;

import android.util.Log;

import com.liger.note.model.Music;
import com.liger.note.media.callback.MusicChangedListener;
import com.liger.note.media.interfaces.IMusicControl;

/**
 * @author Liger
 * @date 2018/12/26 00:45
 */
public class MusicControlManager implements IMusicControl {

    private static final String TAG = "MusicControlManager";
    private IMusicControl mControl;

    private MusicControlManager() {
    }

    private static final class MusicControlManagerHolder {
        static final MusicControlManager instance = new MusicControlManager();
    }

    public static MusicControlManager getInstance() {
        return MusicControlManagerHolder.instance;
    }

    @Override
    public void init() {
        if (mControl == null) {
            mControl = new MusicControlImpl();
        }
        mControl.init();
    }

    @Override
    public void play(Music music) {
        if (isNull()) {
            return;
        }
        mControl.play(music);
    }

    @Override
    public void continuePlay() {
        if (isNull()) {
            return;
        }
        mControl.continuePlay();
    }

    @Override
    public void pause() {
        if (isNull()) {
            return;
        }
        mControl.pause();
    }

    @Override
    public void switchPlay() {
        if (isNull()) {
            return;
        }
        mControl.switchPlay();
    }

    @Override
    public void stop() {
        if (isNull()) {
            return;
        }
        mControl.stop();
    }

    @Override
    public void release() {
        if (isNull()) {
            return;
        }
        mControl.release();
    }

    @Override
    public void seekTo(int position) {
        if (isNull()) {
            return;
        }
        mControl.seekTo(position);
    }

    @Override
    public boolean isPlaying() {
        if (isNull()) {
            return false;
        }
        return mControl.isPlaying();
    }

    @Override
    public Music getCurMusic() {
        if (isNull()) {
            return null;
        }
        return mControl.getCurMusic();
    }

    @Override
    public void playPre() {
        if (isNull()) {
            return;
        }
        mControl.playPre();
    }

    @Override
    public void playNext() {
        if (isNull()) {
            return;
        }
        mControl.playNext();
    }

    @Override
    public void addMusicChangedListener(MusicChangedListener listener) {
        if (isNull()) {
            return;
        }
        mControl.addMusicChangedListener(listener);
    }

    @Override
    public void removeMusicChangedListener(MusicChangedListener listener) {
        if (isNull()) {
            return;
        }
        mControl.removeMusicChangedListener(listener);
    }

    private boolean isNull() {
        if (mControl == null) {
            Log.d(TAG, "mControl is null ");
            return true;
        }
        return false;
    }
}
