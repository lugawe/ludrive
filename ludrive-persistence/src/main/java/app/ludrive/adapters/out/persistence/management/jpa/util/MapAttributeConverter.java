package app.ludrive.adapters.out.persistence.management.jpa.util;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import app.ludrive.core.service.converter.JsonConverter;

// TODO check warning
@ApplicationScoped
@Converter(autoApply = true)
public class MapAttributeConverter implements AttributeConverter<Map<String, String>, String> {

    @Inject
    private JsonConverter jsonConverter;

    public MapAttributeConverter() {}

    @Override
    public String convertToDatabaseColumn(Map<String, String> map) {

        if (map == null || map.isEmpty()) {
            return null;
        }

        return jsonConverter.toJson(map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> convertToEntityAttribute(String json) {

        if (json == null || json.isEmpty()) {
            return null;
        }

        return (Map<String, String>) jsonConverter.fromJson(json, Map.class);
    }
}
