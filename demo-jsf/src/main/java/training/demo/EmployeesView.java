package training.demo;

import java.util.List;

@JsfView
public class EmployeesView {

    private List<EmployeeResource> employees = List.of(
            new EmployeeResource(1L, "Test Joe"),
            new EmployeeResource(2L, "Test Jack")
            );

    public List<EmployeeResource> getEmployees() {
        return employees;
    }
}