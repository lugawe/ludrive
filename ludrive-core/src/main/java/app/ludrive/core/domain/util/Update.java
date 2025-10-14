package app.ludrive.core.domain.util;

import java.io.Serializable;

import app.ludrive.core.domain.Identifiable;

public record Update<T extends Identifiable>(T oldValue, T newValue) implements Serializable {}
