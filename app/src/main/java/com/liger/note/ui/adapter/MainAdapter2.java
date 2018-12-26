package com.liger.note.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liger.note.R;
import com.liger.note.model.Music;

import java.util.List;

/**
 * @author zs
 * @date 2018/12/26 0026.
 */
public class MainAdapter2 extends RecyclerView.Adapter<MainAdapter2.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Music> mData;

    public MainAdapter2(Context context) {
        mContext = context;
    }

    public void setData(List<Music> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * adapter 内部的数据
     */
    public Music getItem(int position) {
        if (position < 0 || position > mData.size()) {
            return null;
        } else {
            return mData.get(position);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_main, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.mSongNameTv.setOnClickListener(this);
        holder.mSongNameTv.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Music music = mData.get(position);
        viewHolder.mSongNameTv.setText(music.getName());
        viewHolder.mSongNameTv.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_tv_song_name:
                if (mListener != null) {
                    mListener.onItemClick(v, (int) v.getTag());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.item_tv_song_name:
                if (mListener != null) {
                    mListener.onItemLongClick(v, (int) v.getTag());
                }
                break;
            default:
                break;
        }
        return false;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mSongNameTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSongNameTv = itemView.findViewById(R.id.item_tv_song_name);
        }
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
