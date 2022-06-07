package ru.itis.resale.javalabresale.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.resale.javalabresale.dto.AddPostDto;
import ru.itis.resale.javalabresale.dto.PostDto;
import ru.itis.resale.javalabresale.enums.State;
import ru.itis.resale.javalabresale.exceptions.AuthorNotExistsException;
import ru.itis.resale.javalabresale.exceptions.AuthorNotFoundException;
import ru.itis.resale.javalabresale.exceptions.PostNotFoundException;
import ru.itis.resale.javalabresale.models.Author;
import ru.itis.resale.javalabresale.models.Post;
import ru.itis.resale.javalabresale.repositories.AuthorsRepository;
import ru.itis.resale.javalabresale.repositories.PostsRepository;
import ru.itis.resale.javalabresale.services.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import static ru.itis.resale.javalabresale.dto.PostDto.from;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostsRepository postsRepository;

    private final AuthorsRepository authorsRepository;

    @Override
    public PostDto getPost(Long postId) {
        return from(postsRepository.findById(postId).orElseThrow((Supplier<RuntimeException>)()
        -> new PostNotFoundException("post not found")));
    }

    @Override
    public PostDto addPost(AddPostDto post) {
        Author author = authorsRepository
                .findById(post.getAuthorId())
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new AuthorNotExistsException(post.getAuthorId()));

        Post newPost = Post.builder()
                .createdAt(LocalDateTime.now())
                .state(State.PUBLISHED)
                .text(post.getText())
                .title(post.getTitle())
                .author(author)
                .build();

        return from(postsRepository.save(newPost));
    }

    @Override
    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public void deletePost(Long authorId, Long postId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("не найден пост"));
        if(!post.getAuthor().getId().equals(authorId))
            throw new AuthorNotFoundException("Пост не принадлежит автору с id = " + authorId);
        post.setState(State.DELETED);
        postsRepository.save(post);
    }

    @Override
    public Post updatePost(Long authorId, Long postId, PostDto newData) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("не найден пост"));
        if(!post.getAuthor().getId().equals(authorId))
            throw new AuthorNotFoundException("Пост не принадлежит автору с id = " + authorId);
        post.setText(newData.getText());
        post.setTitle(newData.getTitle());

        return postsRepository.save(post);
    }
}
