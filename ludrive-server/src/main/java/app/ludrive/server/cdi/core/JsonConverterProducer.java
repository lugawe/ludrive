package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.core.exception.ConvertException;
import app.ludrive.core.service.converter.JsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class JsonConverterProducer {

    private static class JacksonJsonConverter implements JsonConverter {

        private final ObjectMapper objectMapper;

        public JacksonJsonConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public <T> String toJson(T value) throws ConvertException {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (Exception e) {
                throw new ConvertException(e);
            }
        }

        @Override
        public <T> T fromJson(String json, Class<T> tClass) throws ConvertException {
            try {
                return objectMapper.readValue(json, tClass);
            } catch (Exception e) {
                throw new ConvertException(e);
            }
        }
    }

    public JsonConverterProducer() {}

    @Produces
    public JsonConverter produce(ObjectMapper objectMapper) {
        return new JacksonJsonConverter(objectMapper);
    }
}
