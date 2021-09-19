package commands;

import java.util.Map;

import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for displaying all commands with explanations
 */
public class Help extends CommandAbstract {

    private final Map<String, CommandAbstract> commandsInfo;

    public Help(Map<String, CommandAbstract> aCommands) {
        super("help", "display help for available commands");
        commandsInfo = aCommands;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        StringBuilder sb = new StringBuilder();
        sb.append(TextFormatting.getBlueText("\nList of commands:\n\n"));

        commandsInfo.keySet().stream().filter(command -> !command.equals("save")).
                map(command -> "\t" + commandsInfo.get(command).getDescription() + "\n\n").
                forEach(sb::append);

        sb.append("\t").append("exit : end the program (without saving it to a file)").append("\n\n");

        return new WrappedAnswer(sb.toString());
    }
}