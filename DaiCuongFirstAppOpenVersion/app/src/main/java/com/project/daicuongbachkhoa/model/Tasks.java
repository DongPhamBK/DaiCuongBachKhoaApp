package com.project.daicuongbachkhoa.model;

public class Tasks {
    private String titleTasks,contentTasks;

    public Tasks() {
    }

    public Tasks(String titleTasks, String contentTasks) {
        this.titleTasks = titleTasks;
        this.contentTasks = contentTasks;
    }

    public String getTitleTasks() {
        return titleTasks;
    }

    public void setTitleTasks(String titleTasks) {
        this.titleTasks = titleTasks;
    }

    public String getContentTasks() {
        return contentTasks;
    }

    public void setContentTasks(String contentTasks) {
        this.contentTasks = contentTasks;
    }
}
