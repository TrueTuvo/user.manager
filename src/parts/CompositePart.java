package parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import swt.elements.ComplexComposite;
import ua.com.rcp.zabara.Utils;


/**
 * 
 * View, which is responsible for displaying the user menu. This includes fields for editing data of the selected
 * Person, and CRUD buttons.
 * 
 * @author SZabara
 *
 */
public class CompositePart extends ViewPart {

    public static final String ID = "ua.com.rcp.zabara.view.composite";

    private ComplexComposite complexComposite;

    public ComplexComposite getComplexComposite() {
        return complexComposite;
    }

    @Override
    public void createPartControl(Composite parent) {
        complexComposite = new ComplexComposite(parent, SWT.NONE);
        IHandlerService service = (IHandlerService) getSite().getService(IHandlerService.class);
        complexComposite.getNewButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Utils.executeCommand(Utils.COMMAND_NEW, service);

            }
        });
        complexComposite.getSaveButton().addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                Utils.executeCommand(Utils.COMMAND_SAVE, service);

            }
        });

        complexComposite.getResetButton().addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                Utils.executeCommand(Utils.COMMAND_CANCEL, service);

            }
        });

        complexComposite.getDeleteButton().addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                Utils.executeCommand(Utils.COMMAND_DELETE, service);

            }
        });

    }

    @Override
    public void setFocus() {
        complexComposite.setFocus();

    }

}
