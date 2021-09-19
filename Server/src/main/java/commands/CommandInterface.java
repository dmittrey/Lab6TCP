package commands;

import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Interface for commands
 */
public interface CommandInterface {

    /**
     * Method for print command's description
     */
    String getDescription();

    /**
     * Method for execute command and return execution status
     */
    WrappedAnswer execute(WrappedCommand aWrappedCommand);
}