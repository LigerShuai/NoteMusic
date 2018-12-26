package com.liger.note.sdk;

import com.liger.note.sdk.manager.MusicControlManager;
import com.liger.note.sdk.manager.MusicDataManager;

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
