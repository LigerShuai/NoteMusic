package com.liger.note.media.callback;

import com.liger.note.model.Music;

/**
 * @author Liger
 * @date 2018/12/26 11:57 PM
 */
public interface MusicChangedListener {

    void onPlay(Music music);

    void onPause();

    void onStop();

    void onComplete();

    void onRelease();

    void onError();

    void onSeekTo(int position);

    void onPlaying(boolean isPlaying);
}
