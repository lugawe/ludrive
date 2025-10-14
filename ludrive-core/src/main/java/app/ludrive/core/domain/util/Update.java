package app.ludrive.core.domain.util;

public record Update<T>(T oldValue, T newValue) {}
