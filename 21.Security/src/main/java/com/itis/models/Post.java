package com.itis.models;

import com.itis.exceptions.AuthorNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude = {"author", "inFavorites"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

    public enum State {
        DRAFT, DELETED, PUBLISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private LocalDateTime createdAt;

    @Column(length = 20)
    private String title;

    @Column(length = 1000)
    private String text;

    @Enumerated(value = EnumType.STRING)
    private State state;


    public static AuthorNotFoundException throwNotFoundExceptionWithId(Long postId){
        return new AuthorNotFoundException("Could not find post with id = " + postId);
    }

}
