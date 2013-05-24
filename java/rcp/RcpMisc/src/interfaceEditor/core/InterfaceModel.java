/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.core;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADESectionPart;
import com.amdocs.studio.editors.forms.XMLDefaultHandler;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceModel {

	//write constants
	public static final String XML_NODE_DOCUMENT = "Document-Node"; //$NON-NLS-1$
	public static final String INDENT = "    "; //$NON-NLS-1$
	public static final String SPACE = " "; //$NON-NLS-1$

	//change properties
	public final static String EXTEND_SELECTED = "extend_selected"; //$NON-NLS-1$
	public final static String EXTEND_NAME_CHANGED = "extend_name_changed"; //$NON-NLS-1$

	//validation properties
	public final static String VALIDATE_EXTENDS = "validate_extends"; //$NON-NLS-1$
	public final static String VALIDATE_DESCRIPTION = "validate_description"; //$NON-NLS-1$

	private ADEFormEditor editor;
	private List listeners;

	private boolean stale;

	private Interface fInterface;
	private boolean extendSelected;

	/**
	 * @param editor
	 */
	public InterfaceModel(ADEFormEditor editor) {
		this();
		this.editor = editor;
	}

	public InterfaceModel() {
		fInterface = new Interface();
		fInterface = new Interface();
		listeners = new ArrayList();
	}

	public void load(InputStream stream) {
		try {
			stale = true;

			SAXParser parser = getSAXParser();
			XMLDefaultHandler handler = new XMLDefaultHandler();
			parser.setProperty("http://xml.org/sax/properties/lexical-handler", handler); //$NON-NLS-1$
			parser.parse(stream, handler);
			processDocument(handler.getDocument());

			if (fInterface.getExtendedInterfaceName().length() > 0) {
				extendSelected = true;
			}

			stale = false;
		} catch (Exception e) {
			ExamplesPlugin.getDefault().logError(e);
		}
	}

	private void processDocument(Document doc) {
		Node rootNode = doc.getDocumentElement();
		fInterface.parse(rootNode);
	}

	private SAXParser getSAXParser() throws Exception {
		return SAXParserFactory.newInstance().newSAXParser();
	}

	/**
	 * @param lInterface
	 */
	void setInterface(Interface lInterface) {
		fInterface = lInterface;
	}

	/**
	 * @return Returns the fInterface.
	 */
	public Interface getInterface() {
		return fInterface;
	}

	/**
	 * 
	 */
	public void extendsSelected() {
		Boolean oldValue = new Boolean(extendSelected);
		extendSelected = !extendSelected;
		Boolean newValue = new Boolean(extendSelected);
		if (!extendSelected) {
			fInterface.setExtendedInterfaceName("");
		}
		setDirty();
		firePropertyChange(new PropertyChangeEvent(fInterface, EXTEND_SELECTED, oldValue, newValue));
	}

	public void setExtendedInterfaceName(String newValue) {
		String oldValue = fInterface.getExtendedInterfaceName();
		fInterface.setExtendedInterfaceName(newValue);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(fInterface, EXTEND_NAME_CHANGED, oldValue, newValue));
	}

	public void setDescription(String newValue) {
		fInterface.setDescription(newValue);
		setDirty();
	}

	private void setDirty() {
		if (editor != null && stale == false)
			editor.setDirty(true);
	}

	public void addPropertyChangedListener(IPropertyChangeListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	public void removePropertyChangedListener(IPropertyChangeListener listener) {
		if (listeners.contains(listener))
			listeners.remove(listener);
	}

	/**
	 * @param event
	 */
	private void firePropertyChange(PropertyChangeEvent event) {
		if (!stale) {
			for (Iterator iter = listeners.iterator(); iter.hasNext();) {
				IPropertyChangeListener listener = (IPropertyChangeListener) iter.next();
				listener.propertyChange(event);
			}
		}
	}

	/**
	 * @param writer
	 */
	public void save(PrintWriter writer) {
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
		writer.print("" + "<" + XML_NODE_DOCUMENT + ">"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		writer.println();
		fInterface.write("" + INDENT, writer); //$NON-NLS-1$
		writer.println("" + "</" + XML_NODE_DOCUMENT + ">"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * @param property
	 * @return IStatus[]
	 */
	public IStatus[] validate(String property) {
		if (property.equals(VALIDATE_EXTENDS)) {
			if (extendSelected && fInterface.getExtendedInterfaceName().length() == 0) {
				String message = ExamplesPlugin.getDefault().getString("extension.button.error.message"); //$NON-NLS-1$
				return new IStatus[]{createStatusError(message)};
			}
		} else if (property.equals(VALIDATE_DESCRIPTION)) {
			if (fInterface.getDescription().length() == 0) {
				String message = ExamplesPlugin.getDefault().getString("no.description.error.message"); //$NON-NLS-1$
				return new IStatus[]{createStatusError(message)};
			}
		}
		return new IStatus[0];
	}

	private IStatus createStatusError(String message) {
		if (editor != null) {
			return editor.createADEStatusError(message, -1, -1, -1);
		}
		return ADESectionPart.createStatusError(message);
	}

}