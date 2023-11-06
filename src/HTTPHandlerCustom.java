import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HTTPHandlerCustom implements HttpHandler {
    public static String path;
    public static double num1 = 0;
    public static double num2 = 0;
    public static double responseNumber = 0;
    public static double iteration = 0;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            Map<String, String> queryParams = parseQuery(exchange.getRequestURI().getQuery());
            path = exchange.getRequestURI().getPath();
            String response;
            if(path.equals("")){
                response = mainPage(queryParams);
            }
            else response = generateResponse(queryParams);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
            writer.write(response);
            writer.close();
        } else {
            exchange.sendResponseHeaders(405, 0); // Метод не поддерживается
        }
    }

    private Map<String, String> parseQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> parameters = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                parameters.put(key, value);
            }
        }
        return parameters;
    }

    private String generateResponse(Map<String, String> queryParams) {
        StringBuilder response = new StringBuilder("<html><head><style>");
        response.append("body { background-color: #f0f0f0; font-family: Arial, sans-serif; margin: 20px; }");
        response.append("h1 { color: #333; }");
        response.append("ul { list-style-type: square; }");
        response.append("</style></head><body><h1>HTTP Server Response</h1>");
        response.append("<p>Received the following query parameters:</p>");
        response.append("<ul>");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            response.append("<li>").append(entry.getKey()).append(": ").append(entry.getValue()).append("</li>");
            try {
                if(iteration == 0) {
                    num1 = Double.parseDouble(entry.getValue());
                    iteration++;
                }
                else {
                    num2 = Double.parseDouble(entry.getValue());
                    iteration++;
                }
                if(iteration > 1) {
                    if (path.contains("add")) {
                        responseNumber = (num1 + num2);
                    } else if (path.contains("subtract")) {
                        responseNumber = (num1 - num2);
                    } else if (path.contains("multiply")) {
                        responseNumber = (num1 * num2);
                    } else if (path.contains("device")) {
                        if (num2 != 0) {
                            responseNumber = (num1 / num2);
                        }
                    }
                    response.append("<li>").append("Response Number").append(": ").append(responseNumber).append("</li>");
                }
            } catch (NumberFormatException | NullPointerException e) {
            }
        }
        response.append("</ul>");
        response.append("</body></html>");
        return response.toString();
    }

    private String mainPage(Map<String, String> queryParams) {
        StringBuilder response = new StringBuilder("<html><head><style>");
        response.append("body { background-color: #f0f0f0; font-family: Arial, sans-serif; margin: 20px; }");
        response.append("h1 { color: #333; }");
        response.append("ul { list-style-type: square; }");
        response.append("</style></head><body><h1>Select method</h1>");
        response.append("<html>");
        response.append("<head>");
        response.append("<title>Buttons</title>");
        response.append("</head>");
        response.append("<button >Subtract</button>");
        response.append("</body></html>");
        return response.toString();
    }

}
