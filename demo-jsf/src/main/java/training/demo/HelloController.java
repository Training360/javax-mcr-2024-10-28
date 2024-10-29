package training.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
 public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello Spring Boot " + LocalDateTime.now();
    }
}