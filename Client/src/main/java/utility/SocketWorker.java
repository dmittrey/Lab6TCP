package utility;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Class to work with client's socket and handle exceptions from this work
 */
public class SocketWorker {

    private Socket socket;
    private final SocketAddress socketAddress;

    public SocketWorker(Socket aSocket, SocketAddress aSocketAddress) throws IOException {
        socket = aSocket;
        socketAddress = aSocketAddress;
    }

    public WrappedAnswer sendCommand(WrappedCommand aCommand) {

        try {
            OutputStream outputStream = socket.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);

            objOut.writeObject(aCommand);
            objOut.flush();

            byteArrayOutputStream.writeTo(outputStream);
            byteArrayOutputStream.reset();
            return receiveAnswer();
        } catch (IOException exception) {
            return new WrappedAnswer(TextFormatting.getRedText("\tCommand didn't send, try again!\n"));
        }
    }

    public WrappedAnswer receiveAnswer() {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            return (WrappedAnswer) objectInputStream.readObject();
        } catch (ClassNotFoundException exception) {
            return new WrappedAnswer(TextFormatting.getRedText("\tThe client version is not supported, please update the application!\n"));
        } catch (IOException exception) {
            return reConnect();
        }
    }

    private WrappedAnswer reConnect() {
        try {
            socket = new Socket();
            socket.connect(socketAddress);
            return new WrappedAnswer(TextFormatting.getGreenText("\tClient has been reconnected to server! " +
                    "Repeat the command!\n"));
        } catch (IOException exception) {
            return new WrappedAnswer(TextFormatting.getRedText("\tConnection refused, try to connect to another server!\n"));
        }
    }

    public String getInformation() {

        return TextFormatting.getGreenText("\n\t\t\t\t\u0020----------------") +
                TextFormatting.getGreenText("\t\t\t\tConnection status:") +
                TextFormatting.getGreenText("\t\t\t\t------------------\n") +
                "Remote host address:\t" + TextFormatting.getGreenText(String.valueOf(socket.getInetAddress())) + "\n\n" +
                "Remote host port:\t" + TextFormatting.getGreenText(String.valueOf(socket.getPort())) + "\n\n" +
                "Local host address:\t" + TextFormatting.getGreenText(String.valueOf(socket.getLocalAddress())) + "\n\n" +
                "Local host port:\t" + TextFormatting.getGreenText(String.valueOf(socket.getLocalPort())) + "\n\n";
    }
}