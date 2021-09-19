package utility;

/**
 * Class to validate commands
 */
public class Validator {

    private final AvailableCommands availableCommands;

    public Validator() {
        availableCommands = new AvailableCommands();
    }

    public boolean notObjectArgumentCommands(WrappedCommand aCommand) {
        return validateNoArgumentCommand(aCommand) ||
                validateNumArgumentCommands(aCommand) ||
                validateStringArgumentCommands(aCommand);
    }

    public boolean objectArgumentCommands(WrappedCommand aCommand) {
        return validateObjectArgumentCommands(aCommand) ||
                validateObjAndNumArgumentCommand(aCommand);
    }

    private boolean validateNoArgumentCommand(WrappedCommand aCommand) {
        return availableCommands.noArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.getArg() == null;
    }

    private boolean validateNumArgumentCommands(WrappedCommand aCommand) {
        return availableCommands.numArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.isArgInt() && Integer.parseInt(aCommand.getArg()) > 0;
    }

    private boolean validateStringArgumentCommands(WrappedCommand aCommand) {
        return availableCommands.stringArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.getArg() != null;
    }

    private boolean validateObjectArgumentCommands(WrappedCommand aCommand) {
        return availableCommands.objectArgumentCommands.contains(aCommand.getCommand()) &&
                aCommand.getArg() == null;
    }

    private boolean validateObjAndNumArgumentCommand(WrappedCommand aCommand) {
        return availableCommands.objAndNumArgumentCommand.contains(aCommand.getCommand()) &&
                aCommand.isArgInt() && Integer.parseInt(aCommand.getArg()) > 0;
    }

    public boolean validateScriptArgumentCommand(WrappedCommand aCommand) {
        return availableCommands.scriptArgumentCommand.equals(aCommand.getCommand()) &&
                aCommand.getArg() != null;
    }
}