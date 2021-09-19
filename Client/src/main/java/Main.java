import utility.CommandReader;
import utility.ConsoleWorker;
import utility.SocketWorker;
import utility.TextFormatting;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket()) {

            System.out.println(TextFormatting.getGreenText("\n\t\t\t\u0020\u0020\u0020----------------"));
            System.out.println(TextFormatting.getGreenText("\t\t\tWelcome to Lab6 Client!"));
            System.out.println(TextFormatting.getGreenText("\t\t\t\u0020\u0020------------------\n"));

            String answer;

            InetAddress remoteHostAddress = InetAddress.getLocalHost();

            int remoteHostPort = 3134;

            do {
                System.out.print(TextFormatting.getGreenText("Do you want to specify the address of the remote host?" +
                        "[\"y\", \"n\"]: "));

                answer = scanner.nextLine();

            } while (!answer.equals("y") && !answer.equals("n"));

            if (answer.equals("y")) {

                String arg;

                Pattern hostAddress = Pattern.compile("^([\\w.]+):(\\d{4})\\s*");

                do {

                    System.out.print("\nPlease, enter remote host address! (Example: host_name:port): ");

                    arg = scanner.nextLine();

                }
                while (!hostAddress.matcher(arg).find());

                try {
                    remoteHostAddress = InetAddress.getByName(arg.split(":")[0]);
                } catch (UnknownHostException e) {
                    System.out.println("\nThe program could not find the server by the specified address!\n\t " +
                            "The default address(localhost:3134) will be used!");
                }

                remoteHostPort = Integer.parseInt(arg.split(":")[1].trim());
            }

            SocketAddress serverSocketAddress = new InetSocketAddress(remoteHostAddress, remoteHostPort);
            socket.connect(serverSocketAddress);

            ConsoleWorker console = new ConsoleWorker(scanner);

            SocketWorker socketWorker = new SocketWorker(socket, serverSocketAddress);
            System.out.println(socketWorker.getInformation());

            CommandReader commandReader = new CommandReader(console, socketWorker);
            commandReader.enable();

        } catch (IOException e) {
            System.out.println(TextFormatting.getRedText("\nYour computer has problems with the network, " +
                    "run the application again!"));
            System.exit(1);
        }
    }
}