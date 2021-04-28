package com.suleiman.notesappplus.details;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DetailsDataSourceImpl implements DetailsDataSource {
    private final HashMap<Integer, DetailsData> mDetailsMap = new HashMap<>();

    public DetailsDataSourceImpl() {

    }

    @Override
    public Map<Integer, DetailsData> getDetailsData() {
        return mDetailsMap;
    }

    @Override
    public int getItemCount() {
        return mDetailsMap.size();
    }

    @Override
    public void remove(int key) {
        mDetailsMap.remove(key);
    }

    @Override
    public void clear() {
        mDetailsMap.clear();
    }

    @Override
    public void put(int pos, DetailsData data) {
        Integer key = pos;
        mDetailsMap.put(key, data);
    }

    @Override
    public DetailsData get(int pos) {
        Integer key = pos;
        return mDetailsMap.get(key);
    }
}
