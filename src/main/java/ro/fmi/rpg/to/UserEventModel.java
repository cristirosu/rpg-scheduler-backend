package ro.fmi.rpg.to;

import java.util.Date;

/**
 * Created by User on 2/7/2017.
 */
public class UserEventModel {

    private String text;
    private String name;
    private String picture;
    private Date time;

    public UserEventModel() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
