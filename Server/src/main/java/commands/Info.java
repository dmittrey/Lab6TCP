package commands;

import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for displaying all information about collection
 */
public class Info extends CommandAbstract {

    private final CollectionManager collectionManager;

    public Info(CollectionManager aCollectionManager) {
        super("info", "Print information about the collection (type, "
                + "initialization date, number of elements, etc.) to standard output");
        collectionManager = aCollectionManager;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        return new WrappedAnswer(TextFormatting.getBlueText("\nInformation about collection:\n")
                + collectionManager.getInfo());
    }
}