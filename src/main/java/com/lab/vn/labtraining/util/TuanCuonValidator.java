package com.lab.vn.labtraining.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TuanCuonValidator implements ConstraintValidator<TuanCuonAnnotation, String> {

	private static final String TUANCUON_PREFIX = "tuancuon:";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return false;
		} else {
			return value.startsWith(TUANCUON_PREFIX);
		}
	}

}
