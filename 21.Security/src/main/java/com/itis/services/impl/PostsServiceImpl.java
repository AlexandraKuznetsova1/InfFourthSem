package com.itis.services.impl;

import com.itis.dtos.AddPostDto;
import com.itis.dtos.DtoMapper;
import com.itis.dtos.PostDto;
import com.itis.dtos.PostsPage;
import com.itis.exceptions.WrongAuthorException;
import com.itis.models.Author;
import com.itis.models.Post;
import com.itis.respositories.AuthorsRepository;
import com.itis.respositories.PostRepository;
import com.itis.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final AuthorsRepository authorsRepository;
    private final PostRepository postRepository;
    private final DtoMapper dtoMapper;

    @Value("${blog.default-page-size}")
    private Integer pageDefaultSize;

    @Override
    public PostDto getPost(Long postId) {
        return dtoMapper.postToPostDto(
                postRepository.findById(postId)
                        .orElseThrow(() -> Post.throwNotFoundExceptionWithId(postId))
        );
    }

    @Override
    public PostDto addPost(AddPostDto newPost) {
        Author author = authorsRepository.findById(newPost.getAuthorId())
                .orElseThrow(() -> Author.throwNotFoundExceptionWithId(newPost.getAuthorId()));
        Post post = Post.builder()
                .createdAt(LocalDateTime.now())
                .state(Post.State.PUBLISHED)
                .text(newPost.getText())
                .title(newPost.getTitle())
                .author(author)
                .build();

        author.getPosts().add(post);

        return dtoMapper.postToPostDto(postRepository.save(post));
    }

    @Override
    public PostsPage getPosts(Long authorId, Integer page) {
        Author author = authorsRepository.findById(authorId)
                .orElseThrow(() -> Author.throwNotFoundExceptionWithId(authorId));
        PageRequest pageRequest = PageRequest.of(page, pageDefaultSize,
                Sort.by("createdAt").descending().and(Sort.by("state")));

        Page<Post> postPage =  postRepository.findAllByAuthor(author, pageRequest);
        return PostsPage.builder()
                .posts(dtoMapper.postListToPostDtoList(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .build();
    }

    @Override
    public void deletePost(Long authorId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> Post.throwNotFoundExceptionWithId(postId));
        if(!post.getAuthor().getId().equals(authorId))
            throw new WrongAuthorException("Пост не принадлежит автору с id = " + authorId);
        post.setState(Post.State.DELETED);
        postRepository.save(post);
    }

    @Override
    public PostDto updatePost(Long authorId, Long postId, PostDto newData) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> Author.throwNotFoundExceptionWithId(authorId));
        if(!post.getAuthor().getId().equals(authorId))
            throw new WrongAuthorException("Пост не принадлежит автору с id = " + authorId);
        return dtoMapper.postToPostDto(
                postRepository.save(dtoMapper.updatePost(post, newData))
        );
    }


}
