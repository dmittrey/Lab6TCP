package commands;

import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

/**
 * Class for count elements whose less than specified amount of students count
 */
public class CountLessThanStudentsCount extends CommandAbstract {

    private final CollectionManager collectionManager;

    public CountLessThanStudentsCount(CollectionManager aCollectionManager) {
        super("count_less_than_students_count", "print the number of elements whose "
                + "studentsCount field value is less than the specified one" +
                TextFormatting.getBlueText("\n\tYou should to enter students count after entering a command"));
        collectionManager = aCollectionManager;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        String anArg = aCommand.getArg();

        if (collectionManager.getCollection().size() == 0)
            return new WrappedAnswer(TextFormatting.getRedText("\tCollection is empty!\n"));

        long count = collectionManager.getCollection().stream().filter(studyGroup ->
                studyGroup.getStudentsCount() < Integer.parseInt(anArg)).count();

        return new WrappedAnswer(TextFormatting.getGreenText("\tAmount of elements: " + count + "\n"));
    }
}