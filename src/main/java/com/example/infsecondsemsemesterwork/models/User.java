package com.example.infsecondsemsemesterwork.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

	public enum Role {
		USER
	};

	public enum State {
		NOT_CONFIRMED, CONFIRMED, DELETED
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nick;
	private String email;
	private String password;
	private String avatarUrl;
	private String confirmCode;

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Set<Secret> secrets;

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Set<Tip> tips;

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Set<TipComment> tipComments;

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Set<SecretComment> secretComments;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private State state;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		User user = (User) o;
		return id != null && Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
