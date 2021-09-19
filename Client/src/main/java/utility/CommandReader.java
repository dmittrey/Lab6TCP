package utility;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CommandReader read and parse strings from input to WrappedCommand object
 */
public class CommandReader {

    private final ConsoleWorker console;
    private final CommandManager commandManager;


    public CommandReader(ConsoleWorker aConsole, SocketWorker aSocketWorker) {

        console = aConsole;
        commandManager = new CommandManager(console, this, aSocketWorker);
    }

    public void enable() {

        while (true) {
            console.print("Enter the command: ");

            String line;

            try {
                line = console.read();
            } catch (IOException exception) {
                line = null;
            }

            WrappedCommand newCommand = readCommand(line);
            if (newCommand.getCommand().equals("exit") && newCommand.getArg() == null) {

                console.print(TextFormatting.getGreenText("\tHave a nice day!\n"));
                return;
            }

            commandManager.transferCommand(newCommand);
        }
    }


    public WrappedCommand readCommand(String anInputString) {

        if (anInputString == null) return null;

        String command;
        String arg;
        Pattern commandName = Pattern.compile("^\\w+\\s+");
        Pattern argName = Pattern.compile("^.+");

        Matcher matcher = commandName.matcher(anInputString + " ");

        if (matcher.find()) {
            command = matcher.group().trim();
        } else {
            return null;
        }

        matcher = argName.matcher(anInputString.substring(command.length()));

        if (matcher.find()) {
            arg = matcher.group().trim();
            if (arg.equals("")) arg = null;
        } else arg = null;

        return new WrappedCommand(command, arg);
    }
}