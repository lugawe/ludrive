package app.ludrive.core.domain.vfs;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

public record FileContent(File file, ReadableByteChannel content) implements Closeable {

    public static final int BUFFER_SIZE = 8192;

    public FileContent(File file, ReadableByteChannel content) {
        this.file = Objects.requireNonNull(file);
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

    public static FileContent from(File file, ReadableByteChannel content) {
        return new FileContent(file, content);
    }

    public static FileContent from(File file, InputStream inputStream) {
        return new FileContent(file, Channels.newChannel(inputStream));
    }
}
