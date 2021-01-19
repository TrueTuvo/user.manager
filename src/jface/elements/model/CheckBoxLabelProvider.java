package jface.elements.model;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

/**
 * Emulates a native check box in a column label provider which is also centered on screen. This should also work well,
 * in case the field is editable.
 * 
 *
 */

public abstract class CheckBoxLabelProvider extends OwnerDrawLabelProvider {

    private static final String CHECKED_KEY = "CHECKED";

    private static final String UNCHECK_KEY = "UNCHECKED";

    private Image image;

    /**
     * Puts to Image register 2 shots
     * 
     * @param viewer need for getting current shell, that will be use for make shot
     */
    public CheckBoxLabelProvider(ColumnViewer viewer) {
        if (JFaceResources.getImageRegistry().getDescriptor(CHECKED_KEY) == null) {
            Shell shell = viewer.getControl().getShell();
            JFaceResources.getImageRegistry().put(UNCHECK_KEY, makeShot(shell, false));
            JFaceResources.getImageRegistry().put(CHECKED_KEY, makeShot(shell, true));
        }
    }

    /**
     * 
     * Creates one image
     * 
     * @param shell need for create new window only with one check button
     * @param type need for definition checked/unchecked state
     * @return image of checked/unchecked buttons state
     */
    private Image makeShot(Shell shell, boolean type) {
        Shell shellContainsImage = new Shell(shell, SWT.NO_TRIM);  
        Button buttonContainsImage = new Button(shellContainsImage, SWT.CHECK);
        buttonContainsImage.setSelection(type);
        Point bsize = buttonContainsImage.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        buttonContainsImage.setSize(bsize);
        buttonContainsImage.setLocation(0, 0);
        shellContainsImage.setSize(bsize);
        shellContainsImage.open();

        GC imageManager = new GC(buttonContainsImage);
        Image image = new Image(shell.getDisplay(), bsize.x, bsize.y);
        imageManager.copyArea(image, 0, 0);
        imageManager.dispose();

        shellContainsImage.close();

        return image;
    }

    /**
     * Create image depends on return of #isCheched
     */
    public Image getImage(Object element) {
        return JFaceResources.getImageRegistry().getDescriptor(isChecked(element) ? CHECKED_KEY : UNCHECK_KEY)
                .createImage();
    }

    @Override
    protected void paint(Event event, Object element) {

        image = getImage(element);
        if (image != null) {
            Rectangle bounds = ((TableItem) event.item).getBounds(event.index);
            Rectangle imgBounds = image.getBounds();
            bounds.width /= 2;
            bounds.width -= imgBounds.width / 2;
            bounds.height /= 2;
            bounds.height -= imgBounds.height / 2;

            int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
            int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

            event.gc.drawImage(image, x, y);
        }
    }

    /**
     * The method was add for override, when new object was created.
     * 
     * @param element
     * @return
     */
    protected abstract boolean isChecked(Object element);

    @Override
    protected void measure(Event event, Object element) {
    }
}
