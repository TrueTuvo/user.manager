package parts;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import jface.elements.model.CheckBoxLabelProvider;
import jface.elements.model.ModelProvider;
import jface.elements.model.Person;
import swt.elements.ComplexComposite;



/**
 * This class need for customize and control of table viewer
 * 
 * @author SZabara
 *
 */
public class TableViewerPart extends ViewPart {

    public static final String ID = "ua.test.rcp.zabara.view.table";

    private TableViewer viewer;

    /**
     * Shapes the composite and add TableViewer's object to the composite.
     * 
     * @param parent Parent composite of TableViewer
     */
    public void createPartControl(Composite parent) {
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);
        createViewer(parent);

        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                Person selectedPerson = (Person) viewer.getStructuredSelection().getFirstElement();
                IWorkbenchPage compositePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                IViewPart compositeViewPart = null;
                try {
                    compositeViewPart = compositePage.showView(CompositePart.ID);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
                CompositePart compositePart = (CompositePart) compositeViewPart;
                ComplexComposite mainComposite = compositePart.getComplexComposite();
                if (selectedPerson != null) {
                    mainComposite.getNameTextField().setText(selectedPerson.getName());
                    mainComposite.getGroupTextField().setText(String.valueOf(selectedPerson.getGroup()));
                    mainComposite.getSwtCheckdone().setSelection(selectedPerson.isSwtDone());
                }
            }
        });

    }

    /**
     * Creates and shapes the TableViewer's object.
     * 
     * @param parent Parent composite of TableViewer
     */
    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());

        viewer.setInput(ModelProvider.INSTANCE.getPersons());

        // Layout the viewer

        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);

    }

    /**
     * returns viewer of this object
     * 
     * @return viewer of this object
     */
    public TableViewer getViewer() {
        return viewer;
    }

    /**
     * This will create the columns for the table
     * 
     * @param parent Parent composite of TableViewer
     * @param viewer current TableViewer's object
     */
    private void createColumns(final Composite parent, final TableViewer viewer) {
        String[] titles = { "Name", "Group", "swtDone" };
        int[] bounds = { 100, 70, 60 };

        // First column is for the name
        TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Person p = (Person) element;
                return p.getName();
            }
        });
        // Second column is for the group
        col = createTableViewerColumn(titles[1], bounds[1], 1);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Person p = (Person) element;
                return String.valueOf(p.getGroup());
            }
        });
        // Third column is for the swtDone
        col = createTableViewerColumn(titles[2], bounds[2], 2);
        col.setLabelProvider(new CheckBoxLabelProvider(viewer) {
            @Override
            protected boolean isChecked(Object element) {
                return ((Person) element).isSwtDone();
            }
        });
    }

    /**
     * Create a new column for TableViewer object
     * 
     * @param title column's name
     * @param bound column's size
     * @param colNumber column's count number
     * @return done column
     */
    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * removes person from the Model Provider and refresh viewer
     * 
     * @param person object for removing
     */
    public void delete(Person person) {
        if (ModelProvider.INSTANCE.getPersons().contains(person)) {
            ModelProvider.INSTANCE.getPersons().remove(person);
            getViewer().refresh();
        }
    }

    /**
     * adds person to the Model Provider and refresh viewer
     * 
     * @param person object for adding
     */
    public void add(Person person) {
        ModelProvider.INSTANCE.getPersons().add(person);
        getViewer().refresh();
    }

    /**
     * returns selected person in Table Viewer
     * 
     * @return selected person in Table Viewer
     */
    public Person getCurrentPerson() {
        return (Person) getViewer().getStructuredSelection().getFirstElement();
    }
}