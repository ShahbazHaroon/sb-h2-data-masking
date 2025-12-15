/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.h2.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ubaidsample.h2.enums.MaskSensitiveDataCategory;
import com.ubaidsample.h2.enums.MaskSensitiveDataStrategy;

import java.io.IOException;

public final class MaskSensitiveDataSerializer
        extends JsonSerializer<String>
        implements ContextualSerializer {

    private final MaskSensitiveData annotation;

    public MaskSensitiveDataSerializer() {
        this.annotation = null;
    }

    private MaskSensitiveDataSerializer(MaskSensitiveData annotation) {
        this.annotation = annotation;
    }

    @Override
    public JsonSerializer<?> createContextual(
            SerializerProvider provider,
            BeanProperty property) {

        if (property != null) {
            MaskSensitiveData ann =
                    property.getAnnotation(MaskSensitiveData.class);

            if (ann != null) {
                return new MaskSensitiveDataSerializer(ann);
            }
        }
        return this;
    }

    @Override
    public void serialize(
            String value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

        if (value == null || annotation == null) {
            gen.writeString(value);
            return;
        }

        MaskSensitiveDataCategory category = annotation.category();
        MaskSensitiveDataStrategy strategy = annotation.strategy();

        // Category-based override (security-first)
        if (category == MaskSensitiveDataCategory.SECRET ||
                category == MaskSensitiveDataCategory.AUTH) {
            gen.writeString("******");
            return;
        }

        gen.writeString(mask(value, strategy));
    }

    private String mask(String value, MaskSensitiveDataStrategy strategy) {

        int length = value.length();

        return switch (strategy) {
            case FULL -> "******";

            case LAST_FOUR ->
                    length > 4
                            ? "****" + value.substring(length - 4)
                            : "****";

            case PARTIAL ->
                    length > 4
                            ? value.substring(0, 2)
                            + "****"
                            + value.substring(length - 2)
                            : "****";
        };
    }
}