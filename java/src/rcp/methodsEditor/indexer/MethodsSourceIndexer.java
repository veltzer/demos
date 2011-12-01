/*
 * Created on May 4, 2005
 */

package examples.methodsEditor.indexer;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;

import com.amdocs.studio.internal.core.index.IDocument;
import com.amdocs.studio.internal.core.search.indexing.AbstractIndexer;
import com.amdocs.studio.resources.api.ModuleWorkspace;
import com.amdocs.studio.resources.api.ResourcesManager;

import examples.interfaceEditor.indexer.InterfaceIndexerService;
import examples.methodsEditor.core.MethodsModel;

/**
 * @author michaek
 */
public class MethodsSourceIndexer extends AbstractIndexer {

	private static final String[] FILE_TYPES = new String[]{MethodsIndexerService.METHODS_EXTENSION};
	private IFile resourceFile;
	private ModuleWorkspace moduleWorkspace;

	/**
	 * @param resourceFile
	 */
	public MethodsSourceIndexer(IFile resourceFile) {
		this.resourceFile = resourceFile;
		moduleWorkspace = ResourcesManager.getModuleWorkspaceInst();
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.internal.core.search.indexing.AbstractIndexer#getFileTypes()
	 */
	public String[] getFileTypes() {
		return FILE_TYPES;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.internal.core.search.indexing.AbstractIndexer#getResourceFile()
	 */
	public IFile getResourceFile() {
		return resourceFile;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.internal.core.search.indexing.AbstractIndexer#indexFile(com.amdocs.studio.internal.core.index.IDocument)
	 */
	protected void indexFile(IDocument document) {

		// Add the name of the file to the index
		registerDocument(document);

		boolean retVal = true;

		InputStream inputStream = document.getInputStream();
		if (inputStream != null) {

			try {
				MethodsModel methodsModel = new MethodsModel();
				methodsModel.load(inputStream);

				String[] result = initNameLocationModule(document, moduleWorkspace);
				String name = result[0];
				String moduleVersion = result[2];
				addDeclaration(name, moduleVersion, new String[0], MethodsIndexerService.METHODS_TYPE);
				String[] attr = new String[0];
				addReferance(methodsModel.getInterfaceName(), moduleVersion, attr,
					InterfaceIndexerService.INTERFACE_TYPE);
			} catch (Exception e) {
				retVal = false;
			}
		} else {
			retVal = false;
		}

		if (AbstractIndexer.VERBOSE) {
			if (!retVal)
				AbstractIndexer.verbose("PARSE FAILED " + resourceFile.getName().toString()); //$NON-NLS-1$
			else
				AbstractIndexer.verbose("PARSE SUCCEEDED " + resourceFile.getName().toString()); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.internal.core.index.IIndexer#setFileTypes(java.lang.String[])
	 */
	public void setFileTypes(String[] fileTypes) {}

}