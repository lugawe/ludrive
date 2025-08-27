package app.ludrive.server;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class LuDriveServer {

    public LuDriveServer() {}

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
