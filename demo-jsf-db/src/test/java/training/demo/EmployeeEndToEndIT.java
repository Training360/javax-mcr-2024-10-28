package training.demo;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(statements = {"delete from employee"})
class EmployeeEndToEndIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);
    }


    @Test
    void findAll() {
//        Employee employee = employeeRepository.save(new Employee(null, "John Doe"));
        EmployeeResource resource = employeeService.addEmployee(new EmployeeResource(null, "John Doe"));
        long id = resource.getId();

        when()
                .get("/api/employees/{id}", Long.toString(id))
                .then()
                .statusCode(200)
                 .body("id", equalTo((int) id),
                        "name", equalTo("John Doe"));

    }
}
