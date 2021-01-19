package handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import jface.elements.model.ModelProvider;
import jface.elements.model.Person;
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
            try {
                FileWriter tfw = new FileWriter(new File(Utils.DATABASE_PATH));
                BufferedWriter tbw = new BufferedWriter(tfw);

                for (Person person : ModelProvider.INSTANCE.getPersons()) {
                    tbw.write(person.getName() + "," + person.getGroup() + "," + person.isSwtDone());
                    tbw.newLine();
                }
                tbw.flush();

                tbw.close();
            } catch (IOException ex) {
                System.err.println("Some problem with writing file. Changes was not saved.");
            }
            Display.getCurrent().getActiveShell().close();
        }
        return null;
    }

}
