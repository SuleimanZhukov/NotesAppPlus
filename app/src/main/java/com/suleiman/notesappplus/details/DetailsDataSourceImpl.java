package com.suleiman.notesappplus.details;

import java.util.LinkedList;
import java.util.List;

public class DetailsDataSourceImpl implements DetailsDataSource {
    private final LinkedList<DetailsData> mDetailsData = new LinkedList<>();

    public DetailsDataSourceImpl() {

    }

    @Override
    public List<DetailsData> getCardData() {
        return mDetailsData;
    }

    @Override
    public DetailsData getItemAt(int idx) {
        return mDetailsData.get(idx);
    }

    @Override
    public int getItemCount() {
        return mDetailsData.size();
    }

    @Override
    public void add(DetailsData data) {

    }

    @Override
    public void remove(int idx) {
        mDetailsData.remove(idx);
    }

    @Override
    public void clear() {
        mDetailsData.clear();
    }
}
