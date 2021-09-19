package utility;

import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Receiver {

    private final ServerSocketChannel serverSocketChannel;
    private final Logger logger;
    private final Deliver deliver;

    public Receiver(ServerSocketChannel aServerSocketChannel, Logger aLogger, Deliver aDeliver) {
        serverSocketChannel = aServerSocketChannel;
        logger = aLogger;
        deliver = aDeliver;
    }

    public void start() {

        while (true) {

            try (SocketChannel client = serverSocketChannel.accept()) {

                client.configureBlocking(false);

                logger.info("The client(" + client.getRemoteAddress() + ") is connected!");

                while (client.isOpen() && client.isConnected()) {

                    int count = 0;
                    int previousCount;

                    ByteBuffer buffer = ByteBuffer.allocate(4096);

                    do {
                        previousCount = count;
                        count = client.read(buffer);
                    } while (buffer.hasRemaining() && (count > 0 || previousCount == 0));

                    buffer.flip();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.slice().array());
                    ObjectInputStream inObj = new ObjectInputStream(byteArrayInputStream);

                    WrappedCommand aCommand = (WrappedCommand) inObj.readObject();
                    logger.info("Server receive a command: " + aCommand.toString());

                    deliver.answer(aCommand, client);
                }
            } catch (IOException exception) {
                logger.info("Client disconnected!");
            } catch (ClassNotFoundException exception) {
                logger.info("Client's version is outdated!");
            }
        }
    }
}