package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import parts.CompositePart;
import parts.TableViewerPart;
import ua.com.rcp.zabara.Utils;


/**
 * Handler, which undo changes of selected person
 * 
 * @author SZabara
 *
 */
public class CancelPesonHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        TableViewerPart tableViewerPart = Utils.getTableViewerPart();

        CompositePart compositePart = Utils.getCompositePart();

        if (tableViewerPart.getCurrentPerson() != null) {
            Utils.removeChangesPersonData(compositePart.getComplexComposite(), tableViewerPart.getCurrentPerson());
            tableViewerPart.getViewer().refresh();
        }
        return compositePart;
    }

}
