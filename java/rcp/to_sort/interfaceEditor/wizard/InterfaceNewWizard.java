/*
 * Created on May 2, 2005
 */

package examples.interfaceEditor.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.amdocs.studio.editors.forms.ADEFormWizard;

import examples.ui.ExamplesPlugin;

/**
 * @author michaek
 */
public class InterfaceNewWizard extends ADEFormWizard {

	/**
	 * @param fileExtension
	 */
	public InterfaceNewWizard() {
		super("interface");
	}

	protected InterfaceWizardPage interfaceWizardPage;
	private InterfaceWizardModel interfaceWizardModel;

	public void addPages() {
		setWindowTitle("New Interface File");

		interfaceWizardModel = new InterfaceWizardModel(this);

		newWizardPage = new InterfaceNewWizardPage();
		addPage(newWizardPage);

		interfaceWizardPage = new InterfaceWizardPage(interfaceWizardModel);
		addPage(interfaceWizardPage);
	}

	/**
	 * @throws IOException
	 */
	protected InputStream openContentStream() throws IOException {
		StringWriter swriter = new StringWriter();
		PrintWriter writer = new PrintWriter(swriter);
		String contents = "";
		interfaceWizardModel.save(writer);
		writer.flush();
		swriter.close();
		contents = swriter.toString();
		return new ByteArrayInputStream(contents.getBytes());
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.forms.ADEFormWizard#getPluginId()
	 */
	protected String getPluginId() {
		return ExamplesPlugin.getDefault().getPluginId();
	}

}