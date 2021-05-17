package labor14_num2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "p_id")
	private Integer id;
	
	@NotEmpty
	@Column(name = "p_message", nullable = false)
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "p_user")
	private User user;
	
	public Post (String message) {
		this.message = message;
	}
	
	
}
