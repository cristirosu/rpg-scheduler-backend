package ro.fmi.rpg.dao.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 11/1/2016.
 */
@Entity
@Table(name = "Users")
public class User implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 3, max = 15)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 3, max = 15)
    private String lastName;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Size(min = 10, max = 10)
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_login")
    private Date lastLogin;

    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "password")
    private String password;

    @Column(name = "confirmed")
    private boolean confirmed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Category> categories = new HashSet<Category>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Achievement> achievements;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Character character;

    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, Date birthDate, Date createdAt, Date lastLogin, String password, boolean confirmed, Set<Category> categories, Character character) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.password = password;
        this.confirmed = confirmed;
        this.categories = categories;
        this.character = character;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }
}
