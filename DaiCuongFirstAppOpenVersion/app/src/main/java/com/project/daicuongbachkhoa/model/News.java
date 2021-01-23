package com.project.daicuongbachkhoa.model;

public class News {
    private String titleNews, timeNews, contentNews;

    public News() {
    }

    public News(String titleNews, String timeNews, String contentNews) {
        this.titleNews = titleNews;
        this.timeNews = timeNews;
        this.contentNews = contentNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getTimeNews() {
        return timeNews;
    }

    public void setTimeNews(String timeNews) {
        this.timeNews = timeNews;
    }

    public String getContentNews() {
        return contentNews;
    }

    public void setContentNews(String contentNews) {
        this.contentNews = contentNews;
    }
}
