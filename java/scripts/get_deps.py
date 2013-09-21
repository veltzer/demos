#!/usr/bin/python3

import subprocess
import shutil
import os.path
import os # for mkdir

shutil.rmtree('static',True)
os.mkdir('static')

subprocess.check_call(['wget','http://maven.repository.paxle.net/org/jdesktop/jdic/jdic/0.9.5/jdic-0.9.5.jar','-O','static/jdic.jar'])

l=[
	'org.eclipse.core.commands_3.6.2.v20130123-162658.jar',
	'org.eclipse.core.contenttype_3.4.200.v20120523-2004.jar',
	'org.eclipse.core.filebuffers_3.5.200.v20120523-1310.jar',
	'org.eclipse.core.jobs_3.5.300.v20120912-155018.jar',
	'org.eclipse.core.resources_3.8.1.v20121114-124432.jar',
	'org.eclipse.core.runtime_3.8.0.v20120912-155025.jar',
	'org.eclipse.debug.core_3.7.100.v20120521-2012.jar',
	'org.eclipse.e4.ui.workbench3_0.12.0.v20120521-2329.jar',
	'org.eclipse.equinox.app_1.3.100.v20120522-1841.jar',
	'org.eclipse.equinox.common_3.6.100.v20120522-1841.jar',
	'org.eclipse.equinox.preferences_3.5.1.v20121031-182809.jar',
	'org.eclipse.equinox.registry_3.5.200.v20120522-1841.jar',
	'org.eclipse.jdt.launching_3.6.101.v20130111-183046.jar',
	'org.eclipse.jface_3.8.102.v20130123-162658.jar',
	'org.eclipse.jface.text_3.8.2.v20121126-164145.jar',
	'org.eclipse.osgi_3.8.2.v20130124-134944.jar',
	'org.eclipse.persistence.jpa.equinox.weaving_2.4.1.v20121003-ad44345.jar',
	'org.eclipse.swt_3.100.1.v4236b.jar',
	'org.eclipse.swt.gtk.linux.x86_3.100.1.v4236b.jar',
	'org.eclipse.text_3.5.200.v20120523-1310.jar',
	'org.eclipse.ui_3.104.0.v20121024-145224.jar',
	'org.eclipse.ui.editors_3.8.0.v20120523-1540.jar',
	'org.eclipse.ui.forms_3.5.200.v20120705-114351.jar',
	'org.eclipse.ui.ide_3.8.2.v20121106-165923.jar',
	'org.eclipse.ui.views_3.6.100.v20120705-114010.jar',
	'org.eclipse.ui.workbench_3.104.0.v20130204-164612.jar',
	'org.eclipse.ui.workbench.texteditor_3.8.0.v20120523-1310.jar',
	'org.eclipse.wst.server.core_1.4.100.v20130117_1140.jar',
]

epath='/home/mark/install/eclipse-jee-juno-SR2-linux-gtk/plugins'
for x in l:
	shutil.copyfile(os.path.join(epath,x),os.path.join('static',x))
