package com.zunza.pick.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = SpecificValueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecificValue {
	String message() default "Invalid value";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	String[] acceptedValues(); // 허용되는 값 목록
}
