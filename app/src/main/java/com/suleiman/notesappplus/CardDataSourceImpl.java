package com.suleiman.notesappplus;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDataSourceImpl implements CardDataSource {
    private final LinkedList<CardData> mData = new LinkedList<>();

    public CardDataSourceImpl(Resources resources) {
        String[] names = resources.getStringArray(R.array.names);
        String[] desc = resources.getStringArray(R.array.descs);

        for (int i = 0; i < names.length; i++) {
            mData.add(new CardData(names[i], desc[i], "21.04.2021"));
        }
    }

    @Override
    public List<CardData> getCardData() {
        return Collections.unmodifiableList(mData);
    }

    @Override
    public CardData getItemAt(int idx) {
        return mData.get(idx);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
