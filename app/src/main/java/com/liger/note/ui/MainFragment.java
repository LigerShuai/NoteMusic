package com.liger.note.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liger.note.R;
import com.liger.note.ui.adapter.MainAdapter;
import com.liger.note.ui.adapter.MainAdapter2;
import com.liger.note.base.BaseFragment;
import com.liger.note.model.Music;
import com.liger.note.media.MusicFactory;
import com.liger.note.media.manager.MusicDataManager;
import com.liger.note.ui.view.MiniPlayerView;
import com.liger.note.utils.ListUtils;
import com.liger.note.utils.ScanUtil;
import com.liger.note.ui.vm.MainVM;

import java.util.List;

/**
 * @author Liger
 * @date 2018/12/24 23:31
 */
public class MainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private MainVM mainVM;
    private MiniPlayerView mMiniPlayerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.fragment_main_rv);
        mMiniPlayerView = view.findViewById(R.id.view_mini_player);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        int offset = mContext.getResources().getDimensionPixelOffset(R.dimen.size_item_margin);
//        RecyclerDividerItem dividerItem = new RecyclerDividerItem(mContext, DividerItemDecoration.VERTICAL);
//        dividerItem.setRect(0, 0, 0, offset);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getContext().getDrawable(R.drawable.rv_divider));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                playItem(position);
            }
        });
    }

    private void playItem(int position) {
        if (mAdapter == null) {
            return;
        }
        Music music = mAdapter.getItem(position);
        Music curMusic = MusicFactory.getMusicControlManager().getCurMusic();
        if (music != null && music != curMusic) {
            MusicFactory.getMusicControlManager().play(music);
        }
    }

    private void initData() {
        mainVM = ViewModelProviders.of(this).get(MainVM.class);
        mainVM.fetchData();
        mainVM.getMainLiveData().observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(@Nullable List<Music> music) {
                if (!ListUtils.isEmpty(music)) {
                    mAdapter.setNewData(music);
                }
            }
        });
        scanData();
    }

    private void scanData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Music> musicList = ScanUtil.scanFiles(mContext);
                mainVM.getMainLiveData().postValue(musicList);
                MusicDataManager.getInstance().addMusicList(musicList);
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMiniPlayerView.onDestroy();
    }

    private void itemClick() {
        MainAdapter2 adapter = new MainAdapter2(getContext());
        adapter.setOnItemClickListener(new MainAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Music music = mAdapter.getItem(position);
                if (music != null) {
                    MusicFactory.getMusicControlManager().play(music);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Music music = mAdapter.getItem(position);
                Toast.makeText(getContext(), music.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
