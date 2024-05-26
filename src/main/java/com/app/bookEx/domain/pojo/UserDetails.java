package com.app.bookEx.domain.pojo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="userdetails")
public class UserDetails implements Serializable {

    @Id
    private Integer id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private Integer age;
    @Column
    private String username;
    @Column
    private String userpassword;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<PostDetails> posts = new ArrayList<>();

    public UserDetails(){}

    public  UserDetails(Integer id, String username){
        this.id=id;
        this.username=username;
    }
    public UserDetails(Integer id,String firstname,String lastname,String email,Integer age,String username,String userpassword){
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.age=age;
        this.username=username;
        this.userpassword=userpassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String firstname) {
       this.firstname=firstname;
    }

//    public List<PostDetails> getPosts() {
//        return posts;
//    }
//
//    // Setter method for posts
//    public void setPosts(List<PostDetails> posts) {
//        this.posts = posts;
//    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname=lastname;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public Integer getAge(){
        return this.age;
    }

    public void setAge(Integer age) {
        this.age=age;
    }


    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username=username;
    }


    public String getUserpassword(){
        return this.userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword=userpassword;
    }
}
