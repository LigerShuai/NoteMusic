package com.liger.note.ui.adapter;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liger.note.R;
import com.liger.note.model.Music;
import com.liger.note.utils.ListUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static com.liger.note.ui.adapter.MainAdapter2.ViewType.FOOTER;
import static com.liger.note.ui.adapter.MainAdapter2.ViewType.ITEM;

/**
 * @author zs
 * @date 2018/12/26 0026.
 */
public class MainAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Music> mData;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM, FOOTER})
    @interface ViewType {

        int ITEM = 0;
        int FOOTER = 1;
    }
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (ViewType.FOOTER == viewType) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_footer, viewGroup, false);
            return new FooterViewHolder(view);
        }
        view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_main, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.mSongNameTv.setOnClickListener(this);
        holder.mSongNameTv.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            Music music = mData.get(position);
            holder.mSongNameTv.setText(music.getName());
            holder.mSongNameTv.setTag(position);
        } else if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder holder = (FooterViewHolder) viewHolder;
            holder.footerTip.setText("load more");
            mLoadMoreListener.onLoadMore();
            Log.d("shuai", "onBindViewHolder: onLoadMore");
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() - 1 == position) {
            Log.d("shuai", "getItemViewType: FOOTER");
            return ViewType.FOOTER;
        }
        return ViewType.ITEM;
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
    class FooterViewHolder extends RecyclerView.ViewHolder {

        TextView footerTip;

        FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            footerTip = itemView.findViewById(R.id.item_footer_tv);
        }

    }

    public void loadMore() {

    }

    private OnItemClickListener mListener;
    private OnLoadMoreListener mLoadMoreListener;

    public void setLoadMoreListener(OnLoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
