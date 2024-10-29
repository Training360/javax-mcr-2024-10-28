package training.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private Map<Long, EmployeeResource> employees = new ConcurrentHashMap<>(
            Map.of(1L, new EmployeeResource(1L, "John Doe"),
                    2L, new EmployeeResource(2L, "Jack Doe")
                    )
    );

    private AtomicLong counter = new AtomicLong(2);

    public List<EmployeeResource> findEmployees() {
        return new ArrayList<>(employees.values());
    }

    public EmployeeResource findEmployeeById(long id) {
        return employees.get(id);
    }

    public EmployeeResource addEmployee(EmployeeResource employee) {
        long id = counter.incrementAndGet();
        EmployeeResource employeeResource = new EmployeeResource(id, employee.getName());
        employees.put(id, employeeResource);
        return employeeResource;
    }

    public EmployeeResource updateEmployee(EmployeeResource employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    public void deleteEmployee(long id) {
        employees.remove(id);
    }
}
