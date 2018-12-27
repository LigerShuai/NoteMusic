package com.liger.note.media;

import com.liger.note.media.manager.MusicControlManager;
import com.liger.note.media.manager.MusicDataManager;

/**
 * @author Liger
 * @date 2018/12/26 22:54
 */
public class MusicFactory {

    public static MusicControlManager getMusicControlManager() {
        return MusicControlManager.getInstance();
    }

    public static MusicDataManager getMusicDataManager() {
        return MusicDataManager.getInstance();
    }


}
