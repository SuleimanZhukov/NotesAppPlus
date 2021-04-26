package com.suleiman.notesappplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.suleiman.notesappplus.card.CardData;
import com.suleiman.notesappplus.card.CardDataSource;
import com.suleiman.notesappplus.card.CardDataSourceImpl;

public class DetailsFragment extends Fragment {
    private DataTransfer dataTransfer;

    public interface DataTransfer {
        void sendTitle(String title);
    }

    private static final String ARG_PARAM = "DetailsFragment.prefix";

    private String mParam;
    private EditText mTitle;
    private CardDataSource mCardDataSource;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String param1) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dataTransfer = (DataTransfer) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " needs to implement DataTransfer interface.");
        }
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
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        mTitle = view.findViewById(R.id.title_details);

        Button save = view.findViewById(R.id.save_button);
        save.setOnClickListener(v -> {
            sendTitleText();
        });

        return view;
    }

    @Override
    public void onDetach() {
        dataTransfer = null;
        super.onDetach();
    }

    private void sendTitleText() {
        String titleText = mTitle.getText().toString();
        dataTransfer.sendTitle(titleText);
    }
}