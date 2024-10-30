package training.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeesViewTest {

    @Mock
    EmployeeService employeeService;

    @Mock
    MessageContext messageContext;

    @InjectMocks
    EmployeesView employeesView;

    @Test
    void createEmployee() {
        EmployeeResource employeeResource = new EmployeeResource(null, "John Doe");
        employeesView.setEmployeeToAdd(employeeResource);
        String result = employeesView.createEmployee();

        verify(employeeService).addEmployee(employeeResource);
        verify(messageContext).sendMessage("Employee has been created");
        assertEquals("index.xhtml?faces-redirect=true", result);
    }
}