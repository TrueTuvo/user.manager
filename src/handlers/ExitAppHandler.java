package handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import ua.com.rcp.zabara.Utils;

/**
 * Handler, which writes updates to the database.txr and close app
 * 
 * @author SZabara
 *
 */
public class ExitAppHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        if (MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Confirmation", "Do you want to exit?")) {
            Utils.writePersonsToFile();
            Display.getCurrent().getActiveShell().close();        
        }
        return null;
    }

}
