package swt.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Part of MainComposite. Responsible for show SWTDone flag
 * 
 * @author SZabara
 * 
 */
public class CheckLineComposite extends Composite {

    private final Button swtDoneButton;

    public CheckLineComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        Label swtDoneLabel = new Label(this, SWT.FILL);
        swtDoneLabel.setText("SWT task Done");

        swtDoneButton = new Button(this, SWT.CHECK | SWT.RIGHT);
        swtDoneButton.setOrientation(SWT.RIGHT_TO_LEFT);
    }

    /**
     * returns swtDoneButton of this object
     * 
     * @return swtDoneButton
     */
    public Button getSwtDoneButton() {
        return swtDoneButton;
    }
}
