package com.example.myapplication;

public class FeedBackClass {
    private String CustomerUsername ;
    private String descrip;
    private String CleanerUsername;
    private int reviewId;

    public FeedBackClass() {
    }

    public FeedBackClass(String customerUsername, String descrip, String cleanerUsername) {
        this.CustomerUsername = customerUsername;
        this.descrip = descrip;
        this.CleanerUsername = cleanerUsername;
        this.reviewId = reviewId;
    }

    public String getCustomerUsername() {
        return CustomerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        CustomerUsername = customerUsername;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getCleanerUsername() {
        return CleanerUsername;
    }

    public void setCleanerUsername(String cleanerUsername) {
        CleanerUsername = cleanerUsername;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}
