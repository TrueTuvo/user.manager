package ua.com.rcp.zabara;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parts.TableViewerPart;

public class SelectionTester extends PropertyTester {

    private static final Logger LOG = LoggerFactory.getLogger(SelectionTester.class);

    public SelectionTester() {
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

        if ("hasNonEmptyTextSelection".equals(property)) {

            IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()

                    .findView(TableViewerPart.ID);

            TableViewerPart tableViewerPart = null;

            if (viewPart != null) {

                try {
                    tableViewerPart = (TableViewerPart) viewPart;

                } catch (ClassCastException exception) {

                    LOG.warn("Some problem with  classes cast. Current method can return incorrect result");
                }
            }

            if (tableViewerPart.getCurrentPerson() != null) {

                return true;
            }
        }
        return false;
    }
}
