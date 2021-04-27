package com.suleiman.notesappplus.details;

import java.util.List;

public interface DetailsDataSource {
    List<DetailsData> getCardData();
    DetailsData getItemAt(int idx);
    int getItemCount();

    void add(DetailsData data);
    void remove(int idx);
    void clear();
}
