package com.example.hepl_demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tutorials")
public class Tutorial {
    @Id
    @Column(name = "id", length=50)
    private int id;


    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "description", length = 500)
private String description;


    @Column(name = "published", length = 500)
private String published;

    public Tutorial(int id, String title, String description, String published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public Tutorial() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", published='" + published + '\'' +
                '}';
    }
}
