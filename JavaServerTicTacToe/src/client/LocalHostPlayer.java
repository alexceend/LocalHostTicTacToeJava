package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LocalHostPlayer {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 4999); //CLIENTE 1 SE CONECTA EN LOCALHOST
        } catch (IOException e) {
            System.err.println("FAILED TO CONNECT TO SERVER");
            System.exit(1);
        }
        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(socket.getOutputStream());

        while (true){
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str;
            boolean isTurnActive = false;
            while (!(str = bf.readLine()).equals("END")){
                System.out.println("Server: " + str);
                if(str.equals("Turn of player 1")) isTurnActive = true;
            }
            if(isTurnActive){
                System.out.println("Select a tile (Row, col)");
                int row = sc.nextInt();
                int col = sc.nextInt();
                out.println(row);
                out.println(col);
                out.flush();
                isTurnActive = false;
            }
        }
    }
}
