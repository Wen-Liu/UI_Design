package com.wenliu.uidesign;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Wen-MainActivity";
    private ArrayList<String> images = new ArrayList<>();
    private String titles[] = new String[]{"AndroidAndroidAndroid", "IOS", "PHP", "Python", "Html"};
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PagerSnapHelper mPagerSnapHelper;
    private View.OnScrollChangeListener mOnScrollChangeListener;
    private boolean isScroll = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        images.add(0, "http://personal.psu.edu/xqz5228/jpg.jpg");
        images.add(1, "http://www.personal.psu.edu/jyc5774/jpg.jpg");
        images.add(2, "http://personal.psu.edu/xqz5228/jpg.jpg");
        images.add(3, "http://www.personal.psu.edu/jyc5774/jpg.jpg");
        images.add(4, "http://personal.psu.edu/xqz5228/jpg.jpg");

        initRecyclerView();
    }

    private void initTab() {
        mTabLayout = findViewById(R.id.tlTabTitle);

//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.part2).setText(titles[0]).setTag(Constans.TAG_ANDROID));
        View view = LayoutInflater.from(this).inflate(R.layout.part2, null);
        TextView view1 = view.findViewById(R.id.text3);
        view1.setText(titles[0]);
        TabLayout.Tab tab = mTabLayout.newTab().setCustomView(view);

        mTabLayout.addTab(tab);


        mTabLayout.addTab(mTabLayout.newTab().setText(titles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[3]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[4]));

        mOnScrollChangeListener = new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (isScroll) {
                    Log.i(TAG, "onScrollChange: " + i + " ," + i1 + " ," + i2 + " ," + i3);
                    mTabLayout.setScrollPosition(mLayoutManager.findFirstVisibleItemPosition(), 0f, true);
                }
            }
        };
    }

    private void initRecyclerView() {
        initTab();
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(images);
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
}
