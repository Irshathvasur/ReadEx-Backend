package com.app.bookEx.domain.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requests {

    @Id
    private Integer id;
    private String senderName;
    private  Integer senderId;
    private String receiverName;
    private Integer receiverId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offeredbook", referencedColumnName = "id")
    private PostDetails offeredBook;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId", referencedColumnName = "id")
    private PostDetails postid;
}
