package training.demo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    // Ezt is generálja a Lombok: private static Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResource> findEmployees() {
        log.debug("Find all employees");
        log.info("Find all");
        return employeeRepository.findAll()
                .stream().map(entity -> new EmployeeResource(entity.getId(), entity.getName()))
                .toList();
    }

    public EmployeeResource findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: %d".formatted(id)));
        return new EmployeeResource(employee.getId(), employee.getName());
    }

    public EmployeeResource addEmployee(EmployeeResource employee) {
        Optional<Employee> anotherWithSameName = employeeRepository.findEmployeeByName(employee.getName());
        if (anotherWithSameName.isPresent()) {
            throw new IllegalArgumentException("Employee already exists with name: %s, with id: %d"
                    .formatted(employee.getName(), anotherWithSameName.get().getId()));
        }

        Employee original = new Employee(employee.getId(), employee.getName());
        Employee entity = employeeRepository.save(original);

        return new EmployeeResource(entity.getId(), entity.getName());
    }

    @Transactional
    public EmployeeResource updateEmployee(EmployeeResource employee) {
        Employee original = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: %d".formatted(employee.getId())));
        original.setName(employee.getName());
        return employee;
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
