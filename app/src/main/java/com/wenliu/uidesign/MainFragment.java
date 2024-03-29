package com.wenliu.uidesign;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Wen-MainFragment";
    private View root;
    private String titles[] = new String[]{"AndroidAndroidAndroid", "IOS", "PHP", "Python", "Html"};
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PagerSnapHelper mPagerSnapHelper;
    private View.OnScrollChangeListener mOnScrollChangeListener;
    private boolean isScroll = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.add_btn).setOnClickListener(this);
        initRecyclerView();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void initTab() {
        mTabLayout = root.findViewById(R.id.tlTabTitle);

//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.tab_main).setText(titles[0]).setTag(Constans.TAG_ANDROID));
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_main, null);
//        TextView view1 = view.findViewById(R.id.tab_btn);
//        view1.setText(titles[0]);
//        TabLayout.Tab tab = mTabLayout.newTab().setCustomView(view);

        mTabLayout.addTab(mTabLayout.newTab().setText(titles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[3]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[4]));

        mOnScrollChangeListener = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (isScroll) {
//                    Log.i(TAG, "onScrollChange: " + i + " ," + i1 + " ," + i2 + " ," + i3);
                    mTabLayout.setScrollPosition(mLayoutManager.findFirstVisibleItemPosition(), 0f, true);
                }
            }
        };
    }

    private void initRecyclerView() {
        initTab();
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(titles, new CameraViewCallback() {
            @Override
            public void onClickCamera(int position) {

            }
        });
        mRecyclerView.setAdapter(adapter);
        mPagerSnapHelper = new PagerSnapHelper();
        mPagerSnapHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setOnScrollChangeListener(mOnScrollChangeListener);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i(TAG, "onTouch: " + motionEvent.getAction());
                isScroll = true;
                return false;
            }
        });

        mTabLayout.addOnTabSelectedListener(tabSelectedListener);
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        mTabLayout.addOnTabSelectedListener(tabSelectedListener);
    }

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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_btn) {
            Log.i(TAG, "onClick: ");
        }
    }
}
