package ru.practicum.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PlusTwoHours.DateEventValidator.class)
@Documented
public @interface PlusTwoHours {

    String message() default "{value.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class DateEventValidator implements ConstraintValidator<PlusTwoHours, Timestamp> {
        private final Timestamp startTime = Timestamp.valueOf(LocalDateTime.now().plusHours(2));

        @Override
        public boolean isValid(Timestamp timestamp, ConstraintValidatorContext context) {
            if (timestamp == null) return true;
            return timestamp.after(startTime);
        }
    }
}
