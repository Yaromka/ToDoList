package com.epam.todo.entity;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String description;
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, String tag) {
        this.description = description;
        this.tag = tag;
    }

    public Task(String description, String tag, User author) {
        this.description = description;
        this.tag = tag;
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
