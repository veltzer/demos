package org.meta.rcp.myserver;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.model.IModuleResourceDelta;
import org.eclipse.wst.server.core.model.ServerBehaviourDelegate;

public class ServerBehaviour extends ServerBehaviourDelegate {

	/**
	 * This is probably a hook to handle changes to server configuration. Set
	 * the server to restart here...
	 */
	@Override
	public void handleResourceChange() {
		super.handleResourceChange();
		trace("resource has changed");

		Iterator<?> iterator = getAllModules().iterator();
		while (iterator.hasNext()) {
			IModule[] module = (IModule[]) iterator.next();
			IModuleResourceDelta[] delta = getPublishedResourceDelta(module);
			if (delta == null || delta.length == 0) {
				continue;
			}
			trace("in here");
		}
	}

	private IProcess process;

	private static final String ERR_STRING1 = "cannot stop stopped";
	private static final String ERR_STRING2 = "cannot stop process";

	@Override
	public void stop(boolean force) {
		trace("stop");
		if (getServer().getServerState() == IServer.STATE_STOPPED) {
			throw new RuntimeException(ERR_STRING1);
		}
		setServerState(IServer.STATE_STOPPING);
		try {
			process.terminate();
		} catch (DebugException e) {
			throw new RuntimeException(ERR_STRING2);
		}
		setServerState(IServer.STATE_STOPPED);
	}

	@Override
	public IStatus publish(int kind, IProgressMonitor monitor) {
		trace("publish");
		return super.publish(kind, monitor);
	}

	@Override
	public void publish(int kind, List<IModule[]> modules,
			IProgressMonitor monitor, IAdaptable info) throws CoreException {
		super.publish(kind, modules, monitor, info);
		trace("publish(int, list<IModule>");

		for (IModule[] module : modules) {
			IModuleResourceDelta[] delta = getPublishedResourceDelta(module);
			for (IModuleResourceDelta del : delta) {
				trace(del.getModuleRelativePath().toFile().getName());
			}
		}
	}

	private void trace(String string) {
		System.out.println(string);
	}

	public void setServerStatePub(int stateStarted) {
		setServerState(stateStarted);
	}

	public void setProcess(IProcess iProcess) {
		process = iProcess;
	}

}
