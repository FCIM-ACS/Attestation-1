import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.security.KeyPair;

public class TCPServer {
    public static KeyPair keyPair;

    static {
        try {
            keyPair = CustomSecurity.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(11169);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getByName("localhost"));
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            SocketAddress clientAddress = connectionSocket.getRemoteSocketAddress();
            System.out.println("(Подключился клиент с адрессом): " + clientAddress);
            clientSentence = inFromClient.readLine();
            clientSentence = CustomSecurity.decryptData(clientSentence, keyPair.getPrivate());
            clientSentence = CustomSecurity.wordSecurity(clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            //capitalizedSentence = CustomSecurity.encryptData(capitalizedSentence,keyPair.getPublic());
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}