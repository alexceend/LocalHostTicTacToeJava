package server;

import ticTacToe.TicTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        boolean player1Turn = true;
        ServerSocket serverSocket = new ServerSocket(4999);
        Socket player1 = serverSocket.accept();
        System.out.println("ONE PLAYER CONNECTED! ");

        Socket player2 = serverSocket.accept();
        System.out.println("Both players connected");

        TicTacToe ticTacToe = new TicTacToe();

        InputStreamReader firstPlayerInputSR = new InputStreamReader(player1.getInputStream());
        InputStreamReader secondPlayerInputSR = new InputStreamReader(player2.getInputStream());
        BufferedReader firstPlayerBr = new BufferedReader(firstPlayerInputSR);
        BufferedReader secondPlayerBr = new BufferedReader(secondPlayerInputSR);
        PrintWriter firstPlayerPW = new PrintWriter(player1.getOutputStream());
        firstPlayerPW.println("YOU ARE PLAYER 1");
        PrintWriter secondPlayerPW = new PrintWriter(player2.getOutputStream());
        secondPlayerPW.println("YOU ARE PLAYER 2");

        while (!ticTacToe.checkWin()) {
            System.out.println(ticTacToe.toString());
            int row;
            int col;
            int n;
            do {
                if (player1Turn) {
                    firstPlayerPW.println(ticTacToe.toString());
                    secondPlayerPW.println(ticTacToe.toString());

                    firstPlayerPW.println("Turn of player 1");
                    secondPlayerPW.println("Turn of player 1");

                    firstPlayerPW.println("END");

                    firstPlayerPW.flush();
                    secondPlayerPW.flush();
                    row = Integer.parseInt(firstPlayerBr.readLine());
                    col = Integer.parseInt(firstPlayerBr.readLine());
                    System.out.println("Player 1: " + row + ", " + col);

                } else {
                    secondPlayerPW.println(ticTacToe.toString());
                    firstPlayerPW.println(ticTacToe.toString());

                    secondPlayerPW.println("Turn of player 2");
                    firstPlayerPW.println("Turn of player 2");

                    secondPlayerPW.println("END");

                    firstPlayerPW.flush();
                    secondPlayerPW.flush();
                    row = Integer.parseInt(secondPlayerBr.readLine());
                    col = Integer.parseInt(secondPlayerBr.readLine());
                    System.out.println("Player 2: " + row + ", " + col);
                }
                n = player1Turn ? 1 : -1;
            } while (!ticTacToe.setActive(n, row, col));
            player1Turn = !player1Turn;
        }

        String winner = !player1Turn ? "PLAYER 1" : "PLAYER 2";
        firstPlayerPW.println("GANA JUGADOR " + winner);
        secondPlayerPW.println("GANA JUGADOR " + winner);

        firstPlayerPW.flush();
        secondPlayerPW.flush();


    }
}
