package com.suleiman.notesappplus;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view_layout, container, false);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(inflater, new CardDataSourceImpl(getResources()));
        adapter.setOnClickListener((view, position) -> {
            DetailsFragment fragment = new DetailsFragment();
            goToFragment(fragment);
        });

        recyclerView.setAdapter(adapter);

        viewGroup.addView(recyclerView);

        return viewGroup;
    }

    private void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView description;
        public final TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_view);
            description = itemView.findViewById(R.id.desciption_view);
            date = itemView.findViewById(R.id.date_view);
        }

        public void populate(CardData data) {
            title.setText(data.getTitle());
            description.setText(data.getDescription());
            date.setText(data.getDate());
        }
    }

    private interface OnClickListener {
        void onItemClick(View view, int position);
    }

    private static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        private final LayoutInflater mInflater;
        private final CardDataSource mDataSource;
        private OnClickListener mOnClickListener;

        public Adapter(LayoutInflater inflater, CardDataSource dataSource) {
            mInflater = inflater;
            mDataSource = dataSource;
        }

        public void setOnClickListener(OnClickListener onClickListener) {
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
            holder.populate(cardData);
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
}