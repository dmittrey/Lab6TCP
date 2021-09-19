package utility;

import data.*;
import utility.interfaces.FieldsReceiver;

/**
 * Class for creating Study groups with autogenerate id and Date
 */
public class StudyGroupFactory {

    private final FieldsReceiver fieldsReceiver;

    public StudyGroupFactory(FieldsGetter aFieldsGetter) {

        fieldsReceiver = aFieldsGetter;
    }

    /**
     * Method creates new StudyGroup object
     */
    public StudyGroup createStudyGroup() {

        String name = fieldsReceiver.getName();
        Coordinates coordinates = fieldsReceiver.getCoordinates();
        Integer studentsCount = fieldsReceiver.getStudentsCount();
        Double averageMark = fieldsReceiver.getAverageMark();
        FormOfEducation formOfEducation = fieldsReceiver.getFormOfEducation();
        Semester semester = fieldsReceiver.getSemester();
        Person groupAdmin = fieldsReceiver.getGroupAdmin();

        if ((name != null) &&
                (coordinates != null) &&
                (studentsCount != null) &&
                (semester != null) &&
                (groupAdmin != null)) {

            return new StudyGroup(0, name, coordinates, null, studentsCount,
                    averageMark, formOfEducation, semester, groupAdmin);
        }

        return null;
    }
}