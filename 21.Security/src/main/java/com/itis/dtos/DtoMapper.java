package com.itis.dtos;

import com.itis.models.Author;
import com.itis.models.Post;
import com.itis.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    AuthorDto authorToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDto authorDto);

    List<AuthorDto> authorListToAuthorDtoList(List<Author> authors);

    @Mapping(target = "id", ignore = true)
    Author updateAuthor(@MappingTarget Author author, AuthorDto authorDto);

    @Mapping(target = "id", ignore = true)
    Post updatePost(@MappingTarget Post post, PostDto postDto);

    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto post);

    List<PostDto> postListToPostDtoList(List<Post> posts);


    @Mapping(target = "role", expression = "java(User.Role.USER)")
    User signUpDtoToUser(SignUpDto signUpDto);

    UserDto userToUserDto(User user);

    @Mapping(source = "id", target = "id", ignore = true)
    User updateUser(@MappingTarget User user, UserDto userDto);
}
