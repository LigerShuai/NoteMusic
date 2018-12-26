package com.liger.note.ui.vm;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.liger.note.base.BaseViewModel;
import com.liger.note.model.Music;

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
}
