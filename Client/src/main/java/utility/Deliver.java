package utility;

/**
 * Class to send commands with encapsulated Study group's
 */
public class Deliver {

    private final SocketWorker socketWorker;

    public Deliver(SocketWorker aSocketWorker) {

        socketWorker = aSocketWorker;
    }

    public WrappedAnswer send(WrappedCommand aCommand) {

        return socketWorker.sendCommand(aCommand);
    }
}