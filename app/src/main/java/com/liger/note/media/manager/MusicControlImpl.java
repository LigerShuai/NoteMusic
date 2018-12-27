package com.liger.note.media.manager;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;

import com.liger.note.model.Music;
import com.liger.note.media.callback.MusicChangedListener;
import com.liger.note.media.interfaces.IMusicControl;
import com.liger.note.utils.ListUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liger
 * @date 2018/12/26 00:44
 */
class MusicControlImpl implements IMusicControl {

    private static final String TAG = "MusicControlImpl";
    private MediaPlayer mediaPlayer;
    private List<MusicChangedListener> mChangedListeners = new ArrayList<>();
    private Music mCurMusic;

    @Override
    public void init() {
        if (mediaPlayer != null) {
            return;
        }
        mediaPlayer = new MediaPlayer();
        initListener(mediaPlayer);
    }

    private void initListener(MediaPlayer mediaPlayer) {
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                startPlay(mp);
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.d(TAG, "onBufferingUpdate: " + percent);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                dispatchComplete();
                playNext();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                dispatchError();
                Log.d(TAG, "onError: " + what);
                return false;
            }
        });
    }

    private void startPlay(MediaPlayer player) {
        player.start();
        if (mCurMusic != null) {
            dispatchPlay(mCurMusic);
        }
    }

    @Override
    public void play(Music music) {
        if (mediaPlayer == null || music == null) {
            return;
        }
        mCurMusic = music;
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        mediaPlayer.setAudioAttributes(attributes);
        mediaPlayer.reset();

        String path = music.getPath();
        if (TextUtils.isEmpty(path)) {
            return;
        }
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void continuePlay() {
        if (mediaPlayer != null && !isPlaying()) {
            startPlay(mediaPlayer);
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer != null && isPlaying()) {
            mediaPlayer.pause();
            dispatchPause();
        }
    }

    @Override
    public void switchPlay() {
        if (isPlaying()) {
            pause();
        } else {
            continuePlay();
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            dispatchStop();
        }
    }

    @Override
    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            dispatchRelease();
        }
    }

    @Override
    public void seekTo(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
            dispatchSeekTo(position);
        }
    }

    @Override
    public boolean isPlaying() {
        if (mediaPlayer != null) {
            boolean playing = mediaPlayer.isPlaying();
            dispatchPlaying(playing);
            return playing;

        }
        dispatchPlaying(false);
        return false;
    }

    @Override
    public Music getCurMusic() {
        return mCurMusic;
    }

    @Override
    public void playPre() {
        List<Music> musicList = MusicDataManager.getInstance().getMusicList();
        if (ListUtils.isEmpty(musicList)) {
            return;
        }
        Music curMusic = MusicControlManager.getInstance().getCurMusic();
        if (curMusic == null) {
            return;
        }
        int position = musicList.indexOf(curMusic);
        if (position == 0) {
            MusicControlManager.getInstance().play(musicList.get(musicList.size() - 1));
        } else {
            MusicControlManager.getInstance().play(musicList.get(position - 1));
        }
    }

    @Override
    public void playNext() {
        List<Music> musicList = MusicDataManager.getInstance().getMusicList();
        if (ListUtils.isEmpty(musicList)) {
            return;
        }
        Music curMusic = MusicControlManager.getInstance().getCurMusic();
        if (curMusic == null) {
            return;
        }
        int position = musicList.indexOf(curMusic);
        int size = musicList.size();
        int realPosition;
        if (position == size - 1) {
            realPosition = 0;
        } else {
            realPosition = position + 1;
        }
        MusicControlManager.getInstance().play(musicList.get(realPosition));
        Log.d(TAG, "playNext: " + musicList.get(realPosition).getName());
    }

    @Override
    public void addMusicChangedListener(MusicChangedListener listener) {
        if (listener != null && !mChangedListeners.contains(listener)) {
            mChangedListeners.add(listener);
        }
    }

    @Override
    public void removeMusicChangedListener(MusicChangedListener listener) {
        mChangedListeners.remove(listener);
    }

    private void dispatchPlay(Music music) {
        Log.d(TAG, "onPlay");
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onPlay(music);
        }
    }

    private void dispatchPause() {
        Log.d(TAG, "onPause");
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onPause();
        }
    }

    private void dispatchComplete() {
        Log.d(TAG, "onComplete");
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onComplete();
        }
    }

    private void dispatchStop() {
        Log.d(TAG, "onStop");
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onStop();
        }
    }

    private void dispatchRelease() {
        Log.d(TAG, "onRelease");
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onRelease();
        }
    }

    private void dispatchError() {
        Log.d(TAG, "onError");
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onError();
        }
    }

    private void dispatchSeekTo(int position) {
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onSeekTo(position);
        }
    }

    private void dispatchPlaying(boolean isPlaying) {
        Log.d(TAG, "onPlaying" + isPlaying);
        for (MusicChangedListener listener : mChangedListeners) {
            listener.onPlaying(isPlaying);
        }
    }
}
