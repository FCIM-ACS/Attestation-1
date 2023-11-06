import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        try {
                // Пример вызова эндпоинта сложения
                URL addUrl = new URL("http://localhost:8080/add");
                HttpURLConnection addConnection = (HttpURLConnection) addUrl.openConnection();
                addConnection.setRequestMethod("POST");
                addConnection.setDoOutput(true);
    
                String addParams = "first=10&second=5";
                OutputStream addOutputStream = addConnection.getOutputStream();
                addOutputStream.write(addParams.getBytes());
                addOutputStream.flush();
    
                int addResponseCode = addConnection.getResponseCode();
                if (addResponseCode == 200) {
                    BufferedReader addReader = new BufferedReader(new InputStreamReader(addConnection.getInputStream()));
                    String addResponse = addReader.readLine();
                    System.out.println("Success: " + addResponse);
                } else {
                    System.out.println("Fail: Bad Request");
                }
    
                addConnection.disconnect();
                
            // Вызов эндпоинта вычитания
            URL subtractUrl = new URL("http://localhost:8080/subtract");
            HttpURLConnection subtractConnection = (HttpURLConnection) subtractUrl.openConnection();
            subtractConnection.setRequestMethod("POST");
            subtractConnection.setDoOutput(true);

            String subtractParams = "first=10&second=5";
            OutputStream subtractOutputStream = subtractConnection.getOutputStream();
            subtractOutputStream.write(subtractParams.getBytes());
            subtractOutputStream.flush();

            int subtractResponseCode = subtractConnection.getResponseCode();
            if (subtractResponseCode == 200) {
                BufferedReader subtractReader = new BufferedReader(new InputStreamReader(subtractConnection.getInputStream()));
                String subtractResponse = subtractReader.readLine();
                System.out.println("Success: " + subtractResponse);
            } else {
                System.out.println("Fail: Bad Request");
            }

            subtractConnection.disconnect();

            // Вызов эндпоинта умножения
            URL multiplyUrl = new URL("http://localhost:8080/multiply");
            HttpURLConnection multiplyConnection = (HttpURLConnection) multiplyUrl.openConnection();
            multiplyConnection.setRequestMethod("POST");
            multiplyConnection.setDoOutput(true);

            String multiplyParams = "first=10&second=5";
            OutputStream multiplyOutputStream = multiplyConnection.getOutputStream();
            multiplyOutputStream.write(multiplyParams.getBytes());
            multiplyOutputStream.flush();

            int multiplyResponseCode = multiplyConnection.getResponseCode();
            if (multiplyResponseCode == 200) {
                BufferedReader multiplyReader = new BufferedReader(new InputStreamReader(multiplyConnection.getInputStream()));
                String multiplyResponse = multiplyReader.readLine();
                System.out.println("Success: " + multiplyResponse);
            } else {
                System.out.println("Fail: Bad Request");
            }

            multiplyConnection.disconnect();

            // Вызов эндпоинта деления
            URL divideUrl = new URL("http://localhost:8080/divide");
            HttpURLConnection divideConnection = (HttpURLConnection) divideUrl.openConnection();
            divideConnection.setRequestMethod("POST");
            divideConnection.setDoOutput(true);

            String divideParams = "first=10&second=5";
            OutputStream divideOutputStream = divideConnection.getOutputStream();
            divideOutputStream.write(divideParams.getBytes());
            divideOutputStream.flush();

            int divideResponseCode = divideConnection.getResponseCode();
            if (divideResponseCode == 200) {
                BufferedReader divideReader = new BufferedReader(new InputStreamReader(divideConnection.getInputStream()));
                String divideResponse = divideReader.readLine();
                System.out.println("Success: " + divideResponse);
            } else {
                System.out.println("Fail: Bad Request");
            }

            divideConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
