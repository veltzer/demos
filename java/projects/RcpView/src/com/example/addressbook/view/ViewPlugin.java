package com.example.addressbook.view;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.example.addressbook.view.views.AddressBookManager;

/**
 * The main plugin class to be used in the desktop.
 */
public class ViewPlugin extends AbstractUIPlugin {

	// The shared instance.
	private static ViewPlugin plugin;
	private ImageDescriptor friendIconDesc;
	private ImageDescriptor companyIconDesc;
	private Image friendIcon;
	private Image companyIcon;

	/**
	 * The constructor.
	 */
	public ViewPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		friendIconDesc = getImageDescriptor("icons/Person.gif");
		companyIconDesc = getImageDescriptor("icons/Company.gif");
		friendIcon = friendIconDesc.createImage();
		companyIcon = companyIconDesc.createImage();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		AddressBookManager.save();
		friendIcon.dispose();
		companyIcon.dispose();
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ViewPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"com.example.addressbook.view", path);
	}

	public static Image getFriendIcon() {
		return plugin.friendIcon;
	}

	public static Image getCompanyIcon() {
		return plugin.companyIcon;
	}

	public static ImageDescriptor getFriendIconDescriptor() {
		return plugin.friendIconDesc;
	}

	public static ImageDescriptor getCompanyIconDescriptor() {
		return plugin.companyIconDesc;
	}
}
