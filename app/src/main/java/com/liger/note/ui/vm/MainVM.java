package com.liger.note.ui.vm;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.liger.note.base.BaseViewModel;
import com.liger.note.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liger
 * @date 2018/12/25 01:24
 */
public class MainVM extends BaseViewModel {

    private MutableLiveData<List<Music>> mainLiveData;

    public MainVM(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Music>> getMainLiveData() {
        if (mainLiveData == null) {
            mainLiveData = new MutableLiveData<>();
        }
        return mainLiveData;
    }

    public void fetchData() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mainLiveData = null;
    }

    private int mCurPage = 1;

    public boolean loadData() {
        List<Music> list = new ArrayList<>();
        Music music;
        for (int i = 0; i < 20; i++) {
            music = new Music();
            music.setName(i + "");
            music.setSinger(i + "");
            list.add(music);
        }
        getMainLiveData().postValue(list);
        mCurPage++;
        return mCurPage == 7;
    }
}
