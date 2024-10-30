package training.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void findEmployees() {
        // BDD - given,when,then
        when(employeeRepository.findAll()).thenReturn(List.of(
                new Employee(1L, "John Doe"),
                new Employee(2L, "Jack Doe")));
        // Given

        // When
        List<EmployeeResource> employees = employeeService.findEmployees();
        // Then
        assertEquals(List.of(new EmployeeResource(1L, "John Doe"),
                new EmployeeResource(2L, "Jack Doe")),
                employees);
    }

    @Test
    void addEmployee() {
        when(employeeRepository.save(any())).thenReturn(new Employee(1L, "Jane Doe"));

        EmployeeResource result = employeeService.addEmployee(new EmployeeResource(null, "Jane Doe"));

        verify(employeeRepository).save(argThat(employee -> employee.getName().equals("Jane Doe")));
        assertEquals(new EmployeeResource(1L, "Jane Doe"), result);
    }

    @Test
    void addEmployeeWithExistingName() {
        when(employeeRepository.findEmployeeByName(any()))
                .thenReturn(Optional.of(new Employee(10L, "Jane Doe")));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                employeeService.addEmployee(new EmployeeResource(null, "Jane Doe")));

        assertEquals("Employee already exists with name: Jane Doe, with id: 10", e.getMessage());
    }

}