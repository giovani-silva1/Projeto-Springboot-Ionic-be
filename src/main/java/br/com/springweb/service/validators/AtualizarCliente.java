package br.com.springweb.service.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = AtualizarClienteValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtualizarCliente {
 String message() default "Erro de validação";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
}