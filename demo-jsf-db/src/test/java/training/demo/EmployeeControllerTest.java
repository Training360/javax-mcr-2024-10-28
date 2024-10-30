package training.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @Test
    void updateEmployee() {
//        EmployeeService employeeService = mock(EmployeeService.class);
//        EmployeeController employeeController = new EmployeeController(employeeService);

        employeeController.updateEmployee(1L, new EmployeeResource(1L, "John Doe"));
        verify(employeeService).updateEmployee(new EmployeeResource(1L, "John Doe"));
    }

    @Test
    void updateEmployeeIdMismatch() {
        assertThrows(IllegalArgumentException.class, () ->
                employeeController.updateEmployee(1L, new EmployeeResource(2L, "John Doe")));
    }
}