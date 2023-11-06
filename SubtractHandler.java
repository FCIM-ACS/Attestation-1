import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;

public class SubtractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            HashMap<String, String> params = Server.parseParams(exchange);
            int first = Integer.parseInt(params.get("first"));
            int second = Integer.parseInt(params.get("second"));
            int result = first - second;
            Server.sendResponse(exchange, 200, Integer.toString(result));
        } catch (NumberFormatException e) {
            Server.sendResponse(exchange, 400, "Bad Request: Invalid input");
        }
    }
}
