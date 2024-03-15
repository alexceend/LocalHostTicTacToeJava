package ticTacToe;

public class TicTacToe {
    private int[][] tablero = new int[3][3];

    public TicTacToe() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public boolean setActive(int player, int x, int y) {
        if (tablero[x][y] != 0) return false;
        tablero[x][y] = player;
        return true;
    }

    public String toString() {
        StringBuilder stringFinal = new StringBuilder();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                String valor = " ";
                switch (tablero[i][j]) {
                    case -1:
                        valor = "X";
                        break;
                    case 1:
                        valor = "O";
                        break;
                    default:
                }
                stringFinal.append("[").append(valor).append("]");
            }
            stringFinal.append("\n");
        }
        return stringFinal.toString();
    }

    public boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check rows for a win
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != 0 && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Check columns for a win
    private boolean checkColumns() {
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != 0 && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return true;
            }
        }
        return false;
    }

    // Check diagonals for a win
    private boolean checkDiagonals() {
        return (tablero[0][0] != 0 && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) ||
                (tablero[0][2] != 0 && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]);
    }


}
