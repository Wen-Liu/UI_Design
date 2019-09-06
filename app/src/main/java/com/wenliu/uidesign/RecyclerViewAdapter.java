package com.wenliu.uidesign;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> {

    private String[] mStrings;
    private CameraViewCallback mCallback;

    public RecyclerViewAdapter(String[] stringList, CameraViewCallback callback) {
        mStrings = stringList;
        mCallback = callback;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CardViewHolder holder, final int position) {
        holder.getTextView().setText("On/Off " + position);
        holder.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i("Wen-", "onCheckedChanged: " + position);
            }
        });
        holder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onClickCamera(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStrings.length;
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private Switch mSwitch;
        private ImageView mImageView;

        public CardViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.camera_view_text);
            mSwitch = view.findViewById(R.id.camera_view__switch);
            mImageView = view.findViewById(R.id.camera_view_image);
        }

        public TextView getTextView() {
            return mTextView;
        }

        public Switch getSwitch() {
            return mSwitch;
        }

        public ImageView getImageView() {
            return mImageView;
        }

    }
}
