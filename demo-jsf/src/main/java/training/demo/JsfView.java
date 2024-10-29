package training.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

@Component
@RequestScope
public @interface JsfView {
}
