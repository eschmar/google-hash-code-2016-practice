import java.io.*;
import java.util.Scanner;

/**
 * HashCode test
 * Exercise
 * <p>
 * Created by Simone on 08/02/16.
 */
public class HashCdTest {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("logo.in");
        Scanner in = new Scanner(inputFile);
        PrintWriter out = new PrintWriter("logo.out");
        out.println("               ");
        PrintWriter count = new PrintWriter("logo.out");

        int counter = 0;
        int row = in.nextInt();
        int column = in.nextInt();
        in.useDelimiter("");
        System.out.println(row + " rows and " + column + " columns");

        int[][] symbArr = new int[row][column];
        char ch;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                do {
                    ch = in.next().charAt(0);
                } while (ch != '.' && ch != '#');
                if (ch == '.') {
                    symbArr[i][j] = 0;
                } else {
                    symbArr[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            int j = 0;
            while (j < column) {
                if (symbArr[i][j] == 1) {
                    int start = j;
                    while (j < column && symbArr[i][j] == 1) {
                        j++;
                    }
                    j--;
                    int end = j;
                    out.printf("PAINT_LINE %d %d %d %d\n", i, start, i, end);
                    System.out.printf("PAINT_LINE %d %d %d %d\n", i, start, i, end);
                    counter++;
                }
                j++;
                //System.out.println();
            }
        }


        in.close();
        out.close();
        count.printf("%d", counter);
        count.close();
/*
        PrintWriter count = new PrintWriter(new FileWriter("solution.txt", true));
        count.println(counter);
        count.close();
        */


    }
}
