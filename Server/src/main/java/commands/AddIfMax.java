package commands;

import data.StudyGroup;
import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

import java.util.Deque;

/**
 * Class for add maximum element in collection
 */
public class AddIfMax extends CommandAbstract {

    private final CollectionManager collectionManager;
    private final Deque<String> previousCommands;

    public AddIfMax(CollectionManager aCollectionManager, Deque<String> aPreviousCommands) {
        super("add_if_max", "add new element to the collection, if it`s greater, " +
                "than biggest element of this collection.");
        collectionManager = aCollectionManager;
        previousCommands = aPreviousCommands;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        StudyGroup studyGroup = aCommand.getStudyGroup();

        if (collectionManager.getMax() != null && studyGroup.compareTo(collectionManager.getMax()) <= 0) {
            previousCommands.pollLast();
            return new WrappedAnswer(TextFormatting.getRedText("\tStudy group isn't worst!\n"));
        }

        collectionManager.add(studyGroup);
        return new WrappedAnswer(TextFormatting.getGreenText("\n\n\tStudy group has been added!\n"));
    }
}