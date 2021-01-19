package jface.elements.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import jface.elements.model.Person;
import parts.TableViewerPart;
import swt.elements.CheckLineComposite;
import swt.elements.InputDataComposite;
import ua.com.rcp.zabara.Utils;

/**
 * 
 * Every time, when user try to add new person, must fill empty fields confirm action in dialog window
 * 
 * @author SZabara
 */
public class CreateNewPersonDialog extends Dialog {

    private TableViewerPart tableViewerPart;

    private InputDataComposite inputDataComposite;

    private CheckLineComposite swtDoneCheckLine;

    public CreateNewPersonDialog(TableViewerPart tableViewerPart) {
        super(Display.getCurrent().getActiveShell());
        this.tableViewerPart = tableViewerPart;
    }

    @Override
    protected Control createDialogArea(Composite parent) {

        Composite container = (Composite) super.createDialogArea(parent);
        inputDataComposite = new InputDataComposite(container, SWT.NONE);
        swtDoneCheckLine = new CheckLineComposite(container, SWT.NONE);

        return container;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Selection dialog");
    }

    @Override
    protected void okPressed() {

        String name = null;

        int group = 0;

        boolean swtDone = false;

        try {
            name = inputDataComposite.getNameTextField().getText();
            group = Integer.parseInt(inputDataComposite.getGroupTextField().getText());
            swtDone = swtDoneCheckLine.getSwtDoneButton().getSelection();

        } catch (NumberFormatException e) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Incorrect input",
                    "Your input was empty. Please, put the correct data");
        }

        if (Utils.isValidData(name, group)) {
            Person person = new Person(name, group, swtDone);
            tableViewerPart.add(person);
            super.okPressed();

        }
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}