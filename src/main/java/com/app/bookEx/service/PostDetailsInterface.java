package com.app.bookEx.service;

import java.util.List;

public interface PostDetailsInterface {
    String getNewPosts();

    String uploadPost(String body);

    String getGenres();

    String getPostsByGenre(String body);

    String getPostsOfUser(String body);
}
