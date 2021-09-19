package utility;

import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Deliver {

    private final Invoker invoker;
    private final AutoGenFieldsSetter fieldsSetter;
    private final Logger logger;

    public Deliver(Invoker anInvoker, AutoGenFieldsSetter aFieldsSetter, Logger aLogger) {
        invoker = anInvoker;
        fieldsSetter = aFieldsSetter;
        logger = aLogger;
    }

    public void answer(WrappedCommand aCommand, SocketChannel client) throws IOException {
        WrappedAnswer anAnswer = invoker.execute(fieldsSetter.setFields(aCommand));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outObj = new ObjectOutputStream(byteArrayOutputStream);
        outObj.writeObject(anAnswer);

        while (client.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray())) > 0);
        logger.info("Server send an answer!");
    }
}
