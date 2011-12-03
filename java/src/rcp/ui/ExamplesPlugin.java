
package examples.ui;

import com.amdocs.studio.utils.ADEPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class ExamplesPlugin extends ADEPlugin {

	public static final String IMAGES_PATH = "/icons/full/"; //$NON-NLS-1$

	public static final String NEWMETH_WIZ = IMAGES_PATH + "newmeth_wiz.gif"; //$NON-NLS-1$
	public static final String METH_DEF = IMAGES_PATH + "methdef_obj.gif"; //$NON-NLS-1$
	public static final String METH_PRI = IMAGES_PATH + "methpri_obj.gif"; //$NON-NLS-1$
	public static final String METH_PRO = IMAGES_PATH + "methpro_obj.gif"; //$NON-NLS-1$
	public static final String METH_PUB = IMAGES_PATH + "methpub_obj.gif"; //$NON-NLS-1$
	public static final String NEWINT_WIZ = IMAGES_PATH + "newint_wiz.gif"; //$NON-NLS-1$
	public static final String INT_EDITOR = IMAGES_PATH + "interface.gif"; //$NON-NLS-1$
	public static final String METH_EDITOR = IMAGES_PATH + "srcmeth.gif"; //$NON-NLS-1$
	public static final String FINAL = IMAGES_PATH + "final_co.gif"; //$NON-NLS-1$
	public static final String STATIC = IMAGES_PATH + "static_co.gif"; //$NON-NLS-1$
	public static final String ABSTRACT = IMAGES_PATH + "abstract_co.gif"; //$NON-NLS-1$

	//The shared instance.
	private static ExamplesPlugin plugin;

	/**
	 * The constructor.
	 */
	public ExamplesPlugin() {
		super("com.amdocs.studio.examples", "examples.ui.messages"); //$NON-NLS-1$  //$NON-NLS-2$
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.amdocs.studio.utils.ADEPlugin#createImageDescriptors()
	 */
	protected void createImageDescriptors() {
		createImageDescriptor(NEWMETH_WIZ);
		createImageDescriptor(METH_DEF);
		createImageDescriptor(METH_PRI);
		createImageDescriptor(METH_PRO);
		createImageDescriptor(METH_PUB);
		createImageDescriptor(NEWINT_WIZ);
		createImageDescriptor(INT_EDITOR);
		createImageDescriptor(METH_EDITOR);
		createImageDescriptor(FINAL);
		createImageDescriptor(STATIC);
		createImageDescriptor(ABSTRACT);
	}

	/**
	 * Returns the shared instance.
	 * @return ExamplesPlugin
	 */
	public static ExamplesPlugin getDefault() {
		return plugin;
	}

}