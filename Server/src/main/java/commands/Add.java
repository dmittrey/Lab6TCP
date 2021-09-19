package commands;

import data.StudyGroup;
import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for read study group from console and add this in collection
 */
public class Add extends CommandAbstract {

    private final CollectionManager collectionManager;

    public Add(CollectionManager aCollectionManager) {
        super("add", "add new element to the collection");
        collectionManager = aCollectionManager;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        StudyGroup studyGroup = aCommand.getStudyGroup();

        collectionManager.add(studyGroup);

        return new WrappedAnswer(TextFormatting.getGreenText("\n\tStudy group has been added!\n"));
    }
}