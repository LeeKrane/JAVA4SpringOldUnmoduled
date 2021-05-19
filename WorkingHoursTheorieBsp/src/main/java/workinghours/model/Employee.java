package workinghours.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer empId;
	
	@Basic
	@Column(name = "emp_lastname")
	private String empLastName;
	
	@Basic
	@Column(name = "emp_firstname")
	private String empFirstName;
	
	@OneToMany(mappedBy = "whEmp")
	@JsonIgnore
	private List<WorkingHours> workingHoursList;
}
