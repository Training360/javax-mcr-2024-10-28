package training.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeesSeleniumEndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    DataSource dataSource;

    @BeforeEach
    void employEmployeeTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete from employee");
    }

    @Test
    void addEmployee() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:" + port + "/index.xhtml");
        WebElement input = driver.findElement(By.id("create-form:name-input"));
        input.clear();
        input.sendKeys("John Doe " + UUID.randomUUID());
        driver.findElement(By.id("create-form:create-button")).click();

        String value = driver.findElement(By.cssSelector("#messages-ul > li")).getText();

        assertEquals("Employee has been created", value);

        driver.quit();
    }
}
