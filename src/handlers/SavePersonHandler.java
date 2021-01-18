package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import jface.elements.model.ModelProvider;
import jface.elements.model.Person;
import parts.CompositePart;
import parts.TableViewerPart;
import ua.com.rcp.zabara.Utils;

/**
 * Handler, which writes changes in the user form of selected person to the model
 * 
 * @author SZabara
 *
 */
public class SavePersonHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        String name = null;
        int group = 0;
        boolean swtDone = false;

        CompositePart compositePart = Utils.getCompositePart();
        try {
            name = compositePart.getComplexComposite().getNameTextField().getText();
            group = Integer.parseInt(compositePart.getComplexComposite().getGroupTextField().getText());
            swtDone = compositePart.getComplexComposite().getSwtCheckdone().getSelection();
        } catch (NumberFormatException ignore) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Incoorect input",
                    "Your input was incorrect. Please, put the correct data");
        }
        if (Utils.isValidData(name, group)) {
            TableViewerPart tableViewerPart = Utils.getTableViewerPart();
            Person selectionPerson = tableViewerPart.getCurrentPerson();

            if (tableViewerPart.getCurrentPerson() == null) {

                MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Information",
                        "Please, choose a row for saving data");
            } else {
                for (Person availablePerson : ModelProvider.INSTANCE.getPersons()) {
                    if (selectionPerson.equals(availablePerson)) {
                        Utils.updatePersonData(availablePerson, name, group, swtDone);
                        tableViewerPart.getViewer().refresh();
                    }
                }
            }
        }
        return null;
    }
}