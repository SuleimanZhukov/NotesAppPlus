package com.suleiman.notesappplus.details;

public class DetailsData {
    private String mTitleDetails;
    private String mDescriptionDetails;
    private String mDateCreated;
    private String mDateModified;

    public DetailsData(String titleDetails, String descriptionDetails, String dateCreated, String dateModified) {
        mTitleDetails = titleDetails;
        mDescriptionDetails = descriptionDetails;
        mDateCreated = dateCreated;
        mDateModified = dateModified;
    }

    public String getTitleDetails() {
        return mTitleDetails;
    }

    public void setTitleDetails(String titleDetails) {
        mTitleDetails = titleDetails;
    }

    public String getDescriptionDetails() {
        return mDescriptionDetails;
    }

    public void setDescriptionDetails(String descriptionDetails) {
        mDescriptionDetails = descriptionDetails;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDate(String dateCreated) {
        mDateCreated = dateCreated;
    }

    public String getDateModified() {
        return mDateModified;
    }

    public void setDateModified(String dateModified) {
        mDateModified = dateModified;
    }
}
