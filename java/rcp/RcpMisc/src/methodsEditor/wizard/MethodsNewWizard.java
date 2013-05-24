/*
 * Created on Apr 19, 2005
 */

package examples.methodsEditor.wizard;

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
public class MethodsNewWizard extends ADEFormWizard {

	protected MethodsWizardPage methodsPage;
	private MethodsWizardModel methodsWizardModel;

	/**
	 * @param fileExtension
	 */
	public MethodsNewWizard() {
		super("methods");
	}

	/**
	 * Adding the page to the wizard.
	 */
	public void addPages() {
		setWindowTitle("New Methods File");

		methodsWizardModel = new MethodsWizardModel(this);

		newWizardPage = new NewWizardPage();
		addPage(newWizardPage);

		methodsPage = new MethodsWizardPage(methodsWizardModel);
		addPage(methodsPage);

	}

	/**
	 * @throws IOException
	 */
	protected InputStream openContentStream() throws IOException {
		StringWriter swriter = new StringWriter();
		PrintWriter writer = new PrintWriter(swriter);
		String contents = "";
		methodsWizardModel.save(writer);
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