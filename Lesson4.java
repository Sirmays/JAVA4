import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    public static void main(String[] args) {
        playGame();

    }

    public static void playGame() {

        char[][] field = createField();

        drawField(field);

        while (true) {
            if (!checkNextPlayerTurn(field)) {
                return;
            }

            if (!checkNextCompTurn(field)) {
                return;
            }

            drawField(field);
        }

    }

    static boolean checkNextCompTurn(char[][] field) {
        nextCompTurn(field);
        drawField(field);
        if (checkDraw(field)) {
            drawField(field);
            System.out.println("It is draw. The end.");
            return false;
        }
        if (ifWin(field, '0')) {
            drawField(field);
            System.out.println("Sorry. Comp win.");
            return false;
        }
        return true;
    }

    static boolean checkNextPlayerTurn(char[][] field) {
        nextPlayerTurn(field);
        drawField(field);
        if (checkDraw(field)) {
            drawField(field);
            System.out.println("It is draw. The end.");
            return false;
        }
        if (ifWin(field, 'X')) {
            drawField(field);
            System.out.println("Congratulations! You win!");
            return false;
        }
        return true;
    }

    static boolean ifWin(char[][] field, char sign) {
        int coinc1 = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length - 1; j++) {
                if (field[i][j] == field[i][j + 1] && field[i][j] == sign) coinc1++;
            }
            if (coinc1 == 3) return true;
        }
        int coinc2 = 0;
        for (int i = 0; i < field.length - 1; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == field[i + 1][j] && field[i][j] == sign) coinc2++;
            }
            if (coinc2 == 3) return true;
        }
        int coinc3 = 0;
        for (int i = 0; i < field.length - 1; i++) {
            for (int j = 0; j < field.length - 1; j++) {
                if (field[i][j] == field[i + 1][j + 1] && field[i][j] == sign) coinc3++;
            }
            if (coinc3 == 3) return true;
        }

        int coinc4 = 0;
        for (int i = 0; i < field.length - 1; i++) {
            for (int j = 1; j < field.length; j++) {
                if (field[i][j] == field[i + 1][j - 1] && field[i][j] == sign) coinc4++;
            }
            if (coinc4 == 3) return true;
        }
        return false;
    }


    static boolean checkDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static int checkCoordinateRange(Scanner scanner, char coordName) {
        int val;
        do {
            System.out.printf("Enter %s coordinate[1-5]%n", coordName);
            val = scanner.nextInt() - 1;
        } while (val < 0 || val > 4);
        return val;
    }

    static boolean ifCellFree(char[][] field, int x, int y) {
        return field[x][y] != '-';
    }

    static void nextCompTurn(char[][] field) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(5);
            y = random.nextInt(5);
        } while (ifCellFree(field, x, y));

        field[x][y] = '0';
    }

    static void nextPlayerTurn(char[][] field) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            x = checkCoordinateRange(scanner, 'X');
            y = checkCoordinateRange(scanner, 'Y');
        } while (ifCellFree(field, x, y));

        field[x][y] = 'X';
    }

    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");

            }
            System.out.println();
        }
        System.out.println();

    }

    static char[][] createField() {
        return new char[][]{
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'}

        };
    }
}

