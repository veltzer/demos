/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.core;

import java.io.PrintWriter;

import org.w3c.dom.Node;

import com.amdocs.studio.editors.forms.XMLDefaultHandler;

/**
 * @author michaek
 */
public class ReturnValue {

	public static final String XML_NODE_RETURN_TYPE = "return-type";
	public static final String XML_ATTRIBUTE_ARRAY = "array";
	public static final String XML_ATTRIBUTE_TYPE = "type";

	private String type;
	private boolean isArray;

	public boolean isArray() {
		return isArray;
	}

	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param node
	 */
	public void parse(Node node) {
		setType(XMLDefaultHandler.getNodeAttribute(node, XML_ATTRIBUTE_TYPE));
		setArray(XMLDefaultHandler.getBooleanNodeAttribute(node, XML_ATTRIBUTE_ARRAY));
	}

	/**
	 * @param indent
	 * @param writer
	 */
	public void write(String indent, PrintWriter writer) {
		writer.print(indent + "<" + ReturnValue.XML_NODE_RETURN_TYPE); //$NON-NLS-1$
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, ReturnValue.XML_ATTRIBUTE_TYPE, getType());
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, ReturnValue.XML_ATTRIBUTE_ARRAY, String
			.valueOf(isArray()));
		writer.print(">"); //$NON-NLS-1$
		writer.println();
	}
}