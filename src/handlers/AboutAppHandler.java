package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import jface.elements.dialogs.AboutApplicationDialog;

/**
 * Handler, which displaying additional info about app
 * 
 * @author SZabara
 *
 */
public class AboutAppHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        return new AboutApplicationDialog().open();
    }

}
