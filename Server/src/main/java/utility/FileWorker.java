package utility;

import org.slf4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * FileWorker is used to operate with file
 */
public class FileWorker {
    private final CollectionManager collectionManager;
    private final CollectionValidator collectionValidator;
    private final Logger logger;

    public FileWorker(CollectionManager aCollectionManager, Logger aLogger) {
        collectionManager = aCollectionManager;
        collectionValidator = new CollectionValidator(aCollectionManager);
        logger = aLogger;
    }

    public boolean getFromXmlFormat() {

        String filePath = System.getenv("FILE_PATH");

        if (filePath == null) {
            logger.info(TextFormatting.getRedText("Program didn't find xml file. " +
                    "Change the file path in the environment variable(FILE_PATH)!"));
            return false;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            /* init jaxb marshaller */
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            /* unmarshalling java objects from xml */
            CollectionManager defaultCollectionManager = (CollectionManager) jaxbUnmarshaller.unmarshal(bufferedReader);

            collectionValidator.validateCollection(defaultCollectionManager.getCollection());

            logger.info("Collection downloaded!");

        } catch (IOException e) {
            logger.info(checkStatus(filePath, TypeOfAccess.READ));
            return false;
        } catch (JAXBException e) {
            logger.info("XML file has been broken!");
        }

        return true;
    }

    public WrappedAnswer saveToXml() {

        String filePath = System.getenv("FILE_PATH");

        if (filePath == null) return new WrappedAnswer(TextFormatting.getRedText("\tProgram can't find xml file. " +
                "Change environmental variable(\"FILE_PATH\")!\n\n"));

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath))) {

            /* init jaxb marshaller */
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* set this flag to true to format the output */
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            /* marshaling of java objects in xml */
            jaxbMarshaller.marshal(collectionManager, outputStreamWriter);
        } catch (IOException e) {
            return new WrappedAnswer(checkStatus(filePath, TypeOfAccess.WRITE));
        } catch (JAXBException e) {
            return new WrappedAnswer(TextFormatting.getRedText("\n\tXML file has been broken!\n\n"));
        }

        return new WrappedAnswer(TextFormatting.getGreenText("\n\tCollection recorded successfully!\n"));
    }

    private String checkStatus(String filePath, TypeOfAccess type) {

        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                if (!file.canRead() && type == TypeOfAccess.READ)
                    return "Application can't read file!";

                if (!file.canWrite() && type == TypeOfAccess.WRITE)
                    return "Application can't write in file";

            } else return "It is directory's path!\n";
        } else return "File doesn't exist!";

        return null;
    }
}