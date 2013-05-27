package com.example.addressbook.view.views;

import java.util.regex.Pattern;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;

import com.example.addressbook.view.ViewPlugin;
import com.example.addressbook.view.views.model.Company;
import com.example.addressbook.view.views.model.Person;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider. <p> The view uses a label provider to define
 * how model objects should be presented in the view. Each view can present the
 * same model objects using different labels and icons, if needed.
 * Alternatively, a single label provider can be shared between views in order
 * to ensure that objects of the same type are presented in the same way
 * everywhere. <p>
 */

public class AddressBookBrowser extends ViewPart {
	private TableViewer viewer;
	private ViewerFilter filter;
	// Actions:
	private Action deleteAction;
	private Action editAction;
	private Action newAction;
	private Action showCompanies;
	private Action showFriends;

	private boolean nameSortAscend = true;
	private boolean numberSortAscend = true;

	private ITableLabelProvider labelProvider;

	/**
	 * The constructor.
	 */
	public AddressBookBrowser() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);

		// Construct the table
		viewer.getTable().setHeaderVisible(true);
		TableColumn nameColumn = new TableColumn(viewer.getTable(), SWT.LEFT);
		nameColumn.setText(Messages.getString("AddressBookBrowser.name")); //$NON-NLS-1$
		nameColumn.setWidth(200);

		TableColumn numberColumn = new TableColumn(viewer.getTable(), SWT.RIGHT);
		numberColumn.setText(Messages.getString("AddressBookBrowser.number")); //$NON-NLS-1$
		numberColumn.setWidth(100);

		nameColumn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.setSorter(new AddressBookSorter(
						AddressBookTableLabelProvider.NAME_COLUMN,
						nameSortAscend, labelProvider));
				nameSortAscend = !nameSortAscend;
				numberSortAscend = false;
			}
		});

		numberColumn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.setSorter(new AddressBookSorter(
						AddressBookTableLabelProvider.NUMBER_COLUMN,
						numberSortAscend, labelProvider));
				numberSortAscend = !numberSortAscend;
				nameSortAscend = false;
			}
		});

		viewer.setColumnProperties(new String[] {
				"name", "number"}); //$NON-NLS-1$ //$NON-NLS-2$
		viewer.setUseHashlookup(true);

		CellEditor[] editors = new CellEditor[2];
		TextCellEditor nameEditor = new TextCellEditor(viewer.getTable());
		((Text) nameEditor.getControl()).setTextLimit(50);
		TextCellEditor numberEditor = new TextCellEditor(viewer.getTable());
		((Text) numberEditor.getControl()).setTextLimit(15);
		((Text) numberEditor.getControl())
				.addVerifyListener(new VerifyListener() {
					public void verifyText(VerifyEvent e) {
						e.doit = Pattern.matches("[0-9\\-]+", e.text); //$NON-NLS-1$
					}
				});

		editors[0] = nameEditor;
		editors[1] = numberEditor;
		viewer.setCellEditors(editors);
		viewer.setCellModifier(new AddressBookCellModifier(viewer));

		viewer.setContentProvider(new AddressBookContentProvider());
		labelProvider = new AddressBookTableLabelProvider();

		viewer.setLabelProvider(labelProvider);
		viewer.setSorter(new AddressBookSorter2());
		viewer.setInput(AddressBookManager.getInstance());

		filter = new AddressBookViewerFilter(true, true);
		viewer.addFilter(filter);

		makeActions();
		hookContextMenu();
		contributeToActionBars();

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				int numSelected = viewer.getTable().getSelectionCount();
				deleteAction.setEnabled(numSelected > 0);
				editAction.setEnabled(numSelected == 1);
			}
		});

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				if (editAction.isEnabled()) {
					editAction.run();
				}
			}
		});

		getViewSite().getActionBars().setGlobalActionHandler(
				ActionFactory.DELETE.getId(), deleteAction);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				AddressBookBrowser.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(deleteAction);
		manager.add(editAction);
		manager.add(newAction);
		manager.add(new Separator());
		manager.add(showFriends);
		manager.add(showCompanies);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(deleteAction);
		manager.add(editAction);
		manager.add(newAction);
		manager.add(new Separator());
		manager.add(showFriends);
		manager.add(showCompanies);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(deleteAction);
		manager.add(editAction);
		manager.add(newAction);
		manager.add(new Separator());
		manager.add(showFriends);
		manager.add(showCompanies);
	}

	private void makeActions() {
		deleteAction = new Action("Delete") {
			public void run() {
				TableItem[] selected = viewer.getTable().getSelection();
				for (int i = 0; i < selected.length; i++) {
					AddressBookManager.getInstance().remove(
							selected[i].getData());
				}
				viewer.refresh();
			}
		};
		deleteAction.setEnabled(!viewer.getSelection().isEmpty());
		deleteAction.setToolTipText(Messages
				.getString("AddressBookBrowser.delete.tooltip")); //$NON-NLS-1$
		deleteAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		editAction = new Action(Messages.getString("AddressBookBrowser.edit")) { //$NON-NLS-1$
			public void run() {
				TableItem[] items = viewer.getTable().getSelection();
				viewer.editElement(items[0].getData(), 1);
			}
		};
		editAction.setEnabled(viewer.getTable().getSelectionCount() == 1);
		editAction.setToolTipText(Messages
				.getString("AddressBookBrowser.edit.tooltip")); //$NON-NLS-1$
		editAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT));

		newAction = new Action(Messages.getString("AddressBookBrowser.new")) { //$NON-NLS-1$
			public void run() {
				boolean newCompany = MessageDialog
						.openQuestion(viewer.getControl().getShell(),
								Messages.getString("AddressBookBrowser.title"), //$NON-NLS-1$
								Messages.getString("AddressBookBrowser.create.question")); //$NON-NLS-1$
				Object newRecord;
				if (newCompany) {
					newRecord = new Company(
							Messages.getString("AddressBookBrowser.company.default"), ""); //$NON-NLS-1$ //$NON-NLS-2$
					AddressBookManager.getInstance().addCompany(
							(Company) newRecord);
				} else {
					newRecord = new Person(
							Messages.getString("AddressBookBrowser.friend.default.1"), Messages.getString("AddressBookBrowser.friend.default.2"), ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					AddressBookManager.getInstance().addFriend(
							(Person) newRecord);
				}

				viewer.refresh();
				viewer.editElement(newRecord, 0);
			}
		};
		newAction.setToolTipText(Messages
				.getString("AddressBookBrowser.new.tooltip")); //$NON-NLS-1$
		newAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));

		class ToggleFilterAction extends Action {
			ToggleFilterAction(String text) {
				super(text, SWT.TOGGLE);
			}

			public void run() {
				try {
					viewer.getTable().setRedraw(false);
					if (filter != null) {
						viewer.removeFilter(filter);
					}
					filter = new AddressBookViewerFilter(
							showCompanies.isChecked(), showFriends.isChecked());
					viewer.addFilter(filter);
				} finally {
					viewer.getTable().setRedraw(true);
				}
			}
		}

		showCompanies = new ToggleFilterAction(
				Messages.getString("AddressBookBrowser.show.companies")); //$NON-NLS-1$
		showCompanies.setImageDescriptor(ViewPlugin.getCompanyIconDescriptor());
		showCompanies.setChecked(true);
		showCompanies.setToolTipText(Messages
				.getString("AddressBookBrowser.show.companies.tooltip")); //$NON-NLS-1$

		showFriends = new ToggleFilterAction(
				Messages.getString("AddressBookBrowser.show.friends")); //$NON-NLS-1$
		showFriends.setImageDescriptor(ViewPlugin.getFriendIconDescriptor());
		showFriends.setChecked(true);
		showFriends.setToolTipText(Messages
				.getString("AddressBookBrowser.show.friends.tooltip")); //$NON-NLS-1$
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
