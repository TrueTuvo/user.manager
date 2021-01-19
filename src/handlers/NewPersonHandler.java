package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import jface.elements.dialogs.CreateNewPersonDialog;
import parts.TableViewerPart;
import ua.com.rcp.zabara.Utils;

/**
 * Handler, which opens dialog for create new person
 * 
 * @author SZabara
 *
 */
public class NewPersonHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        TableViewerPart tableViewerPart = Utils.getTableViewerPart();
        return new CreateNewPersonDialog(tableViewerPart).open();

    }

}
