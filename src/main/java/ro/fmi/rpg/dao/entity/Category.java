package ro.fmi.rpg.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 11/3/2016.
 */
@Entity
@Table(name = "Categories")
public class Category implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Task> tasks;

    @Column(name = "description")
    private String description;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, User user, List<Task> tasks) {
        this.name = name;
        this.user = user;
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", tasks=" + tasks +
                '}';
    }
}
