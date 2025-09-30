package app.ludrive.core.domain.vfs;

import java.nio.channels.Channel;

public record FileContent(File file, Channel content) {}
