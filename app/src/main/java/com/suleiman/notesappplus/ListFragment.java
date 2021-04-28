package com.suleiman.notesappplus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.suleiman.notesappplus.data.CardData;
import com.suleiman.notesappplus.data.CardDataSource;
import com.suleiman.notesappplus.data.CardDataSourceImpl;
import com.suleiman.notesappplus.details.DetailsData;
import com.suleiman.notesappplus.details.DetailsDataSource;
import com.suleiman.notesappplus.details.DetailsDataSourceImpl;
import com.suleiman.notesappplus.recycleview.Adapter;

import java.nio.channels.MembershipKey;

public class ListFragment extends Fragment {

    private static final String ARG_PARAM = "ListFragment.prefix";

    private int mLastSelectedPosition = -1;

    private String mParam;
    private CardDataSource mCardDataSource;
    private DetailsDataSource mDetailsDataSource;
    private Adapter mAdapter;

    private TextView title;
    private String mTitle;
    private int mKey = 1;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        FloatingActionButton floatingButton = viewGroup.findViewById(R.id.floating_action_button);
        AppCompatImageView deleteCard = viewGroup.findViewById(R.id.card_delete_icon);
        Button toastButton = viewGroup.findViewById(R.id.toast_button);
        toastButton.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Count: " + mDetailsDataSource.getItemCount(), Toast.LENGTH_SHORT).show();
        });

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view_layout, container, false);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);

        mDetailsDataSource = new DetailsDataSourceImpl();
        mCardDataSource = new CardDataSourceImpl(getResources());
        mAdapter = new Adapter(this, mCardDataSource);
        mAdapter.setOnClickListener((view, position) -> {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(mKey);
            addFragment(detailsFragment);
        });

        floatingButtonListener(floatingButton, recyclerView);

        recyclerView.setAdapter(mAdapter);
        viewGroup.addView(recyclerView);

        return viewGroup;
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.option_edit) {

        } else if (item.getItemId() == R.id.option_sort) {

        } else if (item.getItemId() == R.id.option_view) {

        } else if (item.getItemId() == R.id.option_clear) {
            mCardDataSource.clear();
            mAdapter.notifyDataSetChanged();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.context_menu_edit) {
            if (mLastSelectedPosition != -1) {

            }
        } else if (item.getItemId() == R.id.context_menu_delete) {
            if (mLastSelectedPosition != -1) {
                mDetailsDataSource.remove(mLastSelectedPosition);
                mCardDataSource.remove(mLastSelectedPosition);
                mAdapter.notifyItemRemoved(mLastSelectedPosition);
            }
        } else {
            return super.onContextItemSelected(item);
        }
        return true;
    }

    public void setLastSelectedPosition(int lastSelectedPosition) {
        mLastSelectedPosition = lastSelectedPosition;
    }

    public interface OnClickListener {
        void onItemClick(View view, int position);
    }

    private void floatingButtonListener(FloatingActionButton floatingButton, RecyclerView recyclerView) {
        floatingButton.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Key: " + mKey, Toast.LENGTH_SHORT).show();
            mCardDataSource.add(new CardData(mKey, "New note", "No description", "25.04.2021"));
            mDetailsDataSource.put(mKey, new DetailsData("New note",
                    "No description", "25.04.2021", "25.04.2021"));
            int position = mCardDataSource.getItemCount() - 1;
            mAdapter.notifyItemInserted(position);
            recyclerView.smoothScrollToPosition(position);
        });
    }

    private void deleteCardListener(ImageView deleteCard) {
        deleteCard.setOnClickListener(v -> {
            mCardDataSource.remove(mLastSelectedPosition);
            mAdapter.notifyItemRemoved(mLastSelectedPosition);
        });
    }

    public void updateTitleText(String title) {
        mTitle = title;
    }

    public void setKey(int key) {
        mKey = key;
    }

    public String getTitle() {
        return mTitle;
    }
}