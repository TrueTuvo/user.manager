package ua.com.rcp.zabara;

import java.net.URL;

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

    public static final String COMMAND_NEW = "ua.com.rcp.zabara.command.new";
    public static final String COMMAND_SAVE = "ua.com.rcp.zabara.command.save";
    public static final String COMMAND_DELETE = "ua.com.rcp.zabara.command.delete";
    public static final String COMMAND_CANCEL = "ua.com.rcp.zabara.command.cancel";

    public static final String DATABASE_PATH = "C:\\luxoft\\database.txt";

    /**
     * Object of this class we won't create
     */
    private Utils() {
        super();
    }

    /**
     * Writes person's data to fields of the right side
     * 
     * @param mainComposite right side
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
     * Clear all fields in the right side
     */
    public static void putEmptyPersonData(ComplexComposite mainComposite) {
        mainComposite.getNameTextField().setText("");
        mainComposite.getGroupTextField().setText("");
        mainComposite.getSwtCheckdone().setSelection(false);
    }

    /**
     * Discards changes which did without save in the composite.
     * 
     * @param mainComposite composite with changes
     * @param currentPerson person with data that without changes
     */
    public static void removeChangesPersonData(ComplexComposite mainComposite, Person currentPerson) {
        mainComposite.getNameTextField().setText(currentPerson.getName());
        mainComposite.getGroupTextField().setText(String.valueOf(currentPerson.getGroup()));
        mainComposite.getSwtCheckdone().setSelection(currentPerson.isSwtDone());
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
        ;
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
            ImageDescriptor id = ImageDescriptor.createFromURL(imageURL);
            result = id.createImage();
            return result;
        } catch (Exception e) {
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
        } catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * returns current TableViewerPart's object of the app
     * 
     * @return TableViewerPart's object of the app
     */
    public static TableViewerPart getTableViewerPart() {
        IWorkbenchPage tablePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewPart tableViewPart = null;
        try {
            tableViewPart = tablePage.showView(TableViewerPart.ID);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
        return (TableViewerPart) tableViewPart;

    }

    /**
     * returns current CompositePart's object of the app
     * 
     * @return CompositePart's object of the app
     */
    public static CompositePart getCompositePart() {
        IWorkbenchPage compositePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewPart compositeViewPart = null;
        try {
            compositeViewPart = compositePage.showView(CompositePart.ID);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
        return (CompositePart) compositeViewPart;

    }
}
