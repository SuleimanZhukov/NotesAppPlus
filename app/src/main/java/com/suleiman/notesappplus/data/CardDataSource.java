package com.suleiman.notesappplus.data;

import java.util.List;

public interface CardDataSource {
    List<CardData> getCardData();
    CardData getItemAt(int idx);
    int getItemCount();

    void add(CardData data);
    void remove(int idx);
    void clear();
}
