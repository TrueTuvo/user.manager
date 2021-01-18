package jface.elements.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import jface.elements.model.Person;
import parts.TableViewerPart;



/**
 * 
 * Every time, when user try to delete person, must confirm action in dialog window
 * 
 * @author SZabara
 */
public class DeletePersonDialog extends Dialog {

    private final TableViewerPart tableViewerPart;

    public DeletePersonDialog(TableViewerPart tableViewer) {
        super(Display.getCurrent().getActiveShell());
        this.tableViewerPart = tableViewer;
    }

    @Override
    protected Control createDialogArea(Composite parent) {

        Composite container = (Composite) super.createDialogArea(parent);
        Label label = new Label(container, SWT.FILL);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        label.setText(
                String.format("Do you really want to delete %s person?", tableViewerPart.getCurrentPerson().getName()));

        return container;

    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Delete Person");

    }

    @Override
    protected void okPressed() {
        Person person = (Person) tableViewerPart.getViewer().getStructuredSelection().getFirstElement();
        tableViewerPart.delete(person);
        super.okPressed();
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}
