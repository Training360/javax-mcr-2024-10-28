package training.demo;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    EmployeeResource toDto(Employee employee);

    List<EmployeeResource> toDto(List<Employee> employees);
}
