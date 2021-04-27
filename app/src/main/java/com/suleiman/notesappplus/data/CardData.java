package com.suleiman.notesappplus.data;

public class CardData {
    private String mTitle;
    private String mDescription;
    private String mDate;

    public CardData(String title, String description, String date) {
        mTitle = title;
        mDescription = description;
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }
}
