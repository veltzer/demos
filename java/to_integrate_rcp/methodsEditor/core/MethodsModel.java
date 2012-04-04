/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.studio.editors.forms.ADEContext;
import com.amdocs.studio.editors.forms.ADEFormEditor;
import com.amdocs.studio.editors.forms.ADESectionPart;
import com.amdocs.studio.editors.forms.IModelChangeProvider;
import com.amdocs.studio.editors.forms.IModelChangedListener;
import com.amdocs.studio.editors.forms.IReconcilingParticipant;
import com.amdocs.studio.editors.forms.XMLDefaultHandler;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class MethodsModel implements IModelChangeProvider, IReconcilingParticipant {

	public static final String METHOD_ADDED = "method_added";
	public static final String METHOD_NAME_CHANGED = "method_changed";
	public static final String METHOD_REMOVED = "method_removed";
	public static final String METHOD_SELECTED = "method_selected";
	public static final String VALIDATE_METHODS = "validate_methods";
	public static final String VALIDATE_INTERFACE = "validate_interface";
	public static final String METHOD_ACCESS_CHANGED = "method_access_changed";
	public static final String METHOD_MODIFIER_CHANGED = "method_modifier_changed";
	public static final String INTERFACE_CHANGED = "interface_changed";

	public static final String INDENT = "    "; //$NON-NLS-1$
	public static final String SPACE = " "; //$NON-NLS-1$
	public static final String XML_NODE_METHODS = "list-methods";
	public static final String XML_ATTRIBUTE_INTERFACE = "interface";
	public static final String XML_NODE_RETURN_TYPE = "return-type";
	public static final String XML_ATTRIBUTE_ARRAY = "array";
	public static final String XML_ATTRIBUTE_TYPE = "type";
	//	public static final String TRUE = "true";

	private List methodList;
	private String interfaceName;
	private int selectedMethodIndex;

	private List listeners;
	private List modelChangedListeners;

	private ADEFormEditor editor;
	private ADEContext context;
	private IDocument fDocument;
	private MethodsHandler handler;

	private boolean stale;
	private boolean dirty;
	private boolean loaded;

	public MethodsModel(ADEFormEditor editor) {
		this();
		this.editor = editor;
		context = editor.getContext();
		fDocument = context.getDocument();
	}

	public MethodsModel() {
		methodList = new ArrayList();
		listeners = new ArrayList();
		modelChangedListeners = new ArrayList();
	}

	public void load(InputStream stream) {
		try {
			stale = true;

			SAXParser parser = getSAXParser();
			parser.setProperty("http://xml.org/sax/properties/lexical-handler", initMethodsHandler()); //$NON-NLS-1$
			parser.parse(stream, handler);

			if (!loaded) {
				processDocument(handler.getDocument());
			}
			updateOffsets();
			loaded = true;
			stale = false;
		} catch (Exception e) {
			e.printStackTrace();
			ExamplesPlugin.getDefault().logError(e);
		}
	}

	/**
	 * 
	 */
	private void updateOffsets() {
		Map mapMethodsOffsets = handler.getMapMethods();
		for (Iterator iter = methodList.iterator(); iter.hasNext();) {
			Method method = (Method) iter.next();
			Method lMethod = (Method) mapMethodsOffsets.get(method.getName());
			if (lMethod != null) {
				method.setOffset(lMethod.getOffset());
				method.setLength(lMethod.getLength());
			}
		}
	}

	private MethodsHandler initMethodsHandler() {
		handler = new MethodsHandler(this);
		return handler;
	}

	private void processDocument(Document doc) {
		Node rootNode = doc.getDocumentElement();
		//init (or reset) the list of methods
		methodList = new ArrayList();
		parseDocument(rootNode);
	}

	/**
	 * @param rootNode
	 */
	private void parseDocument(Node rootNode) {
		NodeList children = rootNode.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				String tag = child.getNodeName().toLowerCase();
				if (tag.equals(XML_NODE_METHODS)) {
					parseMethods(child);
				}
			}
		}
	}

	private void parseMethods(Node listMethodsNode) {
		setInterfaceName(XMLDefaultHandler.getNodeAttribute(listMethodsNode, XML_ATTRIBUTE_INTERFACE));
		NodeList children = listMethodsNode.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				String tag = child.getNodeName().toLowerCase();
				if (tag.equals(Method.XML_NODE_METHOD)) {
					Method method = new Method();
					method.parse(child);
					methodList.add(method);
				}
			}
		}
	}

	private SAXParser getSAXParser() throws Exception {
		return SAXParserFactory.newInstance().newSAXParser();
	}

	public void addMethod(Method method, Object source) {
		methodList.add(method);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(source, METHOD_ADDED, null, method));
		fireModelChanged();
	}

	public void removeMethod(Method method, Object source) {
		methodList.remove(method);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(source, METHOD_REMOVED, method, null));
		fireModelChanged();
	}

	public void methodSelected(Method method, Object source) {
		int index = methodList.indexOf(method);
		Integer newValue = new Integer(index);
		Integer oldValue = new Integer(selectedMethodIndex);
		selectedMethodIndex = index;
		firePropertyChange(new PropertyChangeEvent(source, METHOD_SELECTED, oldValue, newValue));
	}

	public Method[] getMethods() {
		return (Method[]) methodList.toArray(new Method[methodList.size()]);
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

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.IModelChangeProvider#addModelChangedListener(com.amdocs.studio.editors.forms.IModelChangedListener)
	 */
	public void addModelChangedListener(IModelChangedListener listener) {
		if (!modelChangedListeners.contains(listener))
			modelChangedListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.IModelChangeProvider#fireModelChanged()
	 */
	public void fireModelChanged() {
		for (Iterator iter = modelChangedListeners.iterator(); iter.hasNext();) {
			IModelChangedListener listener = (IModelChangedListener) iter.next();
			listener.modelChanged();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.IModelChangeProvider#removeModelChangedListener(com.amdocs.studio.editors.forms.IModelChangedListener)
	 */
	public void removeModelChangedListener(IModelChangedListener listener) {
		if (modelChangedListeners.contains(listener))
			modelChangedListeners.remove(listener);
	}

	public boolean isStale() {
		return stale;
	}

	//	public void setStale(boolean stale) {
	//		this.stale = stale;
	//	}

	private void setDirty() {
		dirty = true;
		if (editor != null && stale == false)
			editor.setDirty(true);
	}

	/**
	 * @param writer
	 */
	public void save(PrintWriter writer) {
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
		write("", writer); //$NON-NLS-1$
	}

	/**
	 * @param indent
	 * @param writer
	 */
	private void write(String indent, PrintWriter writer) {
		final String documentName = "Methods-Example";
		writer.print(indent + "<" + documentName + ">"); //$NON-NLS-1$
		writer.println();

		String indent2 = indent + INDENT;
		String indent3 = indent + INDENT + INDENT;

		if (getInterfaceName().length() == 0) {
			writer.print(indent2 + "<" + XML_NODE_METHODS + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			writer.print(indent2 + "<" + XML_NODE_METHODS); //$NON-NLS-1$
			writeIfDefined(SPACE, writer, XML_ATTRIBUTE_INTERFACE, getInterfaceName());
			writer.print(">"); //$NON-NLS-1$
		}
		writer.println();

		for (Iterator iter = methodList.iterator(); iter.hasNext();) {
			Method method = (Method) iter.next();
			method.write(indent3, writer);
		}

		writer.print(indent2 + "</" + XML_NODE_METHODS + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.println();

		writer.println(indent + "</" + documentName + ">"); //$NON-NLS-1$
	}

	public static void writeIfDefined(String indent, PrintWriter writer, String attName, String attValue) {
		if (attValue == null || attValue.trim().length() == 0)
			return;
		writer.print(indent + attName + "=\"" + attValue + "\""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void abstractSelected() {
		Method selectedMethod = (Method) methodList.get(selectedMethodIndex);
		boolean oldModifier = selectedMethod.isAbstract();
		boolean newModifier = !oldModifier;
		selectedMethod.setAbstract(newModifier);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(selectedMethod, METHOD_MODIFIER_CHANGED, new Boolean(
			oldModifier), new Boolean(newModifier)));
	}

	public void finalSelected() {
		Method selectedMethod = (Method) methodList.get(selectedMethodIndex);
		boolean oldModifier = selectedMethod.isFinal();
		boolean newModifier = !oldModifier;
		selectedMethod.setFinal(newModifier);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(selectedMethod, METHOD_MODIFIER_CHANGED, new Boolean(
			oldModifier), new Boolean(newModifier)));
	}

	public void staticSelected() {
		Method selectedMethod = (Method) methodList.get(selectedMethodIndex);
		boolean oldModifier = selectedMethod.isStatic();
		boolean newModifier = !oldModifier;
		selectedMethod.setStatic(newModifier);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(selectedMethod, METHOD_MODIFIER_CHANGED, new Boolean(
			oldModifier), new Boolean(newModifier)));
	}

	/**
	 * @param name
	 */
	public void changeMethodName(String name) {
		Method selectedMethod = (Method) methodList.get(selectedMethodIndex);
		String oldName = selectedMethod.getName();
		selectedMethod.setName(name);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(selectedMethod, METHOD_NAME_CHANGED, oldName, name));
		fireModelChanged();
	}

	public void setAccess(String access) {
		Method selectedMethod = (Method) methodList.get(selectedMethodIndex);
		String oldAccess = selectedMethod.getAccess();
		selectedMethod.setAccess(access);
		setDirty();
		firePropertyChange(new PropertyChangeEvent(selectedMethod, METHOD_ACCESS_CHANGED, oldAccess, access));
		fireModelChanged();
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.IReconcilingParticipant#reconciled(org.eclipse.jface.text.IDocument)
	 */
	public void reconciled(IDocument document) {
		if (!dirty) {
			return;
		}
		fDocument = document;
		try {
			load(getInputStream(document));
			dirty = false;
		} catch (Exception e) {
			ExamplesPlugin.getDefault().logError(e);
		}
	}

	protected InputStream getInputStream(IDocument document) throws UnsupportedEncodingException {
		String string = document.get();
		return new ByteArrayInputStream(string.getBytes(getCharset()));
	}

	public String getCharset() {
		return context.getCharset();
	}

	/**
	 * @return Returns the loaded.
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * @param property
	 * @return IStatus[]
	 */
	public IStatus[] validate(String property) {
		if (property.equals(VALIDATE_METHODS)) {
			if (methodList.size() == 0) {
				String message = ExamplesPlugin.getDefault().getString("no.method.error.message");
				return new IStatus[]{createStatusError(message)};
			}
		}
		if (property.equals(VALIDATE_INTERFACE)) {
			if (getInterfaceName().length() == 0) {
				String message = ExamplesPlugin.getDefault().getString("no.interface.error.message");
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

	/**
	 * @return IDocument
	 */
	public IDocument getDocument() {
		return fDocument;
	}

	public String getInterfaceName() {
		if (interfaceName == null) {
			return "";
		}
		return interfaceName;
	}

	public void setInterfaceName(String newValue) {
		String oldValue = interfaceName;
		this.interfaceName = newValue;
		setDirty();
		firePropertyChange(new PropertyChangeEvent(this, INTERFACE_CHANGED, oldValue, newValue));
	}
}
