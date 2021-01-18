package swt.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Composite, what will be added for operations over person
 * 
 * @author SZabara
 *
 */
public class CrudButtonsComposite extends Composite {

    private final Button newPersonButton;

    private final Button savePersonButton;

    private final Button deletePersonButton;

    private final Button resetPersonButton;

    public CrudButtonsComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout());
        newPersonButton = new Button(this, SWT.PUSH);
        newPersonButton.setText("New");

        savePersonButton = new Button(this, SWT.PUSH);
        savePersonButton.setText("Save");

        deletePersonButton = new Button(this, SWT.PUSH);
        deletePersonButton.setText("Delete");

        resetPersonButton = new Button(this, SWT.PUSH);
        resetPersonButton.setText("Reset");
    }

    /**
     * returns newPersonButton of this object
     * 
     * @return newPersonButton
     */
    public Button getNewPersonButton() {
        return newPersonButton;
    }

    /**
     * returns savePersonButton of this object
     * 
     * @return savePersonButton
     */
    public Button getSavePersonButton() {
        return savePersonButton;
    }

    /**
     * returns deletePersonButton of this object
     * 
     * @return deletePersonButton
     */
    public Button getDeletePersonButton() {
        return deletePersonButton;
    }

    /**
     * returns resetPersonButton of this object
     * 
     * @return resetPersonButton
     */
    public Button getResetPersonButton() {
        return resetPersonButton;
    }
}
