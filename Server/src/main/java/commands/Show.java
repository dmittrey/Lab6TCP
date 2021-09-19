package commands;

import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class to print all elements from collection in stdout
 */
public class Show extends CommandAbstract {

    private final CollectionManager collectionManager;

    public Show(CollectionManager aCollectionManager) {
        super("show", "print all elements in string representation to standard output");
        collectionManager = aCollectionManager;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        if (collectionManager.getCollection().size() == 0)
            return new WrappedAnswer(TextFormatting.getRedText("\tCollection is empty!\n"));

        return new WrappedAnswer(collectionManager.getCollection());
    }
}