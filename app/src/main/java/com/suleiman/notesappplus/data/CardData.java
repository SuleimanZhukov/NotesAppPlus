package com.suleiman.notesappplus.data;

public class CardData {
    private int mKeyCard;
    private String mTitle;
    private String mDescription;
    private String mDate;

    public CardData(int keyCard, String title, String description, String date) {
        mKeyCard = keyCard;
        mTitle = title;
        mDescription = description;
        mDate = date;
    }

    public int getKeyCard() {
        return mKeyCard;
    }

    public void setKeyCard(int keyCard) {
        mKeyCard = keyCard;
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
