package app.ludrive.core.domain.util;

import app.ludrive.core.domain.Identifiable;

public record Value<T extends Identifiable>(T value) {}
