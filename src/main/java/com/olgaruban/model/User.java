package com.olgaruban.model;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


public class User {

    private int id;

    private String firstName;

    private String lastName;

    private String position;

    private String email;

    private String phone;

    private String password;

    private String photo;

    private Role role;

    private Date creationDate;


    public User(int id, String firstName, String lastName, String position, String email, String phone, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        setPassword(password);
        this.creationDate = new Date();
    }

    public User(int id, String firstName, String lastName, String position, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.creationDate = new Date();
    }

    public User(String firstName, String lastName, String position, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        setPassword(password);
        this.creationDate = new Date();
    }


    public User(String firstName, String lastName, String position, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        setPassword(password);
        this.creationDate = new Date();
    }

    public User(int id, String firstName, String lastName, String position, String email, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.role = role;
        this.creationDate = new Date();
    }

    public User() {
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        byte[] bytesOfMessage = new byte[0];
        try {
            bytesOfMessage = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] byte_password = md.digest(bytesOfMessage);
        this.password = byte_password.toString();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getRoleString() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getCreationDateTimestamp() {
        return creationDate.getTime();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + (role != null ? role : "");
    }
}
