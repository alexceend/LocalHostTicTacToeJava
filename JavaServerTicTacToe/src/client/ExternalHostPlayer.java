package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ExternalHostPlayer {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("xx.x.xxx.xxx", 4999); //PONER HOST LOCAL (HAMACHI)
            Scanner sc = new Scanner(System.in);

            PrintWriter out = null;
            try {
                out = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                System.err.println("FAILED TO CONNECT TO SERVER");
                System.exit(1);
            }

            while (true){
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                BufferedReader bf = new BufferedReader(in);

                String str;
                boolean isTurnActive = false;
                while (!(str = bf.readLine()).equals("END")){
                    System.out.println("Server: " + str);
                    if(str.equals("Turn of player 2")) isTurnActive = true;
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
        }catch (IOException e){
            System.err.println("LOST CONNECTION WITH THE SERVER");
        }
    }
}
