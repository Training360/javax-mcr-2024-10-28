package training.demo;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.util.List;

@JsfView
public class EmployeesView {

    private EmployeeService employeeService;

    private MessageContext messageContext;

    private List<EmployeeResource> employees;

    private EmployeeResource employeeToAdd = new EmployeeResource(null, "Input name");

    public EmployeesView(EmployeeService employeeService, MessageContext messageContext) {
        this.employeeService = employeeService;
        this.messageContext = messageContext;
    }

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
