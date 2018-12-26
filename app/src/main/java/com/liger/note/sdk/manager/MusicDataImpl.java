package com.liger.note.sdk.manager;

import com.liger.note.model.Music;
import com.liger.note.sdk.interfaces.IMusicData;
import com.liger.note.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liger
 * @date 2018/12/26 00:43
 */
class MusicDataImpl implements IMusicData {

    private List<Music> musicList = new ArrayList<>();

    private MusicDataImpl() {
    }

    private static final class MusicDataImplHolder {
        static final MusicDataImpl instance = new MusicDataImpl();
    }

    static MusicDataImpl getInstance() {
        return MusicDataImpl.MusicDataImplHolder.instance;
    }

    @Override
    public void addMusicList(List<Music> list) {
        if (!ListUtils.isEmpty(list)) {
            musicList.clear();
            musicList.addAll(list);
        }
    }

    @Override
    public List<Music> getMusicList() {
        return musicList;
    }

//    @Override
//    public void deleteMusicList(List<Music> list) {
//        if (!ListUtils.isEmpty(musicList)) {
//            musicList.removeAll(list);
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        if (!ListUtils.isEmpty(musicList)) {
//            musicList.clear();
//        }
//    }
}
