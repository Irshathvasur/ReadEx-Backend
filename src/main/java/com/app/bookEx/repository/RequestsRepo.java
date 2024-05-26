package com.app.bookEx.repository;

import com.app.bookEx.domain.pojo.PostDetails;
import com.app.bookEx.domain.pojo.Requests;
import com.app.bookEx.domain.pojo.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestsRepo extends JpaRepository<Requests, PostDetails> {
    @Query("select max(id) from Requests")
    Integer getRequestCount();

    @Query(value="SELECT * FROM requests r  WHERE r.receiverid = :id and EXISTS (SELECT p FROM postdetails p WHERE r.postid  = p.id AND p.status = true);",nativeQuery = true)
    List<Requests> getActiveRequests(Integer id);

}
