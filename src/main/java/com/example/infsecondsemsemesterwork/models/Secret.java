package com.example.infsecondsemsemesterwork.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "secrets")
public class Secret {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	private String text;
	private String photoUrl;
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "secret")
	private Set<SecretComment> secretComment;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Secret secret = (Secret) o;
		return id != null && Objects.equals(id, secret.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
