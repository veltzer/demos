There are three ways a worker may stay alive:
- keep running code.
- keep being registered to 'onmessage'
- keep timers around.

A thread can die by:
- no registering 'onmessage' and timers and ending it's code.
- deregistering from both 'onmessage' and timers and ending it's code.
- calling close() explicitly.
- the parent calling worker.terminate() (bad practice although not that
	bad in javascript since there is no data to be left in unstable
	state but still there may be calls to the server, half processed
	jobs etc).

A few facts about workers:
- 'console.log' is not available in workers.
- it looks like onclose inside the worker does not work although some documentation
	says otherwise.
- a worker can catch it's own errors using onerror
- both chorme and firebug have nice interfaces to debug workers.

References:
http://www.w3.org/TR/2009/WD-workers-20090423/
https://developer.mozilla.org/En/Using_web_workers
https://developer.mozilla.org/en/XPCOM_Interface_Reference/nsIWorkerScope
