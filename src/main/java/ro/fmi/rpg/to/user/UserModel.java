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
}
