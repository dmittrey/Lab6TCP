package commands;

import utility.FileWorker;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class to save collection in xml file
 */
public class Save extends CommandAbstract {

    private final FileWorker fileWorker;

    public Save(FileWorker aFileWorker) {
        super("save", "save the collection to file");
        fileWorker = aFileWorker;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        return fileWorker.saveToXml();
    }
}