package training.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
public class EmployeesView {

    private List<EmployeeResource> employees = List.of(
            new EmployeeResource(1L, "Test Joe"),
            new EmployeeResource(2L, "Test Jack")
            );

    public List<EmployeeResource> getEmployees() {
        return employees;
    }
}
