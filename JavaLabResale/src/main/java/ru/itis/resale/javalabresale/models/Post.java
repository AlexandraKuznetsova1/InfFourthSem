package ru.itis.resale.javalabresale.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.resale.javalabresale.enums.State;
import ru.itis.resale.javalabresale.exceptions.AuthorNotFoundException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

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
}
