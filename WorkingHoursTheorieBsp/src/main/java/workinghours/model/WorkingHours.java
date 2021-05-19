package workinghours.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class WorkingHours implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wh_id")
	private Integer whId;
	
	@Basic
	@Column(name = "wh_hours")
	private Integer whHours;
	
	@Basic
	@Column(name = "wh_date")
	private LocalDate whDate;
	
	@ManyToOne
	@JoinColumn(name = "wh_emp_id")
	private Employee whEmp;
}
