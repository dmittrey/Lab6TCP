package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class to works with user's input and console
 */
public class ConsoleWorker {

    private final Scanner scanner;
    private BufferedReader bufferedReader;
    private boolean exeStatus;

    public ConsoleWorker(Scanner aScanner) {
        exeStatus = false;
        scanner = aScanner;
    }

    public void print(Object anObj) {
        if (!exeStatus) System.out.print(anObj);
    }

    public String read() throws IOException {

        try {
            return (!exeStatus) ? scanner.nextLine().trim() : bufferedReader.readLine();
        } catch (NoSuchElementException exception) {
            System.exit(0);
            return null;
        }
    }

    public boolean getExeStatus() {
        return exeStatus;
    }

    public void setExeStatus(boolean anExeStatus) {
        exeStatus = anExeStatus;
    }

    public void setBufferedReader(BufferedReader aBufferedReader) {
        bufferedReader = aBufferedReader;
    }
}