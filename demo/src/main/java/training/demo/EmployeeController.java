package training.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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
}
