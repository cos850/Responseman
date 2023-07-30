package com.example.back.web.base.converter;

import com.example.back.web.base.Type;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/**
 * https://www.baeldung.com/jpa-persisting-enums-in-jpa
 *
 * 열거값을 JPA 매핑 시 데이터베이스 데이터의 손상을 방지 하기 위함
 * JPA 2.1부터 도입
 * ServerType to String
 */
@Converter(autoApply = true) // autoApply : ServerType과 매핑된 모든 필드에 해당 로직을 자동으로 수행. @Converter 주석을 엔티티 필드에 넣을 수도 있음
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type attribute) {
        if(attribute == null)
            return null;
        return attribute.getType();
    }

    @Override
    public Type convertToEntityAttribute(String dbData) {
        if(dbData == null)
            return null;

        return Stream.of(Type.values())
                .filter(t -> t.getType().equals(dbData))
                .findAny().orElseThrow();
    }
}
