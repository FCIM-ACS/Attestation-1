public class Main {
    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        TCPClient client = new TCPClient();
        HTTPHandlerEx httpServer = new HTTPHandlerEx();
        int lol = 1;
        if(lol == 0) {
            Thread serverThread = new Thread(() -> {
                try {
                    server.main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();

            try {
                client.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Thread serverThread = new Thread(() -> {
                try {
                    httpServer.main(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
        }
    }
}
