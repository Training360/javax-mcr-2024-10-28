package training.demo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmployeeResource {
    private Long id;

    @NotBlank(message = "Name can not be blank")
    private String name;

}
