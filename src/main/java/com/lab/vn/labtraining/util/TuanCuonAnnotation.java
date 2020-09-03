package com.lab.vn.labtraining.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TuanCuonValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TuanCuonAnnotation {

	// trường message là bắt buộc, khai báo nội dung sẽ trả về khi field k hợp lệ
	String message() default "VALIDATE @Valid : ---name must start with tuancuon---";

	// Cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
	Class<?>[] groups() default {};

	// Cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
	Class<? extends Payload>[] payload() default {};
}
