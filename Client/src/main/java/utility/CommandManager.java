package utility;

import data.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * The class that serves the command
 */
public class CommandManager {

    private final CommandReader commandReader;
    private final Validator validator;
    private final ConsoleWorker console;
    private final Set<String> usedScripts;
    private final Deliver deliver;
    private final StudyGroupFactory studyGroupFactory;


    public CommandManager(ConsoleWorker aConsole, CommandReader aCommandReader, SocketWorker aSocketWorker) {
        commandReader = aCommandReader;
        validator = new Validator();
        console = aConsole;
        usedScripts = new HashSet<>();
        deliver = new Deliver(aSocketWorker);
        studyGroupFactory = new StudyGroupFactory(new FieldsGetter(console));
    }


    public void transferCommand(WrappedCommand aCommand) {

        if (validator.notObjectArgumentCommands(aCommand)) console.print(deliver.send(aCommand));

        else if (validator.objectArgumentCommands(aCommand)) {
            StudyGroup newStudyGroup = studyGroupFactory.createStudyGroup();
            if (newStudyGroup != null) {
                aCommand.addStudyGroup(newStudyGroup);
                console.print(deliver.send(aCommand));
            }

        } else if (validator.validateScriptArgumentCommand(aCommand)) executeScript(aCommand.getArg());

        else console.print(TextFormatting.getRedText("\tCommand entered incorrectly!\n"));
    }

    private void executeScript(String scriptName) {
        if (usedScripts.add(scriptName)) {

            try {

                if (usedScripts.size() == 1) console.setExeStatus(true);

                ScriptReader scriptReader = new ScriptReader(this, commandReader, console,
                        new File(scriptName));
                try {
                    scriptReader.read();

                    System.out.println(TextFormatting.getGreenText("\n\tThe script " + scriptName
                            + " was processed successfully!\n"));
                } catch (IOException exception) {

                    usedScripts.remove(scriptName);

                    if (usedScripts.size() == 0) console.setExeStatus(false);

                    if (!new File(scriptName).exists()) console.print(
                            TextFormatting.getRedText("\n\tThe script does not exist!\n"));
                    else if (!new File(scriptName).canRead()) console.print(
                            TextFormatting.getRedText("\n\tThe system does not have rights to read the file!\n"));
                    else console.print("\n\tWe have some problem's with script!\n");
                }

                usedScripts.remove(scriptName);

                if (usedScripts.size() == 0) console.setExeStatus(false);

            } catch (FileNotFoundException e) {
                console.print("Script not found!");
            }
        }
    }
}