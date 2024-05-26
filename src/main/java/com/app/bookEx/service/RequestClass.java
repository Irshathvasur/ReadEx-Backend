package com.app.bookEx.service;

import com.app.bookEx.domain.pojo.PostDetails;
import com.app.bookEx.domain.pojo.Requests;
import com.app.bookEx.repository.PostDetailsRepo;
import com.app.bookEx.repository.RequestsRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestClass implements RequestsInterface{

    @Autowired
    RequestsRepo reqRepo;
    @Autowired
    PostDetailsRepo postDetailsRep;
    @Override
    public String sendRequest(String body) {
        JSONObject retObj=new JSONObject();
        Requests newRequest=new Requests();
        Integer lastReqId=reqRepo.getRequestCount();
        if(lastReqId==null){
            lastReqId=0;
        }
        try{
            JSONObject jObj=new JSONObject(body);
            newRequest.setId(++lastReqId);
            newRequest.setReceiverName(jObj.getString("receiver")==null?"":jObj.getString("receiver").trim());
            newRequest.setSenderName(jObj.getString("sender")==null?"":jObj.getString("sender").trim());
            newRequest.setReceiverId(jObj.getInt("receiverId"));
            newRequest.setSenderId(jObj.getInt("senderId"));
            newRequest.setOfferedBook(postDetailsRep.getPostById(jObj.getInt("offeredBook")));
            newRequest.setPostid(postDetailsRep.getPostById(jObj.getInt("postId")));
        }
        catch(Exception ex){return ex.getMessage();}
        try{
            reqRepo.save(newRequest);
        }catch(Exception ex){
            retObj.put("message","There was some problem sending your Request");
            retObj.put("error",ex.getMessage());
            return retObj.toString();
        };
        retObj.put("message","Your Request is Sent!");
        return retObj.toString();
    }

    @Override
    public List<Requests> getRequestForId(Integer id) {
        return reqRepo.getActiveRequests(id);
    }
}
