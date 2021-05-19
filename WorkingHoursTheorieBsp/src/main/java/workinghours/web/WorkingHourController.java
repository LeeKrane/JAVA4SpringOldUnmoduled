package workinghours.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workinghours.db.EmployeeRepository;
import workinghours.db.WorkingHoursRepository;
import workinghours.exceptions.EmployeeNotFoundException;
import workinghours.exceptions.WorkingHoursHasIdException;
import workinghours.exceptions.WorkingHoursNotFoundException;
import workinghours.model.Employee;
import workinghours.model.WorkingHours;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class WorkingHourController {
	private final WorkingHoursRepository whRepository;
	private final EmployeeRepository employeeRepository;
	
	public WorkingHourController (WorkingHoursRepository whRepository, EmployeeRepository employeeRepository) {
		this.whRepository = whRepository;
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping(path = "/workinghours/{id}")
	public WorkingHours findWorkingHoursById (@PathVariable(value = "id") Integer id) {
		Optional<WorkingHours> wh = whRepository.findById(id);
		if (wh.isPresent())
			return wh.get();
		else
			throw new WorkingHoursNotFoundException("WH not found: " + id);
	}
	
	@GetMapping(path = "/workinghours")
	public List<WorkingHours> findAllWorkingHours () {
		return whRepository.findAll();
	}
	
	@PostMapping(path = "/workinghours/{empid}")
	public ResponseEntity<WorkingHours> createWH (@PathVariable Integer empid, @Valid @RequestBody WorkingHours wh) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isEmpty())
			throw new EmployeeNotFoundException("Employee not found: " + empid);
		if (wh.getWhId() != null)
			throw new WorkingHoursHasIdException("WH has id: " + wh.getWhId());
		
		wh.setWhEmp(emp.get());
		
		WorkingHours savedWh = whRepository.save(wh);
		String path = "/workinghours/{id}";
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(savedWh.getWhId());
		
		return ResponseEntity.created(uri).body(savedWh);
	}
	
	@DeleteMapping(path = "/workinghours/{id}")
	public void deleteWH (@PathVariable Integer id) {
		Optional<WorkingHours> wh = whRepository.findById(id);
		if (wh.isPresent())
			whRepository.delete(wh.get());
		else
			throw new WorkingHoursNotFoundException("WH not found: " + id);
	}
}
