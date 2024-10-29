package training.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationIT {

	@Autowired
	EmployeeController employeeController;

	@Test
	void contextLoads() {
	}

	@Test
	void addEmployee() {
		employeeController.addEmployee(new EmployeeResource(null, "Jane Doe"));
		assertThat(employeeController.findEmployees())
				.extracting(EmployeeResource::name)
				.contains("Jane Doe");
	}

}
