package commands;

import data.StudyGroup;
import utility.CollectionManager;
import utility.TextFormatting;
import utility.WrappedAnswer;
import utility.WrappedCommand;

import java.util.Deque;

/**
 * Class to update study groups in collection by id
 */
public class UpdateId extends CommandAbstract {

    private final CollectionManager collectionManager;
    private final Deque<String> previousCommands;

    public UpdateId(CollectionManager aCollectionManager, Deque<String> aPreviousCommands) {
        super("update", "update the element`s value, whose ID is equal to the given. " +
                TextFormatting.getBlueText("\n\tYou should to enter ID after entering a command"));
        collectionManager = aCollectionManager;
        previousCommands = aPreviousCommands;
    }

    @Override
    public WrappedAnswer execute(WrappedCommand aCommand) {

        String anArg = aCommand.getArg();
        StudyGroup upgradedGroup = aCommand.getStudyGroup();

        Object studyGroup = collectionManager.getId(Integer.parseInt(anArg));

        if (studyGroup != null) collectionManager.remove((StudyGroup) studyGroup);
        else {
            previousCommands.pollLast();
            return new WrappedAnswer(TextFormatting.getRedText("\tAn object with this id does not exist!\n"));
        }

        upgradedGroup.setId(Integer.parseInt(anArg));
        collectionManager.add(upgradedGroup);

        return new WrappedAnswer(TextFormatting.getGreenText("\n\tObject has been updated!\n"));
    }
}