package commands;

import java.util.Queue;

import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for displaying last 14 commands
 */
public class History extends CommandAbstract {

    Queue<String> previousCommands;

    public History(Queue<String> aPreviousCommands) {
        super("history", "print the last 14 commands (without their arguments)");
        previousCommands = aPreviousCommands;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        previousCommands.stream().map(command -> ")" + command + "\n").forEach(sb::append);

        return new WrappedAnswer(sb.toString());
    }
}