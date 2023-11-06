import com.sun.net.httpserver.HttpServer;


import java.net.InetSocketAddress;

public class HTTPHandlerEx {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(11110), 0);

        server.createContext("/", new HTTPHandlerCustom());
        server.createContext("/add", new HTTPHandlerCustom());
        server.createContext("/subtract", new HTTPHandlerCustom());
        server.createContext("/multiply", new HTTPHandlerCustom());
        server.createContext("/divide", new HTTPHandlerCustom());

        server.setExecutor(null); // Use default executor
        server.start();
    }
}
