package commands;

import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class print object form collection
 */
public class MinByStudentsCount extends CommandAbstract {

    private final CollectionManager collectionManager;

    public MinByStudentsCount(CollectionManager aCollectionManager) {
        super("min_by_students_count", "print any object from the collection whose " +
                "studentsCount field value is minimal");
        collectionManager = aCollectionManager;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        if (collectionManager.getMinStudentsCount() != null)
            return new WrappedAnswer(collectionManager.getMinStudentsCount());
        else
            return new WrappedAnswer(TextFormatting.getRedText("\tThere are no study groups in the collection yet!\n"));
    }
}