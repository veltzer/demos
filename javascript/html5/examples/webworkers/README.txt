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
- if the parent calls 'worker.terminate()' the workers 'close' method will
	not be called.

References:
https://developer.mozilla.org/En/Using_web_workers
https://developer.mozilla.org/en/XPCOM_Interface_Reference/nsIWorkerScope
