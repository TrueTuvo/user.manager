package ua.com.rcp.zabara;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jface.elements.model.ModelProvider;
import jface.elements.model.Person;
import parts.CompositePart;
import parts.TableViewerPart;
import swt.elements.ComplexComposite;

/**
 * Using for simplification routine operation. consist of static methods and constants.
 * 
 * @author SZabara
 *
 */
public class Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static final String COMMAND_NEW = "ua.com.rcp.zabara.command.new";
    public static final String COMMAND_SAVE = "ua.com.rcp.zabara.command.save";
    public static final String COMMAND_DELETE = "ua.com.rcp.zabara.command.delete";
    public static final String COMMAND_CANCEL = "ua.com.rcp.zabara.command.cancel";

    public static final String DATABASE_PATH = getDataBaseFilePath();

    /**
     * Object of this class we won't create
     */
    private Utils() {
        super();
    }

    /**
     * Writes person's data to fields of the right side
     * 
     * @param complexComposite right side
     * @param selectionPerson selected Person
     */
    public static void putPersonData(ComplexComposite complexComposite, Person selectionPerson) {
        if (selectionPerson != null && !complexComposite.getDeleteButton().getSelection()) {
            complexComposite.getNameTextField().setText(selectionPerson.getName());
            complexComposite.getGroupTextField().setText(String.valueOf(selectionPerson.getGroup()));
            complexComposite.getSwtCheckdone().setSelection(selectionPerson.isSwtDone());
        }
    }

    /**
     * Clear all fields in the complexComposite
     */
    public static void putEmptyPersonData(ComplexComposite complexComposite) {
        complexComposite.getNameTextField().setText("");
        complexComposite.getGroupTextField().setText("");
        complexComposite.getSwtCheckdone().setSelection(false);
    }

    /**
     * Discards changes which did without save in the composite.
     * 
     * @param complexComposite composite with changes
     * @param currentPerson person with data that without changes
     */
    public static void removeChangesPersonData(ComplexComposite complexComposite, Person currentPerson) {
        complexComposite.getNameTextField().setText(currentPerson.getName());
        complexComposite.getGroupTextField().setText(String.valueOf(currentPerson.getGroup()));
        complexComposite.getSwtCheckdone().setSelection(currentPerson.isSwtDone());
    }

    /**
     * Updates person`s data
     * 
     * @param availablePerson person that must be changed.
     * @param name new name for person
     * @param group new gruop's number for person
     * @param swtDone
     */
    public static void updatePersonData(Person availablePerson, String name, int group, boolean swtDone) {
        availablePerson.setName(name);
        availablePerson.setGroup(group);
        availablePerson.setSwtDone(swtDone);
    }

    /**
     * 
     * Validates Person object
     * 
     * @param name can not be empty or equals null
     * @param group can not equals zero
     * @return if all conditions was kept, return true
     */
    public static boolean isValidData(String name, int group) {

        if (name == null || name.equals("") || group <= 0 || group > 99) {
            return false;
        }
        return true;
    }

    /**
     * Validates name by lenght
     * 
     * @param str String for validate
     * @return validation result
     */
    public static boolean isName(String str) {

        return str.length() < 100;
    }

    public static Image getImageForAbout() {
        Image result = null;

        try {
            URL imageURL = new URL("platform:/plugin/ua.com.rcp.zabara/icons/ñapture.png");
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(imageURL);
            result = imageDescriptor.createImage();
            return result;
        } catch (Exception e) {
            LOG.warn("Failed to get Image");
            e.printStackTrace();

        }

        return result;
    }

    /**
     * execute handler of the chosen command using IhandleService
     * 
     * @param command command for execution;
     * @param service interface, which contains same method
     */
    public static void executeCommand(String command, IHandlerService service) {
        try {
            service.executeCommand(command, null);
        } catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException e) {
            LOG.warn("Failed to execute command");
            e.printStackTrace();
        }
    }

    /**
     * returns current TableViewerPart's object of the app
     * 
     * @return TableViewerPart's object of the app
     */
    public static TableViewerPart getTableViewerPart() {
        IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView(TableViewerPart.ID);

        TableViewerPart tableViewerPart = null;
        if (viewPart != null) {
            try {
                tableViewerPart = (TableViewerPart) viewPart;
                return tableViewerPart;
            } catch (ClassCastException exception) {
                LOG.warn("Some problem with  classes cast. Current method can return incorrect result");
            }
        }
        return  null;

    }

    /**
     * returns current CompositePart's object of the app
     * 
     * @return CompositePart's object of the app
     */
    public static CompositePart getCompositePart() {
        IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView(CompositePart.ID);

        CompositePart compositePart = null;
        if (viewPart != null) {
            try {
                compositePart = (CompositePart) viewPart;
                return compositePart;
            } catch (ClassCastException exception) {
                LOG.warn("Some problem with  classes cast. Current method can return incorrect result");
            }
        }
        return  null;

    }

    private static String getDataBaseFilePath() {
        return System.getProperty("user.home") + File.separator + "persons.txt";

    }

    /**
     * reads persons from the file and put their to the ArrayList
     * 
     * @return ArrayList with persons from file
     */
    public static List<Person> readPersonsFromFile() {

        List<Person> persons = new ArrayList<Person>();

        File dataBaseFile = new File(DATABASE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(dataBaseFile))) {

            while (reader.ready()) {

                String personDataString = reader.readLine();
                if (!personDataString.equals("")) {
                    String[] personDataElements = personDataString.split(",");
                    try {
                        Person person = new Person(personDataElements[0], Integer.parseInt(personDataElements[1]),
                                Boolean.parseBoolean(personDataElements[2]));
                        persons.add(person);
                    } catch (Exception e) {
                        LOG.warn("failure when trying to read a person");
                    }
                }
            }
        } catch (FileNotFoundException exception) {
             LOG.warn("failure when trying to find the DATABASE file");
        } catch (IOException e) {
             LOG.warn("failure when trying to read the DATABASE file");
        }

        return persons;

    }
    /**
     * Writes persons to the DATABASE file.
     * 
     */
    public static void writePersonsToFile() {
        try (FileWriter fileWriter = new FileWriter(new File(DATABASE_PATH));
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {

            for (Person person : ModelProvider.INSTANCE.getPersons()) {
                bufferedWriter.write(person.getName() + "," + person.getGroup() + "," + person.isSwtDone());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ex) {
            LOG.warn("Some problem with writing file. Changes was not saved.");
        }
    }
}
