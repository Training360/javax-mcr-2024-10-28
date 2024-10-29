package training.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void findEmployees() {
        // BDD - given,when,then

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
        employeeService.addEmployee(new EmployeeResource(null, "Jane Doe"));

        assertThat(employeeService.findEmployees())
                .extracting(EmployeeResource::name)
                .contains("Jane Doe");
    }

}