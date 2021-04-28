package com.suleiman.notesappplus.details;

import java.util.Map;

public interface DetailsDataSource {
    Map<Integer, DetailsData> getDetailsData();
    int getItemCount();

    void remove(int key);
    void clear();

    void put(int position, DetailsData data);
    DetailsData get(int key);
}
