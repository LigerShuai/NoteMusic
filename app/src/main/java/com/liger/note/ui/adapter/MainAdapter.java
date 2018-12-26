package com.liger.note.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liger.note.R;
import com.liger.note.model.Music;

/**
 * @author Liger
 * @date 2018/12/25 00:55
 */
public class MainAdapter extends BaseQuickAdapter<Music, BaseViewHolder> {

    public MainAdapter() {
        super(R.layout.item_rv_main);
    }

    @Override
    protected void convert(BaseViewHolder helper, Music item) {
        helper.setText(R.id.item_tv_song_name, item.getName());
        helper.setText(R.id.item_tv_singer, item.getSinger());
    }
}
