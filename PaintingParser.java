import java.io.*;
import java.util.Scanner;

/**
 * Created by eschmar on 09/02/16.
 */
public class PaintingParser {

    private String fileName;
    private String fileExtension;

    private int commandCounter = 0;
    private int rows, cols;
    private char[][] inputArray;
    private FileWriter out;

    private static final char CELL_EMPTY = '.';
    private static final char CELL_PAINTED = '#';

    private static final String CMD_LINE = "PAINT_LINE";
    private static final String CMD_SQUARE = "PAINT_SQUARE";

    public PaintingParser(String file) {
        this.fileName = file.substring(0, file.lastIndexOf('.'));
        this.fileExtension = file.substring(file.lastIndexOf('.'), file.length());
        this.readInput();
    }

    public void run() {
        File outputFile = new File(this.fileName + ".out");

        try {
            this.out = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(this.out);
            this.parseHorizontalLines(bw);
            this.out.write("" + this.commandCounter);
            bw.flush();
            bw.close();

        } catch (IOException e) {
            // file not found.
            System.out.printf("[ERROR] " + e.getMessage());
        }

        System.out.println("\nParsed input file '" + this.fileName + this.fileExtension + "' and wrote output to '" + this.fileName + ".out'");
    }

    private void readInput() {
        File inputFile = new File(this.fileName + this.fileExtension);

        try {
            Scanner in = new Scanner(inputFile);
            this.rows = in.nextInt();
            this.cols = in.nextInt();
            this.inputArray = new char[this.rows][this.cols];

            for (int i = 0; i < this.rows; i++) {
                this.inputArray[i] = (in.next()).toCharArray();
            }

            in.close();

        } catch (FileNotFoundException e) {
            // file not found.
            System.out.printf("[ERROR] " + e.getMessage());
        }
    }

    private void parseHorizontalLines(BufferedWriter bw) {
        for (int i = 0; i < this.rows; i++) {
            int j = 0;
            while (j < this.cols) {
                if (inputArray[i][j] == CELL_PAINTED) {
                    int start = j;
                    while (j < this.cols && this.inputArray[i][j] == CELL_PAINTED) {
                        j++;
                    }

                    j--;
                    this.commandCounter++;

                    try {
                        bw.newLine();
                        bw.write(CMD_LINE + " " + i + " " + start + " " + i + " " + j);
                    }catch (IOException e) {
                        // error while writing
                        System.out.printf("[ERROR] " + e.getMessage());
                    }
                }

                j++;
            }
        }
    }
}
