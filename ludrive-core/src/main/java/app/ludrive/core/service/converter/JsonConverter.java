package app.ludrive.core.service.converter;

import app.ludrive.core.exception.ConvertException;

public interface JsonConverter extends Converter {

    <T> String toJson(T value) throws ConvertException;

    <T> T fromJson(String json, Class<T> tClass) throws ConvertException;
}
