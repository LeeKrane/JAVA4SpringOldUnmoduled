package labor14_num2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "u_id")
	private Integer id;
	
	@Size(min = 3)
	@Column(name = "u_name", unique = true)
	private String name;
	
	@PastOrPresent
	@Column(name = "u_birthday")
	private LocalDate birthday;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();
	
	public User (String name, LocalDate birthday) {
		this.name = name;
		this.birthday = birthday;
	}
	
	public void addPost (Post post) {
		posts.add(post);
	}
	
	public void removePost (Post post) {
		posts.remove(post);
	}
}
