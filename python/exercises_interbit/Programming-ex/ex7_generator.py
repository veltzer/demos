# Using a for loop
# ================

def apply_funcs_gen(funcs, x):
    """Generate results of functions applied to an argument.

    >>> def print_a(x):
    ...     print 'a'
    ...     return x
    >>> def print_b(x):
    ...     print 'b'
    ...     return x
    >>> for res in apply_funcs_gen([print_a, print_b], 42):
    ...     print res
    a
    42
    b
    42
    """
    for f in funcs:
        yield f(x)

import doctest
doctest.testmod()
