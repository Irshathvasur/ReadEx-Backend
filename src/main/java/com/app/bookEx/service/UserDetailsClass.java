package com.app.bookEx.service;

import com.app.bookEx.domain.pojo.UserDetails;
import com.app.bookEx.repository.UserDetailsRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsClass implements UserDetailsInterface{

    @Autowired
    UserDetailsRepo UserDetailsRep;
    @Override
    public List<UserDetails> getAllUsers() {
        return UserDetailsRep.getAllUsers();
    }

    @Override
    public String saveUser(String requestBody) {

        JSONObject retObj=new JSONObject();
        UserDetails newUser=new UserDetails();
        Integer lastUserId=UserDetailsRep.getUserCount();
        if(lastUserId==null){
            lastUserId=0;
        }
        try{
            JSONObject jObj=new JSONObject(requestBody);
            boolean userExist=(UserDetailsRep.checkUserExist(jObj.getString("username").trim())>0);
            if(userExist){
                retObj.put("message","Username already exists, Try another name");
                return retObj.toString();
            };
            newUser.setId(++lastUserId);
            newUser.setFirstname(jObj.getString("firstname")==null?"":jObj.getString("firstname").trim());
            newUser.setLastname(jObj.getString("lastname")==null?"":jObj.getString("lastname").trim());
            newUser.setAge(jObj.getInt("age"));
            newUser.setEmail(jObj.getString("email")==null?"":jObj.getString("email").trim());
            newUser.setUsername(jObj.getString("username")==null?"":jObj.getString("username").trim());
            newUser.setUserpassword(jObj.getString("password")==null?"":jObj.getString("password").trim());
        }
        catch (JSONException e){return "Please review your Input";}
        catch(Exception ex){return ex.getMessage();}
        try{
        UserDetailsRep.save(newUser);
        }catch(Exception ex){
            retObj.put("message","There was some problem saving User");
            retObj.put("error",ex.getMessage());
            return retObj.toString();
        };
        retObj.put("message","You are signed Up!");
        return retObj.toString();
    }

    @Override
    public String getUser(String body) {
        JSONObject dataObj=new JSONObject(body);
        JSONObject retObj=new JSONObject();
        UserDetails tableObj=UserDetailsRep.validateUser(dataObj.getString("username"), dataObj.getString("password"));
        if(tableObj != null){
            retObj.put("id",tableObj.getId()==null?"":tableObj.getId().toString());
            retObj.put("username",tableObj.getUsername()==null?"":tableObj.getUsername().trim());
            retObj.put("firstname",tableObj.getFirstname()==null?"":tableObj.getFirstname().trim());
            retObj.put("lastname",tableObj.getLastname()==null?"":tableObj.getLastname().trim());
            retObj.put("age",tableObj.getAge()==null?"":tableObj.getAge().toString());
            retObj.put("email",tableObj.getEmail()==null?"":tableObj.getEmail().trim());
            return retObj.toString();
        }else{
            retObj.put("message","invalid Credentials");
            return retObj.toString();
        }
    }
}
