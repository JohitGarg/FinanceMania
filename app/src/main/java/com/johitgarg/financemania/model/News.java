package com.johitgarg.financemania.model;

public class News {

    private String sourceUrl;
    private String dateAndTime;
    private String title;
    private String description;
    private String imageUrl;
    private String completeUrl;

    public News() {
    }

    public News(String sourceUrl, String dateAndTime, String title, String description, String imageUrl, String completeUrl) {
        this.sourceUrl = sourceUrl;
        this.dateAndTime = dateAndTime;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.completeUrl = completeUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }
}
