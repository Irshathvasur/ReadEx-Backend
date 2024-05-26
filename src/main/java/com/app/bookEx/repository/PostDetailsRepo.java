package com.app.bookEx.repository;

import com.app.bookEx.domain.pojo.PostDetails;
import com.app.bookEx.domain.pojo.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostDetailsRepo extends JpaRepository<PostDetails, UserDetails> {


    @Query(value = "SELECT p.id AS id, p.author AS author, p.title AS title, p.image AS image," +
            " p.coverid AS coverid, p.genre AS genre,p.status AS status, u.id AS useridno, u.username AS username" +
            " FROM postdetails p JOIN userdetails u ON p.userid = u.id where p.status=true order by p.id desc ", nativeQuery = true)
    List<Object[]> getNewPosts();

    @Query("select max(id) from PostDetails")
    Integer getPostCount();

    @Query("SELECT pd FROM PostDetails pd WHERE pd.id = :postId")
    PostDetails getPostById(@Param("postId") Integer postId);
    @Query("select distinct genre from PostDetails")
    List<String> getGenres();

    @Query(value = "SELECT p.id AS id, p.author AS author, p.title AS title, p.image AS image," +
            "p.coverid AS coverid, p.genre AS genre,p.status AS status, u.id AS useridno," +
            " u.username AS username FROM postdetails p JOIN userdetails u ON p.userid = u.id" +
            " where p.status=true and  p.genre IN (:data) order by p.id desc",nativeQuery = true)
    List<Object[]> getPostsByGenre(String data);

    @Query(value = "SELECT p.id AS id, p.author AS author, p.title AS title, p.image AS image," +
            "p.coverid AS coverid, p.genre AS genre,p.status AS status, u.id AS useridno," +
            "u.username AS username FROM postdetails p JOIN userdetails u ON p.userid = u.id "+
            "where p.status=true and  u.id IN (:data) order by p.id desc",nativeQuery = true)
    List<Object[]> getPostsOfUser(Integer data);

}
