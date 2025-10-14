package app.ludrive.core.domain.util;

import java.io.Serializable;

import app.ludrive.core.domain.Identifiable;

public record Value<T extends Identifiable>(T value) implements Serializable {}
