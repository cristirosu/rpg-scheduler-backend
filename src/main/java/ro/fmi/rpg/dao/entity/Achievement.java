package ro.fmi.rpg.dao.entity;

import javax.persistence.*;

/**
 * Created by User on 12/20/2016.
 */
@Table(name = "Achievements")
@Entity
public class Achievement {

    @Column
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Achievement(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public Achievement(String name) {
        this.name = name;
    }

    public Achievement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
