package app.ludrive.core.domain.vfs;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

public record Content(ReadableByteChannel content) implements Closeable {

    public static final int BUFFER_SIZE = 8192;

    public Content(ReadableByteChannel content) {
        this.content = Objects.requireNonNull(content);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        while (content.read(buffer) != -1) {
            buffer.flip();
            outputStream.write(buffer.array(), 0, buffer.limit());
            buffer.clear();
        }
    }

    @Override
    public void close() throws IOException {
        content.close();
    }

    public static Content from(ReadableByteChannel content) {
        return new Content(content);
    }

    public static Content from(InputStream inputStream) {
        return new Content(Channels.newChannel(inputStream));
    }
}
