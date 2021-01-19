package swt.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * Consist of the three composites : InputDataComposite, CrudButtonsComposite and CheckLineComposite, provides access to
 * the important elements
 * 
 * @author SZabara
 * 
 */
public class ComplexComposite extends Composite {

    private final Text nameTextField;

    private final Text groupTextField;

    private final Button swtCheckdone;

    private final Button newButton;

    private final Button saveButton;

    private final Button deleteButton;

    private final Button resetButton;

    public ComplexComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout(1, false));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        InputDataComposite inputDataComposite = new InputDataComposite(this, SWT.FILL);
        inputDataComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        nameTextField = inputDataComposite.getNameTextField();
        groupTextField = inputDataComposite.getGroupTextField();

        CheckLineComposite checkLineComposite = new CheckLineComposite(this, SWT.FILL);
        checkLineComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        swtCheckdone = checkLineComposite.getSwtDoneButton();

        CrudButtonsComposite crudButtonsComposite = new CrudButtonsComposite(this, SWT.FILL);
        crudButtonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        newButton = crudButtonsComposite.getNewPersonButton();
        saveButton = crudButtonsComposite.getSavePersonButton();
        deleteButton = crudButtonsComposite.getDeletePersonButton();
        resetButton = crudButtonsComposite.getResetPersonButton();
    }

    /**
     * returns nameTextField of this object
     * 
     * @return nameTextField
     */
    public Text getNameTextField() {
        return nameTextField;
    }

    /**
     * returns groupTextField of this object
     * 
     * @return groupTextField
     */
    public Text getGroupTextField() {
        return groupTextField;
    }

    /**
     * returns swtCheckdone of this object
     * 
     * @return swtCheckdone
     */
    public Button getSwtCheckdone() {
        return swtCheckdone;
    }

    /**
     * returns newButton of this object
     * 
     * @return newButton
     */
    public Button getNewButton() {
        return newButton;
    }

    /**
     * returns saveButton of this object
     * 
     * @return saveButton
     */
    public Button getSaveButton() {
        return saveButton;
    }

    /**
     * returns deleteButton of this object
     * 
     * @return deleteButton
     */
    public Button getDeleteButton() {
        return deleteButton;
    }

    /**
     * returns resetButton of this object
     * 
     * @return resetButton
     */
    public Button getResetButton() {
        return resetButton;
    }
}
