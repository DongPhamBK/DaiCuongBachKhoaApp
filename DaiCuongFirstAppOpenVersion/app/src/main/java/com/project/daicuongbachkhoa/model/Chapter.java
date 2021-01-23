package com.project.daicuongbachkhoa.model;

public class Chapter {
private String chapterCode,chapterName,chapterContent;

    public Chapter() {
    }

    public Chapter(String chapterCode, String chapterName, String chapterContent) {
        this.chapterCode = chapterCode;
        this.chapterName = chapterName;
        this.chapterContent = chapterContent;
    }

    public String getChapterCode() {
        return chapterCode;
    }

    public void setChapterCode(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }
}
