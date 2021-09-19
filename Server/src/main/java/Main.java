import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

public class Main {

    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Entering server!");

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            SocketAddress address = new InetSocketAddress(3134);
            serverSocketChannel.bind(address);

            CollectionManager collectionManager = new CollectionManager();

            FileWorker fileWorker = new FileWorker(collectionManager, logger);
            if (!fileWorker.getFromXmlFormat()) return;

            AutoGenFieldsSetter fieldsSetter = new AutoGenFieldsSetter(collectionManager.getHighUsedId());

            Invoker invoker = new Invoker(collectionManager, fileWorker);

            Deliver deliver = new Deliver(invoker, fieldsSetter, logger);
            Receiver receiver = new Receiver(serverSocketChannel, logger, deliver);

            Runtime.getRuntime().addShutdownHook(new Thread(new ExitSaver(fileWorker, logger)));

            receiver.start();

        } catch (IOException exception) {
            logger.error("\tSome problem's with network! Reboot server please!");
            System.exit(1);
        }
    }
}