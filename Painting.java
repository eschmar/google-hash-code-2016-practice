import java.io.*;
import java.util.Scanner;

/**
 * Painting
 * Created by eschmar on 08/02/16.
 */
public class Painting {
    public static void main(String[] args) {
        PaintingParser parser = new PaintingParser(args[0]);
        parser.run();
    }
}
