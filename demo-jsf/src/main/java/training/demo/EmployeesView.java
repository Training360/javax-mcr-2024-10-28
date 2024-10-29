package training.demo;

import jakarta.annotation.PostConstruct;

import java.util.List;

@JsfView
public class EmployeesView {

    private EmployeeService employeeService;

    private List<EmployeeResource> employees;

    public EmployeesView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void findEmployees() {
        employees = employeeService.findEmployees();
    }

    public List<EmployeeResource> getEmployees() {
        return employees;
    }
}
