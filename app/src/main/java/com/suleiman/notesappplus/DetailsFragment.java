package com.suleiman.notesappplus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.suleiman.notesappplus.data.CardDataSource;
import com.suleiman.notesappplus.details.DetailsDataSource;
import com.suleiman.notesappplus.details.DetailsDataSourceImpl;

public class DetailsFragment extends Fragment {
    private DataTransfer dataTransfer;

    public interface DataTransfer {
        void sendTitle(String title);
    }

    private static final String ARG_KEY_DETAILS = "DetailsFragment.prefix";

    private int mKeyDetails;
    private EditText mTitle;
    private DetailsDataSource mDetailsDataSource;

    public DetailsFragment() {

    }

    public static DetailsFragment newInstance(int keyDetails) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_KEY_DETAILS, keyDetails);
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
            mKeyDetails = getArguments().getInt(ARG_KEY_DETAILS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        mDetailsDataSource = new DetailsDataSourceImpl();

        mTitle = view.findViewById(R.id.title_details);
        String titleText = mDetailsDataSource.get(mKeyDetails).getTitleDetails();

        mTitle.setText(titleText);

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