package commands;

import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

import java.util.Deque;

/**
 * Class that remove object with current id from collection
 */
public class RemoveById extends CommandAbstract {

    private final CollectionManager collectionManager;
    private final Deque<String> previousCommands;

    public RemoveById(CollectionManager aCollectionManager, Deque<String> aPreviousCommands) {
        super("remove_by_id", "remove an element from the collection by ID." +
                TextFormatting.getBlueText("\n\tYou should to enter ID after entering a command"));
        collectionManager = aCollectionManager;
        previousCommands = aPreviousCommands;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        String anArg = aCommand.getArg();
        Integer anId = Integer.parseInt(anArg);

        if (!collectionManager.remove(collectionManager.getId(anId))) {
            previousCommands.pollLast();
            return new WrappedAnswer(TextFormatting.getRedText("\tAn object with this id does not exist!\n"));
        }

        return new WrappedAnswer(TextFormatting.getGreenText("\n\tObject has been removed!\n"));
    }
}