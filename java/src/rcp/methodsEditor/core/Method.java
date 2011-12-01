/*
 * Created on Apr 17, 2005
 */

package examples.methodsEditor.core;

import java.io.PrintWriter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.studio.editors.forms.ISourceElement;
import com.amdocs.studio.editors.forms.XMLDefaultHandler;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class Method implements ISourceElement {

	public final static String ACCESS_DEFAULT = "default";
	public final static String ACCESS_PRIVATE = "private";
	public final static String ACCESS_PROTECTED = "protected";
	public final static String ACCESS_PUBLIC = "public";

	public static final String XML_NODE_METHOD = "method";
	public static final String XML_ATTRIBUTE_NAME = "name";
	public static final String XML_ATTRIBUTE_ACCESS = "access";
	public static final String XML_ATTRIBUTE_ABSTRACT = "abstract";
	public static final String XML_ATTRIBUTE_STATIC = "static";
	public static final String XML_ATTRIBUTE_FINAL = "final";

	private String name;
	private String access;
	private boolean isFinal;
	private boolean isAbstract;
	private boolean isStatic;
	private ReturnValue returnValue;

	private int offset;
	private int length;

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReturnValue getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(ReturnValue returnValue) {
		this.returnValue = returnValue;
	}

	public String getAccess() {
		return (access != null) ? access : ACCESS_DEFAULT;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ISourceElement#getOffset()
	 */
	public int getOffset() {
		return offset;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ISourceElement#setOffset(int)
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ISourceElement#getLength()
	 */
	public int getLength() {
		return length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ISourceElement#setLength(int)
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ISourceElement#getXMLTagName()
	 */
	public String getXMLTagName() {
		return XML_NODE_METHOD;
	}

	public Image getImage() {
		ImageDescriptor d = null;

		if (access == null || access.equals(ACCESS_DEFAULT)) {
			d = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.METH_DEF);
		} else if (access.equals(ACCESS_PRIVATE)) {
			d = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.METH_PRI);
		} else if (access.equals(ACCESS_PROTECTED)) {
			d = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.METH_PRO);
		} else if (access.equals(ACCESS_PUBLIC)) {
			d = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.METH_PUB);
		}

		if (isFinal) {
			ImageDescriptor over = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.FINAL);
			d = ExamplesPlugin.getDefault().createOverlayIconLeftBottom(d, over);
		}
		if (isStatic) {
			ImageDescriptor over = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.STATIC);
			d = ExamplesPlugin.getDefault().createOverlayIconLeftTop(d, over);
		}
		if (isAbstract) {
			ImageDescriptor over = ExamplesPlugin.getDefault().getImageDescriptor(ExamplesPlugin.ABSTRACT);
			d = ExamplesPlugin.getDefault().createOverlayIconRightTop(d, over);
		}

		return ExamplesPlugin.getDefault().getImage(d);
	}

	/**
	 * @param methodNode
	 */
	public void parse(Node methodNode) {
		setName(XMLDefaultHandler.getNodeAttribute(methodNode, XML_ATTRIBUTE_NAME));
		setAccess(XMLDefaultHandler.getNodeAttribute(methodNode, XML_ATTRIBUTE_ACCESS));
		setAbstract(XMLDefaultHandler.getBooleanNodeAttribute(methodNode, XML_ATTRIBUTE_ABSTRACT));
		setStatic(XMLDefaultHandler.getBooleanNodeAttribute(methodNode, XML_ATTRIBUTE_STATIC));
		setFinal(XMLDefaultHandler.getBooleanNodeAttribute(methodNode, XML_ATTRIBUTE_FINAL));

		NodeList children = methodNode.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				String tag = child.getNodeName().toLowerCase();
				if (tag.equals(ReturnValue.XML_NODE_RETURN_TYPE)) {
					returnValue = new ReturnValue();
					returnValue.parse(child);
				}
			}
		}
	}

	/**
	 * @param indent
	 * @param writer
	 */
	public void write(String indent, PrintWriter writer) {
		writer.print(indent + "<" + XML_NODE_METHOD); //$NON-NLS-1$
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, XML_ATTRIBUTE_NAME, getName());
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, XML_ATTRIBUTE_ABSTRACT, String
			.valueOf(isAbstract()));
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, XML_ATTRIBUTE_STATIC, String.valueOf(isStatic()));
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, XML_ATTRIBUTE_FINAL, String.valueOf(isFinal()));
		MethodsModel.writeIfDefined(MethodsModel.SPACE, writer, XML_ATTRIBUTE_ACCESS, getAccess());
		writer.print(">"); //$NON-NLS-1$
		writer.println();

		if (returnValue != null) {
			returnValue.write(indent + MethodsModel.INDENT, writer);
		}
		writer.print(indent + "</" + XML_NODE_METHOD + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		writer.println();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}