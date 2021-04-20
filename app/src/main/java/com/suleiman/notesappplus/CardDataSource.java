package com.suleiman.notesappplus;

import java.util.List;

public interface CardDataSource {
    List<CardData> getCardData();
    CardData getItemAt(int idx);
    int getItemCount();
}
