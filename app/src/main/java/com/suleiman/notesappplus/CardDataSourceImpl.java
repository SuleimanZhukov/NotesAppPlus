package com.suleiman.notesappplus;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDataSourceImpl implements CardDataSource {
    private final LinkedList<CardData> mData = new LinkedList<>();

    public CardDataSourceImpl(Resources resources) {

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
