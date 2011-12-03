/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.core;

import java.io.PrintWriter;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.studio.editors.forms.XMLDefaultHandler;

/**
 * @author michaek
 */
public class Interface {

	public static final String XML_NODE_INTERFACE = "interface"; //$NON-NLS-1$
	public static final String XML_NODE_DESCRIPTION = "description"; //$NON-NLS-1$
	public static final String XML_ATTRIBUTE_EXTENDS = "extends"; //$NON-NLS-1$

	private String extendedInterfaceName;
	private String description;

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		if (description == null) {
			return "";
		}
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the name of the extended Interface.
	 */
	public String getExtendedInterfaceName() {
		if (extendedInterfaceName == null) {
			return "";
		}
		return extendedInterfaceName;
	}

	/**
	 * @param extendedInterfaceName The the name of the extended Interface to set.
	 */
	public void setExtendedInterfaceName(String extendedInterfaceName) {
		this.extendedInterfaceName = extendedInterfaceName;
	}

	/**
	 * @param node
	 */
	public void parse(Node node) {
		NodeList children = node.getChildNodes();

		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				String tag = child.getNodeName().toLowerCase();
				if (tag.equals(XML_NODE_INTERFACE)) {
					setExtendedInterfaceName(XMLDefaultHandler.getNodeAttribute(child, XML_ATTRIBUTE_EXTENDS));
					NodeList children1 = child.getChildNodes();
					for (int j = 0; j < children1.getLength(); j++) {
						Node child1 = children1.item(j);
						tag = child1.getNodeName().toLowerCase();
						if (tag.equals(XML_NODE_DESCRIPTION)) {
							Node firstChild = child1.getFirstChild();
							if (firstChild != null) {
								description = XMLDefaultHandler.getNormalizedText(firstChild.getNodeValue());
							}
						}
					}
				}
			}
		}
	}

	private void writeIfDefined(String indent, PrintWriter writer, String attName, String attValue) {
		if (attValue == null || attValue.trim().length() == 0)
			return;
		writer.print(indent + attName + "=\"" + attValue + "\""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @param indent
	 * @param writer
	 */
	public void write(String indent, PrintWriter writer) {
		writer.print(indent + "<" + XML_NODE_INTERFACE); //$NON-NLS-1$
		writeIfDefined(InterfaceModel.SPACE, writer, XML_ATTRIBUTE_EXTENDS, getExtendedInterfaceName());
		writer.print(">"); //$NON-NLS-1$
		writer.println();
		writeDescription(indent + InterfaceModel.INDENT, writer);
		writer.print(indent + "</" + XML_NODE_INTERFACE + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.println();
	}

	/**
	 * @param indent
	 * @param writer
	 */
	private void writeDescription(String indent, PrintWriter writer) {
		writer.print(indent + "<" + XML_NODE_DESCRIPTION + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.println();
		writer.print(indent + getDescription());
		writer.println();
		writer.print(indent + "</" + XML_NODE_DESCRIPTION + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.println();
	}

}