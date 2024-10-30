package training.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@JsfView
@RequiredArgsConstructor
@EnableConfigurationProperties(EmployeeProperties.class)
public class EmployeesView {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    private final EmployeeProperties employeeProperties;

    private List<EmployeeResource> employees;

    private EmployeeResource employeeToAdd;

    @PostConstruct
    public void findEmployees() {
        employees = employeeService.findEmployees();
        employeeToAdd = new EmployeeResource(null, employeeProperties.getPlaceholder());
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
