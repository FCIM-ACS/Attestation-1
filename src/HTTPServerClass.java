import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HTTPServerClass {
    public static void main() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(443), 0);

        httpServer.createContext("/halo", new HTTPHandlerCustom());
        httpServer.setExecutor(null);
        httpServer.start();

    }
}
