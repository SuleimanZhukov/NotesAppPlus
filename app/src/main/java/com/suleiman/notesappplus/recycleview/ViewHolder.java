package com.suleiman.notesappplus.recycleview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.suleiman.notesappplus.ListFragment;
import com.suleiman.notesappplus.R;
import com.suleiman.notesappplus.card.CardData;

public class ViewHolder extends RecyclerView.ViewHolder {
    public final TextView title;
    public final TextView description;
    public final TextView date;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title_view);
        description = itemView.findViewById(R.id.description_view);
        date = itemView.findViewById(R.id.date_view);
    }

    public void populate(ListFragment fragment, CardData data) {
        title.setText(data.getTitle());
        description.setText(data.getDescription());
        date.setText(data.getDate());
        itemView.setOnLongClickListener(v -> {
            fragment.setLastSelectedPosition(getLayoutPosition());
            return false;
        });
        fragment.registerForContextMenu(itemView);
    }

    public void clear(Fragment fragment) {
        itemView.setOnLongClickListener(null);
        fragment.unregisterForContextMenu(itemView);
    }
}
