/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.amdocs.studio.editors.forms.XMLDefaultHandler;

/**
 * @author
 */
public class MethodsHandler extends XMLDefaultHandler {

	private MethodsModel model;
	private Map mapMethods;
	private Method fMethod;

	/**
	 * @param model
	 */
	public MethodsHandler(MethodsModel model) {
		super();
		this.model = model;
		mapMethods = new HashMap();
	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
	 *      org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes)
		throws SAXException {

		if (qName.equals(Method.XML_NODE_METHOD)) {
			fMethod = new Method();
			for (int i = 0; i < attributes.getLength(); i++) {
				String attrName = attributes.getQName(i);
				if (attrName.equals(Method.XML_ATTRIBUTE_NAME)) {
					fMethod.setName(attributes.getValue(i));
					break;
				}
			}
			try {
				fMethod.setOffset(getStartOffset(qName, model.getDocument()));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}

		super.startElement(uri, localName, qName, attributes);
	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(Method.XML_NODE_METHOD)) {
			try {
				int length = getElementLength(fMethod, model.getDocument(), fLocator.getLineNumber() - 1, fLocator
					.getColumnNumber());
				fMethod.setLength(length);
				mapMethods.put(fMethod.getName(), fMethod);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		super.endElement(uri, localName, qName);
	}

	/**
	 * @return Returns the mapMethods.
	 */
	public Map getMapMethods() {
		return mapMethods;
	}

}