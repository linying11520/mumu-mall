package com.mumu.gateway.design.builder;

import lombok.Data;

@Data
public class Course {

    private String name;

    private String ppt;

    private String video;

    private String note;

    private String homework;

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
