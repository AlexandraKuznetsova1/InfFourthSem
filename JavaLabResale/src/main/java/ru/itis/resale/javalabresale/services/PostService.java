package ru.itis.resale.javalabresale.services;

import ru.itis.resale.javalabresale.dto.AddPostDto;
import ru.itis.resale.javalabresale.dto.PostDto;
import ru.itis.resale.javalabresale.models.Post;

import java.util.List;

public interface PostService {
    PostDto getPost(Long postId);

    PostDto addPost(AddPostDto post);

    List<Post> getAllPosts();

    void deletePost(Long authorId, Long postId);//проверка, что этот пост принадлежит автору, SOFT DELETE

    Post updatePost(Long authorId, Long postId, PostDto newData);
}
