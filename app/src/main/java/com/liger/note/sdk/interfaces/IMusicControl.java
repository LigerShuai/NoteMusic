package com.liger.note.sdk.interfaces;

import com.liger.note.model.Music;
import com.liger.note.sdk.callback.MusicChangedListener;

/**
 * @author Liger
 * @date 2018/12/25 02:11
 */
public interface IMusicControl {

    void init();

    void play(Music music);

    void continuePlay();

    void pause();

    void switchPlay();

    void stop();

    void release();

    void seekTo(int position);

    boolean isPlaying();

    Music getCurMusic();

    void playPre();

    void playNext();

    /**
     * 注册播放状态回调
     */
    void addMusicChangedListener(MusicChangedListener listener);

    void removeMusicChangedListener(MusicChangedListener listener);

}
