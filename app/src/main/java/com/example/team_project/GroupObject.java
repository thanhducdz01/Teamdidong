package com.example.team_project;

public class GroupObject {
    private int id;
    private int img_left;
    private String title;

    public GroupObject(int id, int img_left, String title) {
        this.id = id;
        this.img_left = img_left;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg_left() {
        return img_left;
    }

    public void setImg_left(int img_left) {
        this.img_left = img_left;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
