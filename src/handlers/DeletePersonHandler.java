package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import jface.elements.dialogs.DeletePersonDialog;
import parts.TableViewerPart;
import ua.com.rcp.zabara.Utils;

/**
 * Handler, which remove selected person from model
 * 
 * @author SZabara
 *
 */
public class DeletePersonHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        TableViewerPart tableViewerPart = Utils.getTableViewerPart();
        if (tableViewerPart.getCurrentPerson() != null) {
            new DeletePersonDialog(tableViewerPart).open();
        }
        return null;
    }

}