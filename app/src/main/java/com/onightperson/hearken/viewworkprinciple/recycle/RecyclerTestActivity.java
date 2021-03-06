package com.onightperson.hearken.viewworkprinciple.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;

import com.onightperson.hearken.R;
import com.onightperson.hearken.viewworkprinciple.recycle.adapter.ContentAdapter;
import com.onightperson.hearken.viewworkprinciple.recycle.model.ContentBase;
import com.onightperson.hearken.viewworkprinciple.recycle.utils.DataUtils;

import java.util.List;


/**
 * Created by liubaozhu on 17/2/21.
 */

public class RecyclerTestActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "RecyclerTestActivity";
    private RecyclerView mRecyclerView;

    private float mCurRawY;
    private int mOffset = 0;
    private boolean isBottomNeedInit = true;
    private boolean isNeedDealDragDown = false;

    private List<ContentBase> mContentList;
    private ContentAdapter.AnimViewHolder mAnimViewHoler;
    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        initViews();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mContentList = DataUtils.getContactInfoList();
        mRecyclerView.setAdapter(new ContentAdapter(mContentList) {
            @Override
            public void convert(StudentInfoHolder viewHolder, int position) {
                viewHolder.mLayoutContentView.getLayoutParams().width = getScreenWidth();
                viewHolder.mNameView.getLayoutParams().width = getScreenWidth();
            }
        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (!isSlideToBottom(rv)) {
                    Log.d(TAG, "onInterceptTouchEvent: isNeedDealDragDown: " + isNeedDealDragDown
                            + ", 是否松手： " + (e.getAction() == MotionEvent.ACTION_UP));
                    if (isNeedDealDragDown && e.getAction() == MotionEvent.ACTION_UP) {
                        mAnimViewHoler.bottomLayout.onDropDown();
                        isBottomNeedInit = true;
                        isNeedDealDragDown = false;
                    }
                    return false;
                } else if (isBottomNeedInit) {
                    if (mAnimViewHoler == null) {
                        mAnimViewHoler = (ContentAdapter.AnimViewHolder)
                                rv.findViewHolderForAdapterPosition(mContentList.size()-1);
                    }

                    mAnimViewHoler.bottomLayout.initDragOp(e.getRawY());

                    isNeedDealDragDown = true;
                    isBottomNeedInit = false;
                }

                int action = e.getAction();

                mAnimViewHoler.bottomLayout.addVelocityTracker(e);
                float y = e.getRawY();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        if (mAnimViewHoler != null) {
                            mAnimViewHoler.bottomLayout.onDragUp(y);

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isBottomNeedInit = true;
                        mAnimViewHoler.bottomLayout.onDropDown();
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        int verticalScrollExtent = recyclerView.computeVerticalScrollExtent();
        int scrollOffset = recyclerView.computeVerticalScrollOffset();
        int verticalScrollRange = recyclerView.computeVerticalScrollRange();
        return verticalScrollExtent + scrollOffset
                >= verticalScrollRange;
    }


    @Override
    public void onClick(View v) {
    }
}
