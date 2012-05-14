/*
 * Created on May 4, 2005
 */

package examples.interfaceEditor.indexer;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import com.amdocs.studio.indexer.IIndexerService;
import com.amdocs.studio.indexer.search.ISearchScope;
import com.amdocs.studio.indexer.search.SearchEngine;
import com.amdocs.studio.indexer.search.SearchType;
import com.amdocs.studio.indexer.search.SearchableObject;
import com.amdocs.studio.internal.core.search.indexing.AbstractIndexer;

/**
 * @author michaek
 */
public class InterfaceIndexerService implements IIndexerService {

	public static final String INTERFACE_EXTENSION = "interface";
	public static final SearchType INTERFACE_TYPE = SearchType.createSearchType(INTERFACE_EXTENSION);

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.indexer.IIndexerService#getSourceIndexer(org.eclipse.core.resources.IFile,
	 *      org.eclipse.core.resources.IProject)
	 */
	public AbstractIndexer getSourceIndexer(IFile resourceFile, IProject project) {
		return new InterfaceSourceIndexer(resourceFile);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.indexer.IIndexerService#getSearchableObject(org.eclipse.core.runtime.IPath,
	 *      org.eclipse.core.resources.IResource)
	 */
	public SearchableObject getSearchableObject(IPath path, IResource resource) {
		return SearchableObject.createObjectFromFileName(path, resource);
	}

	public static List searchForInterfaceDeclaration(String interfaceName, ISearchScope scope) {
		return SearchEngine.searchForTypeDeclaration(interfaceName, INTERFACE_TYPE, scope);
	}

}