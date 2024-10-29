package training.demo;

import jakarta.annotation.PostConstruct;

import java.util.List;

@JsfView
public class EmployeesView {

    private EmployeeService employeeService;

    private List<EmployeeResource> employees;

    private EmployeeResource employeeToAdd = new EmployeeResource(null, "Input name");

    public EmployeesView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void findEmployees() {
        employees = employeeService.findEmployees();
    }

    public String createEmployee() {
        employeeService.addEmployee(employeeToAdd);
        return "index.xhtml?faces-redirect=true";
    }

    public List<EmployeeResource> getEmployees() {
        return employees;
    }

    public EmployeeResource getEmployeeToAdd() {
        return employeeToAdd;
    }

    public void setEmployeeToAdd(EmployeeResource employeeToAdd) {
        this.employeeToAdd = employeeToAdd;
    }
}
