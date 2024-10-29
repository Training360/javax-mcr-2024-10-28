package training.demo;

//import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationIT {

	@Autowired
	EmployeeController employeeController;

	@Test
	void contextLoads() {
	}

	@Test
//	@RepeatedTest(10)
//	@DirtiesContext
	void addEmployee() {
		employeeController.addEmployee(new EmployeeResource(null, "Jane Doe"));
		System.out.println(employeeController.findEmployees().size());
		assertThat(employeeController.findEmployees())
				.extracting(EmployeeResource::getName)
				.contains("Jane Doe");
	}

}
