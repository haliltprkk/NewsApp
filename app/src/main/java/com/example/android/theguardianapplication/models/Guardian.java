package com.example.android.theguardianapplication.models;

/**
 * Created by LENOVO on 16.12.2017.
 */

public class Guardian {
    String nameOfSection;
    String titleOfArticle;
    String webUrl;

    public String getNameOfSection() {
        return nameOfSection;
    }

    public void setNameOfSection(String nameOfSection) {
        this.nameOfSection = nameOfSection;
    }

    public String getTitleOfArticle() {
        return titleOfArticle;
    }

    public void setTitleOfArticle(String titleOfArticle) {
        this.titleOfArticle = titleOfArticle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Guardian(String nameOfSection, String titleOfArticle, String webUrl) {

        this.nameOfSection = nameOfSection;
        this.titleOfArticle = titleOfArticle;
        this.webUrl = webUrl;
    }
}
