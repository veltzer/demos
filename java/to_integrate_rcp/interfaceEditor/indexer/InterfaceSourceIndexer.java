/*
 * Created on May 4, 2005
 */

package examples.interfaceEditor.indexer;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;

import com.amdocs.studio.internal.core.index.IDocument;
import com.amdocs.studio.internal.core.search.indexing.AbstractIndexer;
import com.amdocs.studio.resources.api.ModuleWorkspace;
import com.amdocs.studio.resources.api.ResourcesManager;

import examples.interfaceEditor.core.Interface;
import examples.interfaceEditor.core.InterfaceModel;

/**
 * @author michaek
 */
public class InterfaceSourceIndexer extends AbstractIndexer {

	private static final String[] FILE_TYPES = new String[]{InterfaceIndexerService.INTERFACE_EXTENSION};
	private IFile resourceFile;
	private ModuleWorkspace moduleWorkspace;

	/**
	 * @param resourceFile
	 */
	public InterfaceSourceIndexer(IFile resourceFile) {
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
				InterfaceModel interfaceModel = new InterfaceModel();
				interfaceModel.load(inputStream);

				String[] result = initNameLocationModule(document, moduleWorkspace);
				String name = result[0];
				String moduleVersion = result[2];
				addDeclaration(name, moduleVersion, new String[0], InterfaceIndexerService.INTERFACE_TYPE);
				Interface lInterface = interfaceModel.getInterface();
				if (lInterface != null) {
					String[] attr = new String[0];
					addReferance(lInterface.getExtendedInterfaceName(), moduleVersion, attr,
						InterfaceIndexerService.INTERFACE_TYPE);
				}
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