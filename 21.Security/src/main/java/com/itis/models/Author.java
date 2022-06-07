package com.itis.models;

import com.itis.exceptions.AuthorNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    public static AuthorNotFoundException throwNotFoundExceptionWithId(Long authorId){
        return new AuthorNotFoundException("Could not find author with id = " + authorId);
    }
}
