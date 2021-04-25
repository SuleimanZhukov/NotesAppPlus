package com.suleiman.notesappplus.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.suleiman.notesappplus.ListFragment;
import com.suleiman.notesappplus.R;
import com.suleiman.notesappplus.card.CardData;
import com.suleiman.notesappplus.card.CardDataSource;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private final LayoutInflater mInflater;
    private final CardDataSource mDataSource;
    private ListFragment.OnClickListener mOnClickListener;
    private ListFragment mListFragment;

    public Adapter(ListFragment listFragment, CardDataSource dataSource) {
        mListFragment = listFragment;
        mInflater = listFragment.getLayoutInflater();
        mDataSource = dataSource;
    }

    public void setOnClickListener(ListFragment.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardData cardData = mDataSource.getItemAt(position);
        holder.populate(mListFragment, cardData);
        holder.itemView.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSource.getItemCount();
    }
}
