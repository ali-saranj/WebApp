package com.example.webapp.ui.viewModel;

import com.example.webapp.data.model.retrofit.ResponseItem;

public class Post {
    String id;
    String title,description,image,data,username;

    public Post(String id, String title, String description, String image, String data, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.data = data;
        this.username = username;
    }
    public Post(ResponseItem post){
        this.id = post.getId();
        this.title = post.getTitel();
        this.description = post.getDescription();
        this.image = post.getImage();
        this.data = post.getData();
        this.username = post.getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
