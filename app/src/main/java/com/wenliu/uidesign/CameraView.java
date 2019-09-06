package com.wenliu.uidesign;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class CameraView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "Wen-CameraView";
    private Context mContext;
    private CameraViewCallback mCallback;
    private String titles[];
    private View root;
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PagerSnapHelper mPagerSnapHelper;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private boolean isScroll = false;

    public CameraView(Context context, String[] datas, CameraViewCallback callback) {
        super(context);
        Log.i(TAG, "CameraView: ");
        mContext = context;
        titles = datas;
        mCallback = callback;
        init();
    }

    public void init() {
        root = LayoutInflater.from(mContext).inflate(R.layout.home_camera_layout, this, true);

        root.findViewById(R.id.add_btn).setOnClickListener(this);
        mTabLayout = root.findViewById(R.id.tlTabTitle);
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewAdapter = new RecyclerViewAdapter(titles, mCallback);
        mPagerSnapHelper = new PagerSnapHelper();

        for (String string : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(string));
        }

        mTabLayout.addOnTabSelectedListener(tabSelectedListener);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPagerSnapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setOnScrollChangeListener(mOnScrollChangeListener);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.i(TAG, "onTouch: " + motionEvent.getAction());
                isScroll = true;
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_btn) {
            mCallback.onClickCamera(-1);
        }
    }

    private View.OnScrollChangeListener mOnScrollChangeListener = new View.OnScrollChangeListener() {
        @Override
        public void onScrollChange(View view, int i, int i1, int i2, int i3) {
            if (isScroll) {
//                    Log.i(TAG, "onScrollChange: " + i + " ," + i1 + " ," + i2 + " ," + i3);
                mTabLayout.setScrollPosition(mLayoutManager.findFirstVisibleItemPosition(), 0f, true);
            }
        }
    };

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.i(TAG, "onTabSelected: ");
            isScroll = false;
            mRecyclerView.setOnScrollChangeListener(null);
            mPagerSnapHelper.attachToRecyclerView(null);
            mRecyclerView.smoothScrollToPosition(tab.getPosition());
            mPagerSnapHelper.attachToRecyclerView(mRecyclerView);
            mRecyclerView.setOnScrollChangeListener(mOnScrollChangeListener);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    private void onDestory() {


    }
}
