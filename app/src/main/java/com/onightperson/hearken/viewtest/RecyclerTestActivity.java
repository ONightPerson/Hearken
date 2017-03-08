package com.onightperson.hearken.viewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.onightperson.hearken.R;
import com.onightperson.hearken.viewtest.adapter.ContentAdapter;
import com.onightperson.hearken.viewtest.model.ContentBase;
import com.onightperson.hearken.viewtest.utils.DataUtils;

import java.util.List;


/**
 * Created by liubaozhu on 17/2/21.
 */

public class RecyclerTestActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "RecyclerTestActivity";
    private RecyclerView mRecyclerView;

    private float mCurRawY;
    private int mOffset = 0;
    private boolean isBottomInit = true;
    private boolean isDealDragDown = false;

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
        mRecyclerView.setAdapter(new ContentAdapter(mContentList));


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d(TAG, "onInterceptTouchEvent--isBottomInit: " + isBottomInit);
                if (!isSlideToBottom(rv)) {
                    if (isDealDragDown && e.getAction() == MotionEvent.ACTION_UP) {
                        mAnimViewHoler.bottomLayout.onDropDown();
                        isBottomInit = false;
                        isDealDragDown = false;
                    }
                    return false;
                } else if (isBottomInit) {
                    Log.d(TAG, "onInterceptTouchEvent: init animview holer");
                    if (mAnimViewHoler == null) {
                        mAnimViewHoler = (ContentAdapter.AnimViewHolder)
                                rv.findViewHolderForAdapterPosition(mContentList.size()-1);
                    }
                    Log.d(TAG, "onInterceptTouchEvent: init animviewm holder finished");

                    mAnimViewHoler.bottomLayout.initDragOp(e.getRawY());

                    mAnimViewHoler.bottomLayout.addVelocityTracker(e);
                    isDealDragDown = true;
                    isBottomInit = false;
                }

                int action = e.getAction();

                mAnimViewHoler.bottomLayout.addVelocityTracker(e);
                Log.d(TAG, "onInterceptTouchEvent: action: " + action);
                float y = e.getRawY();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        if (mAnimViewHoler != null) {
                            Log.d(TAG, "onInterceptTouchEvent: onDragUp before");
                            mAnimViewHoler.bottomLayout.onDragUp(y);
                            Log.d(TAG, "onInterceptTouchEvent: onDragUp after");

                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        isBottomInit = true;
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

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        int verticalScrollExtent = recyclerView.computeVerticalScrollExtent();
        int scrollOffset = recyclerView.computeVerticalScrollOffset();
        int verticalScrollRange = recyclerView.computeVerticalScrollRange();
        Log.i(TAG, "isSlideToBottom--verticalScrollExtent : " + verticalScrollExtent + "scrollOffset  :" + scrollOffset + " verticalScrollRange :" + verticalScrollRange);
        return verticalScrollExtent + scrollOffset
                >= verticalScrollRange;
    }


    @Override
    public void onClick(View v) {
    }
}
