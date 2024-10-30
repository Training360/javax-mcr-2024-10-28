package training.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;

@JsfView
@RequiredArgsConstructor
public class EmployeesView {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    private List<EmployeeResource> employees;

    private EmployeeResource employeeToAdd = new EmployeeResource(null, "Input name");

    @PostConstruct
    public void findEmployees() {
        employees = employeeService.findEmployees();
    }

    public String createEmployee() {
        employeeService.addEmployee(employeeToAdd);
        messageContext.sendMessage("Employee has been created");
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
