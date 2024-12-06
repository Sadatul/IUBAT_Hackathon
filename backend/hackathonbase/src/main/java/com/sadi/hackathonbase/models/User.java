package com.sadi.hackathonbase.models;

import com.sadi.hackathonbase.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private  String username;

    @Column(name = "full_name", nullable = false)
    private  String fullName;

    @Column(nullable = false)
    private  String password;

    @Column(nullable = false)
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column
    private String occupation;

    @Column
    private String hobbies;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(nullable = false)
    private Long score;

    public User(Long id){
        this.id = id;
    }

//    public User(String username, String password, String fullName) {
//        this.username = username;
//        this.password = password;
//        this.fullName = fullName;
//    }
//
//    public User(Long id, String username, String fullName, String password) {
//        this.id = id;
//        this.username = username;
//        this.fullName = fullName;
//        this.password = password;
//    }

    public User(String username, String password, String fullName, LocalDate dob, Gender gender) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.score = 0L;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", occupation='" + occupation + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", score=" + score +
                '}';
    }
}
