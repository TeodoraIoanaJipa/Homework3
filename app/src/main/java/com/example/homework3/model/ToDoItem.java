package com.example.homework3.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ToDoItem {
    private Integer id;
    private int userId;
    private String title;
    private Boolean completed;

    public ToDoItem fromJSON(JSONObject itemJSON) throws JSONException {

        Integer toDoId = itemJSON.getInt("id");
        int toDoUserId = itemJSON.getInt("userId");
        String toDotitle = itemJSON.getString("title");
        Boolean toDoCompleted = itemJSON.getBoolean("completed");

        ToDoItem toDoItem = new ToDoItem(toDoId, toDoUserId, toDotitle, toDoCompleted);
        return toDoItem;
    }

    public ToDoItem(Integer id, int userId, String title, Boolean completed) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public ToDoItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
