package commands;

import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for print information about command execute_script
 */
public class ExecuteScript extends CommandAbstract {

    public ExecuteScript() {
        super("execute_script", "Read and execute script from entered file" +
                TextFormatting.getBlueText("\n\tYou should to enter script name after entering a command"));
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {
        return null;
    }
}