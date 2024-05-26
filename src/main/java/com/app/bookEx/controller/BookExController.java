package com.app.bookEx.controller;

import com.app.bookEx.domain.pojo.PostDetails;
import com.app.bookEx.domain.pojo.Requests;
import com.app.bookEx.domain.pojo.UserDetails;
import com.app.bookEx.domain.pojo.model.Message;
import com.app.bookEx.service.PostDetailsClass;
import com.app.bookEx.service.RequestClass;
import com.app.bookEx.service.UserDetailsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
public class BookExController {

    @Autowired
    UserDetailsClass userDetail;

    @Autowired
    PostDetailsClass postDetail;

    @Autowired
    RequestClass requestsClass;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping(value = "/getUsers", produces = { "application/json" })
    public List<UserDetails> getAllUsers() {
        return userDetail.getAllUsers();
    }
    @PostMapping(value = "/validateUserCredentials", produces = { "application/json" })
    public String getUsers(@RequestBody String body) {
        return userDetail.getUser(body);
    }
    @PostMapping(value="/createUser",produces = {"application/json"})
    public String saveUser(@RequestBody String body){
        return userDetail.saveUser(body);
    }
    @GetMapping(value ="/getNewPosts", produces = {"application/json"})
    public String getNewPosts(){return postDetail.getNewPosts();}
    @PostMapping(value = "/uploadPost", produces = {"application/json"})
    public String uploadPost(@RequestBody String body){return postDetail.uploadPost(body);}
    @GetMapping(value = "/getAllTypesGenre", produces = {"application/json"})
    public String getAllTypeGenre(){return  postDetail.getGenres();}
    @PostMapping(value="/getPostsByGenre",produces = {"application/json"})
    public  String getPostsByGenre(@RequestBody String body){return  postDetail.getPostsByGenre(body);}
    @PostMapping(value="/getPostsOfUser",produces = {"application/json"})
    public  String getPostsOfUser(@RequestBody String body){return  postDetail.getPostsOfUser(body);}
    @PostMapping(value="/sendRequest",produces = {"application/json"})
    public  String sendRequest(@RequestBody String body){return  requestsClass.sendRequest(body);}
    @GetMapping("/getActiveRequests")
    @ResponseBody
    public List<Requests> getActiveRequests(@RequestParam Integer id) {
        return requestsClass.getRequestForId(id);
    }
    @MessageMapping(value="/private-message")
    public Message recievePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        return message;
    }
}
