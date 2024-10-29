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

    @Test
    void createEmployee() {

        EmployeesView employeesView = new EmployeesView(employeeService){
            @Override
            protected void sendMessage(String message) {
                // Mocked
            }
        } ;

        EmployeeResource employeeResource = new EmployeeResource(null, "John Doe");
        employeesView.setEmployeeToAdd(employeeResource);
        employeesView.createEmployee();

        verify(employeeService).addEmployee(employeeResource);
    }
}