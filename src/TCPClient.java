import java.io.*;
import java.net.*;
import java.security.KeyPair;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Socket clientSocket = new Socket("localhost", 11169);
            DataOutputStream ssilkaVSibir = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader pobegIsShoushenka = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.print("Enter a message or exit: ");
            sentence = inFromUser.readLine();

            if (sentence.equalsIgnoreCase("exit")) {
                System.exit(0);
                break;
            }
            sentence = CustomSecurity.encryptData(sentence, TCPServer.keyPair.getPublic());
            ssilkaVSibir.writeBytes(sentence + '\n');
            modifiedSentence = pobegIsShoushenka.readLine();
            //modifiedSentence = CustomSecurity.decryptData(modifiedSentence,TCPServer.keyPair.getPrivate());
            System.out.println("From Server: " + modifiedSentence);

            clientSocket.close();
        }
    }
}
