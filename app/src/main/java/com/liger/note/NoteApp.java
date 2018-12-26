package com.liger.note;

import android.app.Application;

import com.liger.note.sdk.MusicFactory;
import com.liger.note.ui.MainActivity;

/**
 * @author Liger
 * @date 2018/12/25 00:32
 */
public class NoteApp extends Application {

    private static NoteApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        MusicFactory.getMusicControlManager().init();
    }

    public static NoteApp getInstance() {
        return instance;
    }

    public void initLibs() {

    }

    public Class<?> firstActivity() {
        return MainActivity.class;
    }

    public String[] allNeedPermissions() {
        return new String[0];
//        return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.INTERNET};
    }
}
