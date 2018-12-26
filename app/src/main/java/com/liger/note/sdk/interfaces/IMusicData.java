package com.liger.note.sdk.interfaces;

import com.liger.note.model.Music;

import java.util.List;

/**
 * @author Liger
 * @date 2018/12/26 00:40
 */
public interface IMusicData {

    void addMusicList(List<Music> list);

    List<Music> getMusicList();

}
