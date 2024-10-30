package training.demo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "employees")
@Data
@Validated
public class EmployeeProperties {

    @NotBlank
    private String placeholder;
}
