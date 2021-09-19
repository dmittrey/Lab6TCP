package utility;

import org.slf4j.Logger;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class to works with user's input
 */
public class ConsoleWorker {

    private final Scanner scanner;
    private final Logger logger;

    public ConsoleWorker(Scanner aScanner, Logger aLogger) {
        scanner = aScanner;
        logger = aLogger;
    }

    public void print(Object anObj) {
        System.out.print(anObj);
    }

    public String read() {

        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException exception) {
            logger.info("Server was turned off!");
            System.exit(0);
            return null;
        }
    }
}