package com.project.daicuongbachkhoa.model;

public class Exam {
   private String examCode, examName, examTeacher, examDeadline;

    public Exam() {
    }

    public Exam(String examCode, String examName, String examTeacher, String examDeadline) {
        this.examCode = examCode;
        this.examName = examName;
        this.examTeacher = examTeacher;
        this.examDeadline = examDeadline;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamTeacher() {
        return examTeacher;
    }

    public void setExamTeacher(String examTeacher) {
        this.examTeacher = examTeacher;
    }

    public String getExamDeadline() {
        return examDeadline;
    }

    public void setExamDeadline(String examDeadline) {
        this.examDeadline = examDeadline;
    }
}
