import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Добавляем обработчики для различных эндпоинтов
        server.createContext("/add", new AddHandler());
        server.createContext("/subtract", new SubtractHandler());
        server.createContext("/multiply", new MultiplyHandler());
        server.createContext("/divide", new DivideHandler());

        server.setExecutor(null); // Используем дефолтный executor
        server.start();
    }

    // Обработчик для операции сложения
    static class AddHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                HashMap<String, String> params = parseParams(exchange);
                int first = Integer.parseInt(params.get("first"));
                int second = Integer.parseInt(params.get("second"));
                int result = first + second;
                sendResponse(exchange, 200, Integer.toString(result));
            } catch (NumberFormatException e) {
                sendResponse(exchange, 400, "Bad Request: Invalid input");
            }
        }
    }

    // Добавьте аналогичные обработчики для вычитания, умножения и деления.

    static HashMap<String, String> parseParams(HttpExchange exchange) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }

    static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
