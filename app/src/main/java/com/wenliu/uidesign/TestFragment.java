package com.wenliu.uidesign;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    private static final String TAG = "Wen-TestFragment";
    private View root;
    private CameraView mCameraView;
    private FrameLayout mFrameLayout;
    private TextView  mTextView;
    private String titles[] = new String[]{"Android", "iOS", "PHP", "Python", "Html"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_test, container, false);
        mTextView = root.findViewById(R.id.text_show_position);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCameraView = new CameraView(getActivity(), titles, new CameraViewCallback() {
            @Override
            public void onClickCamera(int position) {
                mTextView.setText(String.valueOf(position));
            }
        });
        mFrameLayout = root.findViewById(R.id.test_frame);
        mFrameLayout.addView(mCameraView);
//        mCameraView.init();
    }
}
