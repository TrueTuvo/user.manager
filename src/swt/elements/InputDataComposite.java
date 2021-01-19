package swt.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ua.com.rcp.zabara.Utils;

/**
 * This composite responsible input data
 * 
 * @author SZabara
 */
public class InputDataComposite extends Composite {

    private final Text nameTextField;

    private final Text groupTextField;

    public InputDataComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout(2, true));
        setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Label nameLabel = new Label(this, SWT.FILL);
        nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        nameLabel.setText("Name ");
        
        nameTextField = new Text(this, SWT.FILL);
        nameTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        nameTextField.setToolTipText("Input can not be empty");
        nameTextField.addVerifyListener(new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {
                String oldInputValue = ((Text) e.widget).getText();
                String newSInputValue = oldInputValue.substring(0, e.start) + e.text + oldInputValue.substring(e.end);
                if (Utils.isName(newSInputValue)) {
                    e.doit = true;
                } else {
                    e.doit = false;
                }
            }
        });
        
        Label groupLabel = new Label(this, SWT.FILL);
        groupLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        groupLabel.setText("Group ");
        
        groupTextField = new Text(this, SWT.FILL);
        groupTextField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        groupTextField.setToolTipText("Group must be a number from 1 to 99");
        groupTextField.addVerifyListener(new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {
                String oldInputValue = ((Text) e.widget).getText();
                String newSInputValue = oldInputValue.substring(0, e.start) + e.text + oldInputValue.substring(e.end);
                if (newSInputValue.matches("[0-9]{1,2}") || newSInputValue.matches("")) {
                    e.doit = true;
                } else {
                    e.doit = false;
                }
            }
        });
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
     * returns nameTextField of this object
     * 
     * @return nameTextField
     */
    public Text getNameTextField() {
        return nameTextField;
    }

}
