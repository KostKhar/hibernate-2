package entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class SpecialFeaturesConverter implements AttributeConverter<List<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(List<SpecialFeature> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        return attribute.stream()
                .map(SpecialFeature::getDbValue)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<SpecialFeature> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return List.of();
        return Arrays.stream(dbData.split(","))
                .map(SpecialFeature::fromDbValue)
                .collect(Collectors.toList());
    }
}
