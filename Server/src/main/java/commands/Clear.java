package commands;

import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for remove all elements from collection
 */
public class Clear extends CommandAbstract {

    private final CollectionManager collectionManager;

    public Clear(CollectionManager aCollectionManager) {
        super("clear", "clear the collection");
        collectionManager = aCollectionManager;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {
        collectionManager.clear();

        return new WrappedAnswer(TextFormatting.getGreenText("\n\tSuccessful!\n"));
    }
}