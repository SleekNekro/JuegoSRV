import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String response;
        while ((response = in.readLine()) != null) {
            System.out.println(response);
        }

        in.close();
        socket.close();
    }
}
