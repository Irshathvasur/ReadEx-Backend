package com.app.bookEx.domain.pojo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="postdetails")
public class PostDetails implements Serializable {

    @Id
    private Integer postid;
    private String image;
    private Integer userid;
    private String author;
    private String title;
    private String genre;
    private String coverid;
    private Boolean status;

    public PostDetails(){}

    public PostDetails(Integer postId, String title, String author, String genre, String image,String coverId,Boolean status) {
        this.author = author;
        this.image = image;
        this.coverid = coverId;
        this.genre = genre;
        this.title = title;
        this.postid = postId;
        this.status = status;
    }

    public PostDetails(Object[] row) {
        this.postid = (Integer) row[0];
        this.author = (String) row[1];
        this.title = (String) row[2];
        this.image = (String) row[3];
        this.coverid = (String) row[4];
        this.genre = (String) row[5];
    }
    @Id
    public Integer getId(){
        return this.postid;
    }

    public void setId(Integer id){this.postid=id;}

    public Integer getUserid(){return this.userid;}

    public void setUserid(Integer userId){ this.userid=userId;}

    @Column
    public String getTitle(){ return this.title; }

    public void setTitle(String title){ this.title=title;}

    @Column
    public String getGenre(){return this.genre;}

    public void setGenre(String genre){ this.genre=genre;}

    @Column
    public String getImage(){return this.image;}

    public void setImage(String image){ this.image=image;}

    @Column
    public String getAuthor(){return this.author;}

    public void setAuthor(String author){ this.author=author;}

    @Column(name = "coverid")
    public String getCoverId(){return this.coverid;}

    public void setCoverId(String coverId){ this.coverid=coverId;}

    @Column
    public Boolean getStatus(){return this.status;}

    public void setStatus(boolean status){ this.status=status;}
}
