import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado, esperando clientes...");

        Socket clientSocket1 = serverSocket.accept();
        System.out.println("Cliente 1 conectado.");

        Socket clientSocket2 = serverSocket.accept();
        System.out.println("Cliente 2 conectado.");

        PrintWriter out1 = new PrintWriter(clientSocket1.getOutputStream(), true);
        BufferedReader in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));

        PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

        Random random = new Random();
        int[] dice = new int[5];
        int score1 = 0;
        int score2 = 0;

        for (int round = 1; round <= 10; round++) {

            for (int i = 0; i < 5; i++) {
                dice[i] = random.nextInt(6) + 1;
            }

            int[] counts1 = new int[6];
            for (int die : dice) {
                counts1[die - 1]++;
            }

            int points1 = 0;
            for (int count : counts1) {
                if (count >= 3) points1 = count >= 5 ? 12 : count == 4 ? 6 : 3;
            }

            score1 += points1;
            out1.println("Ronda " + round + ": " + java.util.Arrays.toString(dice) + " - Puntos: " + points1);

            for (int i = 0; i < 5; i++) {
                dice[i] = random.nextInt(6) + 1;
            }

            int[] counts2 = new int[6];
            for (int die : dice) {
                counts2[die - 1]++;
            }

            int points2 = 0;
            for (int count : counts2) {
                if (count >= 3) points2 = count >= 5 ? 12 : count == 4 ? 6 : 3;
            }

            score2 += points2;
            out2.println("Ronda " + round + ": " + java.util.Arrays.toString(dice) + " - Puntos: " + points2);
        }

        out1.println("Puntuación final: " + score1);
        out2.println("Puntuación final: " + score2);

        in1.close();
        out1.close();
        clientSocket1.close();

        in2.close();
        out2.close();
        clientSocket2.close();
        serverSocket.close();
    }
}
