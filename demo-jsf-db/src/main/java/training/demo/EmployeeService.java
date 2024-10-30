package training.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResource> findEmployees() {
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
