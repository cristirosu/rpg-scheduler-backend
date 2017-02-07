package ro.fmi.rpg.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/7/2017.
 */
@Entity
@Table(name = "USER_EVENT")
public class UserEvent {

    private Long id;
    private String description;
    private User user;
    private Date date;

    public UserEvent(Long id, String description, User user, Date date) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.date = date;
    }

    public UserEvent() {
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finished_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
