package ro.fmi.rpg.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by User on 11/13/2016.
 */
@Entity
@Table(name = "Characters")
public class Character implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "experience")
    private int experience;

    @Column(name = "level")
    private int level;

    @Column(name = "picture")
    private String picture;

    @Column(name = "health")
    private int health;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Character() {
    }

    public Character(int experience, int level, String picture, User user) {
        this.experience = experience;
        this.level = level;
        this.picture = picture;
        this.user = user;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", experience=" + experience +
                ", level=" + level +
                ", picture='" + picture + '\'' +
                ", user=" + user +
                '}';
    }
}
