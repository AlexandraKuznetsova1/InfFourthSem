package com.itis.services;

import com.itis.dtos.AddPostDto;
import com.itis.dtos.PostDto;
import com.itis.dtos.PostsPage;


public interface PostsService {
    PostDto getPost(Long postId);

    PostDto addPost(AddPostDto post);

    PostsPage getPosts(Long authorId, Integer page);

    void deletePost(Long authorId, Long postId);

    PostDto updatePost(Long authorId, Long postId, PostDto newData);
}
