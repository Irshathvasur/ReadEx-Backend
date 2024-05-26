package com.app.bookEx.service;

import com.app.bookEx.domain.pojo.PostDetails;
import com.app.bookEx.repository.PostDetailsRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDetailsClass implements PostDetailsInterface{

    @Autowired
    PostDetailsRepo postDetailsRep;
    @Override
    public String getNewPosts() {
        List<JSONObject> retObj = new ArrayList<>();
        int count=0;
        for (Object[] result : postDetailsRep.getNewPosts()) {
            retObj.add(new JSONObject());
            retObj.get(count).put("id",result[1]==null?"":result[0]);
            retObj.get(count).put("author",result[1]==null?"":result[1]);
            retObj.get(count).put("title",result[1]==null?"":result[2]);
            retObj.get(count).put("image",result[1]==null?"":result[3]);
            retObj.get(count).put("coverid",result[1]==null?"":result[4].toString());
            retObj.get(count).put("genre",result[1]==null?"":result[5]);
            retObj.get(count).put("status",result[1]==null?"":result[6]);
            retObj.get(count).put("userid",result[1]==null?"":result[7]);
            retObj.get(count).put("username",result[1]==null?"":result[8]);
            count++;
        }
        return retObj.toString();
    }

    @Override
    public String uploadPost(String requestBody){
        JSONObject retObj=new JSONObject();
        PostDetails newPost=new PostDetails();
        Integer lastPostId=postDetailsRep.getPostCount();
        if(lastPostId==null){
            lastPostId=9;
        }
        try{
            System.out.println(requestBody);
            JSONObject jObj=new JSONObject(requestBody);
            newPost.setId(++lastPostId);
            newPost.setTitle(jObj.getString("title")==null?"":jObj.getString("title").trim());
            newPost.setUserid(jObj.getInt("userid"));
            newPost.setAuthor(jObj.getString("author")==null?"":jObj.getString("author").trim());
            newPost.setGenre(jObj.getString("genre")==null?"":jObj.getString("genre").trim());
            newPost.setImage(jObj.getString("image")==null?"":jObj.getString("image").trim());
            newPost.setCoverId(jObj.getString("coverid"));
            newPost.setStatus(jObj.getBoolean("status"));
        }
        catch(Exception ex){return ex.getMessage();}
        try{
            postDetailsRep.save(newPost);
        }catch(Exception ex){
            retObj.put("message","There was some problem uploading Post");
            retObj.put("error",ex.getMessage());
            return retObj.toString();
        };
        retObj.put("message","Your post is Uploaded!");
        return retObj.toString();
    }

    @Override
    public String getGenres() {
        JSONArray jsonArray = new JSONArray();
        for (String str : postDetailsRep.getGenres()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", str);
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public String getPostsByGenre(String body) {
        List<JSONObject> retObj = new ArrayList<>();
        JSONObject jObj=new JSONObject(body);
        int count=0;
        for (Object[] result : postDetailsRep.getPostsByGenre(jObj.getString("genre"))) {
            retObj.add(new JSONObject());
            retObj.get(count).put("id",result[1]==null?"":result[0]);
            retObj.get(count).put("author",result[1]==null?"":result[1]);
            retObj.get(count).put("title",result[1]==null?"":result[2]);
            retObj.get(count).put("image",result[1]==null?"":result[3]);
            retObj.get(count).put("coverid",result[1]==null?"":result[4].toString());
            retObj.get(count).put("genre",result[1]==null?"":result[5]);
            retObj.get(count).put("status",result[1]==null?"":result[6]);
            retObj.get(count).put("userid",result[1]==null?"":result[7]);
            retObj.get(count).put("username",result[1]==null?"":result[8]);
            count++;
        }
        return retObj.toString();
    }

    @Override
    public String getPostsOfUser(String body) {
        List<JSONObject> retObj = new ArrayList<>();
        JSONObject jObj=new JSONObject(body);
        int count=0;
        for (Object[] result : postDetailsRep.getPostsOfUser(Integer.parseInt(jObj.getString("userId")))) {
            retObj.add(new JSONObject());
            retObj.get(count).put("id",result[1]==null?"":result[0]);
            retObj.get(count).put("author",result[1]==null?"":result[1]);
            retObj.get(count).put("title",result[1]==null?"":result[2]);
            retObj.get(count).put("image",result[1]==null?"":result[3]);
            retObj.get(count).put("coverid",result[1]==null?"":result[4].toString());
            retObj.get(count).put("genre",result[1]==null?"":result[5]);
            retObj.get(count).put("status",result[1]==null?"":result[6]);
            retObj.get(count).put("userid",result[1]==null?"":result[7]);
            retObj.get(count).put("username",result[1]==null?"":result[8]);
            count++;
        }
        return retObj.toString();
    }
}
