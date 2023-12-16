package com.example.hepl_file_upload.model;


import jakarta.persistence.*;

@Entity
@Table(name="hepl")
public class Hepl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id",length=200)
    private long id;
    @Column(name="name",length = 200)
    private String name;

    public Hepl(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
