package com.wenliu.uidesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> {

    private List<String> mStrings;

    public RecyclerViewAdapter(List<String> stringList) {
        mStrings = stringList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.part, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CardViewHolder holder, int position) {
        holder.getTextView().setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public CardViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.text);
        }

        public TextView getTextView() {
            return mTextView;
        }
    }
}
