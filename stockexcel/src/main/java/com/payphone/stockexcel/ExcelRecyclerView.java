package com.payphone.stockexcel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.stockexcel.library.adapter.ExcelAdapter;

import java.util.HashSet;

/**
 * create time : 2023/9/6 13:21
 * create by : xupengpeng
 */
public class ExcelRecyclerView extends FrameLayout {
    private RecyclerView mContentRecyclerView;
    private RecyclerView mTopRecyclerView;

    private FrameLayout leftTopLayout;

    private ExcelAdapter mExcelAdapter;

    private TopItemAdapter topItemAdapter;
    private ContentItemAdapter contentItemAdapter;

    private int LeftWidth = 150;

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public ExcelRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public ExcelRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ExcelRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setExcelAdapter(ExcelAdapter mExcelAdapter) {
        this.mExcelAdapter = mExcelAdapter;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void notifyDataSetChanged(){
        mTopRecyclerView.getAdapter().notifyDataSetChanged();
        mContentRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_excel_recyclerview, this,true);
        mContentRecyclerView = findViewById(R.id.contentRecyclerView);
        mTopRecyclerView = findViewById(R.id.topRecyclerView);
        leftTopLayout = findViewById(R.id.leftTopLayout);

        mContentRecyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

        LinearLayoutManager topManager = new LinearLayoutManager(context);
        topManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTopRecyclerView.setLayoutManager(topManager);
        new PagerSnapHelper().attachToRecyclerView(mTopRecyclerView);

        topItemAdapter = new TopItemAdapter();
        mTopRecyclerView.setAdapter(topItemAdapter);

        LinearLayoutManager columnManager = new LinearLayoutManager(context);
        columnManager.setOrientation(LinearLayoutManager.VERTICAL);
        mContentRecyclerView.setLayoutManager(columnManager);
        contentItemAdapter = new ContentItemAdapter(mTopRecyclerView);
        mContentRecyclerView.setAdapter(contentItemAdapter);
        leftTopLayout.setLayoutParams(new LayoutParams(dip2px(context, LeftWidth), dip2px(context,50)));
    }


    class TopItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return mExcelAdapter.createViewHolder(parent, ExcelAdapter.TOP_ITEM);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            mExcelAdapter.onTopTitleBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return mExcelAdapter.getTopItemCount();
        }
    }

    class ContentItemAdapter extends RecyclerView.Adapter<ContentItemAdapter.ViewHolder> {
        private int firstPos = -1;
        private int firstOffset = -1;
        private HashSet<RecyclerView> observerList = new HashSet<>();

        private RecyclerView topRecyclerView;

        public ContentItemAdapter(RecyclerView recyclerView) {
            topRecyclerView = recyclerView;
            observerList.add(recyclerView);
            initRecyclerView(topRecyclerView);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
            ViewHolder holder = new ViewHolder(view);
            RecyclerView.ViewHolder viewHolder = mExcelAdapter.createViewHolder(parent, ExcelAdapter.LEFT_ITEM);
            holder.leftLayout.addView(viewHolder.itemView);
            holder.leftLayout.setTag(viewHolder);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int row) {
            mExcelAdapter.onLeftBindViewHolder((RecyclerView.ViewHolder) holder.leftLayout.getTag(), row);
            boolean show = mExcelAdapter.showTipContentLayout(holder.tipLayout,row);
            if (show){
                holder.tipLayout.setVisibility(VISIBLE);
            }else {
                holder.tipLayout.setVisibility(GONE);
            }
            LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.itemRecyclerView.setLayoutManager(manager);
            holder.itemRecyclerView.setAdapter(new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return mExcelAdapter.createViewHolder(parent, ExcelAdapter.CENTER_ITEM);
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int column) {
                    mExcelAdapter.onContentItemBindViewHolder(holder, row, column);
                }

                @Override
                public int getItemCount() {
                    return mExcelAdapter.getTopItemCount();
                }
            });
            initRecyclerView(holder.itemRecyclerView);
        }

        @SuppressLint("ClickableViewAccessibility")
        private void initRecyclerView(RecyclerView recyclerView) {
            recyclerView.setHasFixedSize(true);
            //为每一个recycleview创建layoutManager
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //todo
            // 通过移动layoutManager来实现列表滑动  此行是让新加载的item条目保持跟已经滑动的recycleview位置保持一致
            // 也就是上拉加载更多的时候  保证新加载出来的item 跟已经滑动的item位置保持一致
            if (layoutManager != null && firstPos > 0 && firstOffset > 0) {
                layoutManager.scrollToPositionWithOffset(firstPos + 1, firstOffset);
            }
            // 添加所有的 recyclerView
            observerList.add(recyclerView);
            recyclerView.setOnTouchListener((view, motionEvent) -> {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        for (RecyclerView rv : observerList) {
                            rv.stopScroll();
                        }
                }
                return false;
            });
            //添加当前滑动recycleview的滑动监听
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //获取显示第一个item的位置
                    int firstPos1 = linearLayoutManager.findFirstVisibleItemPosition();
                    View firstVisibleItem = linearLayoutManager.getChildAt(0);
                    if (firstVisibleItem != null) {
                        //获取第一个item的偏移量
                        int firstRight = linearLayoutManager.getDecoratedRight(firstVisibleItem);
                        //遍历其它的所有的recycleview条目
                        for (RecyclerView rv : observerList) {
                            if (recyclerView != rv) {
                                LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
                                if (layoutManager != null) {
                                    firstPos = firstPos1;
                                    firstOffset = firstRight;
                                    //通过当前显示item的位置和偏移量的位置来置顶recycleview 也就是同步其它item的移动距离
                                    layoutManager.scrollToPositionWithOffset(firstPos + 1, firstRight);
                                }
                            }
                        }
                        LinearLayoutManager topManager = (LinearLayoutManager) mTopRecyclerView.getLayoutManager();
                        topManager.scrollToPositionWithOffset(firstPos + 1, firstRight);
                    }
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mExcelAdapter.getColumnItemCount();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView itemRecyclerView;
            FrameLayout leftLayout;

            FrameLayout tipLayout;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemRecyclerView = itemView.findViewById(R.id.itemRecyclerView);
                leftLayout = itemView.findViewById(R.id.leftLayout);
                tipLayout = itemView.findViewById(R.id.tipLayout);
                leftLayout.setLayoutParams(new LayoutParams(dip2px(itemView.getContext(),LeftWidth), ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
    }




}
