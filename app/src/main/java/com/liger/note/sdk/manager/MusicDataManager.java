package com.liger.note.sdk.manager;

import com.liger.note.model.Music;
import com.liger.note.sdk.interfaces.IMusicData;

import java.util.List;

/**
 * @author Liger
 * @date 2018/12/26 00:45
 */
public class MusicDataManager implements IMusicData {

    private MusicDataImpl iMusicData;

    private MusicDataManager() {
        if (iMusicData == null) {
            iMusicData = MusicDataImpl.getInstance();
        }
    }

    private static final class MusicDataManagerHolder {
        static final MusicDataManager instance = new MusicDataManager();
    }

    public static MusicDataManager getInstance() {
        return MusicDataManagerHolder.instance;
    }

    @Override
    public void addMusicList(List<Music> list) {
        iMusicData.addMusicList(list);
    }

    @Override
    public List<Music> getMusicList() {
        return iMusicData.getMusicList();
    }

//    @Override
//    public void deleteMusicList(List<Music> list) {
//        iMusicData.deleteMusicList(list);
//    }
//
//    @Override
//    public void deleteAll() {
//        iMusicData.deleteAll();
//    }
}
