/*
 * Created on May 4, 2005
 */

package examples.interfaceEditor.indexer;

import java.io.InputStream;

import org.eclipse.core.resources.IProject;

import com.amdocs.studio.editors.widgets.AbstractToolTipListener;
import com.amdocs.studio.indexer.search.IMatch;
import com.amdocs.studio.indexer.search.SearchType;
import com.amdocs.studio.indexer.utils.Util;

import examples.interfaceEditor.core.Interface;
import examples.interfaceEditor.core.InterfaceModel;

/**
 * @author michaek
 */
public class InterfaceToolTipListener extends AbstractToolTipListener {

	private IProject project;
	private SearchType type;

	/**
	 * @param project
	 * @param fileExtension
	 */
	public InterfaceToolTipListener(IProject project, String fileExtension) {
		super(fileExtension);
		this.project = project;
		type = Util.getSearchTypeByExtension(fileExtension);
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.editors.widgets.AbstractToolTipListener#getRequestedObjectDesc(java.lang.String)
	 */
	protected String getRequestedObjectDesc(String name) {
		IMatch match = Util.findRequestedObject(project, name, type);
		return getInterfaceDescriptionForMatch(match);
	}

	/**
	 * @param match
	 * @return String
	 */
	private String getInterfaceDescriptionForMatch(IMatch match) {
		if (match == null) {
			return "";
		}
		try {
			InputStream in = Util.getInputStreamFromMatch(match);
			InterfaceModel interfaceModel = new InterfaceModel();
			interfaceModel.load(in);
			Interface lInterface = interfaceModel.getInterface();
			if (lInterface != null) {
				return lInterface.getDescription();
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}
}