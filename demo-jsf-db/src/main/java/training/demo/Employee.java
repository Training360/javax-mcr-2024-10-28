package training.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Data: equals hashCode ne legyen entity-nél!
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

}
