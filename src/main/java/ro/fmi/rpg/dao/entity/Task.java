package ro.fmi.rpg.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 11/5/2016.
 */
@Entity
@Table(name = "Tasks")
public class Task implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "difficulty")
    private int difficulty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finished_date")
    private Date finishedDate;

    @Column(name = "EMAIL_LATE_NOTIFICATION")
    private boolean sentLateNotification;

    @Column(name = "EMAIL_BEFORE_DUEDATE")
    private boolean sentBeforeDueDateNotificaiton;

    @Column(name = "ACCORDED_PENALTY")
    private boolean accordedPenalty;

    public Task() {
    }

    public Task(String name, String description, Date dueDate, boolean isFinished, Category category, boolean sentLateNotification, boolean sentBeforeDueDateNotificaiton) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isFinished = isFinished;
        this.category = category;
        this.sentLateNotification = sentLateNotification;
        this.sentBeforeDueDateNotificaiton = sentBeforeDueDateNotificaiton;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public boolean isSentLateNotification() {
        return sentLateNotification;
    }

    public void setSentLateNotification(boolean sentLateNotification) {
        this.sentLateNotification = sentLateNotification;
    }

    public boolean isSentBeforeDueDateNotificaiton() {
        return sentBeforeDueDateNotificaiton;
    }

    public void setSentBeforeDueDateNotificaiton(boolean sentBeforeDueDateNotificaiton) {
        this.sentBeforeDueDateNotificaiton = sentBeforeDueDateNotificaiton;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public boolean isAccordedPenalty() {
        return accordedPenalty;
    }

    public void setAccordedPenalty(boolean accordedPenalty) {
        this.accordedPenalty = accordedPenalty;
    }
}
