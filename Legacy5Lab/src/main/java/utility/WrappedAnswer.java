package utility;

import data.StudyGroup;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Set;

/**
 * Class to serialize answer's from server
 */
public class WrappedAnswer implements Serializable {

    private String information;
    private StudyGroup studyGroup;
    private Set<StudyGroup> collection;

    public WrappedAnswer(String anInformation) {
        information = anInformation;
    }

    public WrappedAnswer(StudyGroup aStudyGroup) {
        studyGroup = aStudyGroup;
    }

    public WrappedAnswer(Set<StudyGroup> aCollection) {
        collection = aCollection;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public Set<StudyGroup> getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (information != null)
            sb.append(information);

        if (studyGroup != null)
            sb.append(studyGroup).append("\n");

        if (collection != null)
            collection.stream().sorted(Comparator.comparing(StudyGroup::getName)).
                    forEach(sg -> sb.append(sg).append("\n"));

        return sb.toString();
    }
}