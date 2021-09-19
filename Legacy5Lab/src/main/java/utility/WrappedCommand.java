package utility;

import data.StudyGroup;

import java.io.Serializable;

/**
 * Class to serialize requests to server
 */
public class WrappedCommand implements Serializable {

    private final String commandName;
    private final String argName;
    private StudyGroup studyGroup;

    public WrappedCommand(String aCommandName, String anArgName) {

        commandName = aCommandName;
        argName = anArgName;
        studyGroup = null;
    }

    public void addStudyGroup(StudyGroup aStudyGroup) {
        studyGroup = aStudyGroup;
    }

    public String getCommand() {
        return commandName;
    }

    public String getArg() {
        return argName;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    @Override
    public String toString() {
        return commandName + " "
                + (argName != null ? argName : "")
                + (studyGroup != null ? studyGroup : "");
    }

    public boolean isArgInt() {
        try {
            if (argName != null) {
                Integer.parseInt(argName);
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}