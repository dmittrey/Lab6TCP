package utility;

import org.slf4j.Logger;

public class ExitSaver implements Runnable {

    private final FileWorker fileWorker;
    private final Logger logger;

    public ExitSaver(FileWorker aFileWorker, Logger aLogger) {
        fileWorker = aFileWorker;
        logger = aLogger;
    }

    @Override
    public void run() {
        logger.warn("Client shutdown server!");
        fileWorker.saveToXml();
    }
}
