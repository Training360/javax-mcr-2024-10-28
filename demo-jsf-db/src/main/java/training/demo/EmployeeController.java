package training.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //@GetMapping("/api/employees")
    @GetMapping
    public List<EmployeeResource> findEmployees() {
        return employeeService.findEmployees();
    }

//    @GetMapping("/api/employees/{id}")
    @GetMapping("/{id}")
    public EmployeeResource findEmployeeById(@PathVariable long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping
    public EmployeeResource addEmployee(@RequestBody EmployeeResource employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResource updateEmployee(@PathVariable long id, @RequestBody EmployeeResource employee) {
        if (id != employee.getId()) {
            throw new IllegalArgumentException("Ids don't match %d != %d".formatted(id, employee.getId()));
        }
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }
}
