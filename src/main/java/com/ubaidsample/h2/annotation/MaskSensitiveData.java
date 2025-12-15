/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.h2.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ubaidsample.h2.enums.MaskSensitiveDataCategory;
import com.ubaidsample.h2.enums.MaskSensitiveDataStrategy;

import java.lang.annotation.*;

@JacksonAnnotationsInside
@JsonSerialize(using = MaskSensitiveDataSerializer.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaskSensitiveData {

    MaskSensitiveDataCategory category() default MaskSensitiveDataCategory.PII;

    MaskSensitiveDataStrategy strategy() default MaskSensitiveDataStrategy.FULL;
}