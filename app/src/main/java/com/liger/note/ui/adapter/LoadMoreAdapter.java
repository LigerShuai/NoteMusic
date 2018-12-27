//package com.liger.note.ui.adapter;
//
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.util.SparseArray;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//
///**
// * @author Liger
// * @date 2018/12/28 00:39
// */
//public class HeaderAndFooterWrapper<k> extends RecyclerView.Adapter<BaseViewHolder> {
//    private static final int BASE_ITEM_TYPE_HEADER = 100000;
//    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
//
//    private SparseArray<View> mHeaderViews = new SparseArray<>();
//    private SparseArray<View> mFootViews = new SparseArray<>();
//    private BaseAdapter<k> mInnerAdapter;
//    private static final int M_DEFAULT_NUMBER = -1;
//    private static final String TAG = "HeaderAndFooterWrapper";
//
//    //将我们自己的adapter 传进去:
//    public HeaderAndFooterWrapper(BaseAdapter<k> adapter) {
//        mInnerAdapter = adapter;
//    }
//
//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        BaseViewHolder holder;
//        if (mHeaderViews.get(viewType) != null) {
//            holder = BaseViewHolder.creatBaseViewHolder(mHeaderViews.get(viewType));
//            return holder;
//
//        } else if (mFootViews.get(viewType) != null) {
//            holder = BaseViewHolder.creatBaseViewHolder(mFootViews.get(viewType));
//            return holder;
//        }
//        return mInnerAdapter.onCreateViewHolder(parent, viewType);
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
//        if (isHeaderViewPos(position)) {
//            return;
//        }
//        if (isFooterViewPos(position)) {
//            return;
//        }
//        mInnerAdapter.onBindViewHolder(viewHolder, position - getHeadersCount());
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (isHeaderViewPos(position)) {
//            return mHeaderViews.keyAt(position);
//        } else if (isFooterViewPos(position)) {
//            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
//        }
//        return mInnerAdapter.getItemViewType(position - getHeadersCount());
//    }
//
//    private int getRealItemCount() {
//        return mInnerAdapter.getItemCount();
//    }
//
//    @Override
//    public int getItemCount() {
//        return getHeadersCount() + getFootersCount() + getRealItemCount();
//    }
//
//
//    private boolean isHeaderViewPos(int position) {
//        return position < getHeadersCount();
//    }
//
//    private boolean isFooterViewPos(int position) {
//        return position >= getHeadersCount() + getRealItemCount();
//    }
//
//    //add Headers 不重复添加头布局:
//    public void addHeaderView(View view) {
//        int i = mHeaderViews.indexOfValue(view);
//        if (M_DEFAULT_NUMBER == i) {
//            mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
//        }
//    }
//
//    public void addFootView(View view) {
//        int i = mFootViews.indexOfValue(view);
//        if (M_DEFAULT_NUMBER == i) {
//            mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
//        }
//    }
//
//    public int getHeadersCount() {
//        return mHeaderViews.size();
//    }
//
//    public int getFootersCount() {
//        return mFootViews.size();
//    }
//
//    //针对网格布局:
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
//        final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    //如果是头布局或者是脚布局返回为1;
//                    int itemViewType = getItemViewType(position);
//                    if (mHeaderViews.get(itemViewType) != null || mFootViews.get(itemViewType) != null) {
//                        return ((GridLayoutManager) manager).getSpanCount();
//                    }
//                    return 1;
//                }
//            });
//        }
//    }
//
//    //针对流式布局
//    @Override
//    public void onViewAttachedToWindow(BaseViewHolder holder) {
//        mInnerAdapter.onViewAttachedToWindow(holder);
//        int layoutPosition = holder.getLayoutPosition();
//        if (isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition)) {
//            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//            if (layoutParams != null) {
//                if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
//                    StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
//                    //占领全部空间;
//                    params.setFullSpan(true);
//                }
//            }
//        }
//    }
//
//    //remover header;
//    public boolean removedHeader(View view) {
//        int i = mHeaderViews.indexOfValue(view);
//        if (i == M_DEFAULT_NUMBER) {
//            return false;
//        }
//        mHeaderViews.removeAt(i);
//        notifyItemRemoved(i);
//        return true;
//    }
//
//    //remover footer
//    public boolean removedFooter(View view) {
//        int i = mFootViews.indexOfValue(view);
//        if (i == M_DEFAULT_NUMBER) {
//            return false;
//        }
//        mFootViews.removeAt(i);
//        notifyItemRemoved(i);
//        return true;
//    }
//
//    //remover all footerview;
//    public boolean removerAllFooterView() {
//        if (mFootViews.size() > 0) {
//            mFootViews.clear();
//            notifyDataSetChanged();
//            return true;
//        }
//        return false;
//    }
//
//    //remover all footerview;
//    public boolean removerAllHeaderView() {
//        if (mHeaderViews.size() > 0) {
//            mHeaderViews.clear();
//            notifyDataSetChanged();
//            return true;
//        }
//        return false;
//    }
//
//
//}