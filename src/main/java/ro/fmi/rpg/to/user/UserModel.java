package ro.fmi.rpg.to.user;

import java.util.Date;

/**
 * Created by User on 12/18/2016.
 */
public class UserModel {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone_number;
    private Date birthDate;
    private CharacterModel character;
    private String password;
    private String picture;
    private boolean receivesToasts;
    private boolean receivesEmails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public CharacterModel getCharacter() {
        return character;
    }

    public void setCharacter(CharacterModel character) {
        this.character = character;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isReceivesToasts() {
        return receivesToasts;
    }

    public void setReceivesToasts(boolean receivesToasts) {
        this.receivesToasts = receivesToasts;
    }

    public boolean isReceivesEmails() {
        return receivesEmails;
    }

    public void setReceivesEmails(boolean receivesEmails) {
        this.receivesEmails = receivesEmails;
    }
}
