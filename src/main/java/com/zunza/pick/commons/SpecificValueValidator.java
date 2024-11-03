package com.zunza.pick.commons;

import java.util.Arrays;

import com.zunza.pick.annotations.SpecificValue;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpecificValueValidator implements ConstraintValidator<SpecificValue, String> {

	private String[] acceptedValues;

	@Override
	public void initialize(SpecificValue constraintAnnotation) {
		acceptedValues = constraintAnnotation.acceptedValues();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && Arrays.asList(acceptedValues).contains(value);
	}
}
