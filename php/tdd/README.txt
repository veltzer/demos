running php unit tests...
=========================

from the command line:
======================
- Your main tool is a small executable called phpunit(1) which comes with
the installation of the phpunit framework. On UNIX systems it is in your
path automatically, on Windows you may have to do some black magic.
BTW: phpunit in itself is written in php and uses the ability to run php
on the command line...

- You need to run it one test at a time (phpunit(1) is stupid that way...).

- Just give the name of the class you want to run or the full name of the file...
	phpunit ArrayTest
	or
	phpunit ArrayTest.php

- This is one of the reasons that it is customary to name all of your unit test
	under some naming convention (FooTest.php) -> so that automatic scripts
	for testing could find all the test and run them one at a time and there
	will be no need to maintain a central list of all the tests in the system.

- Example of output when test goes well:
	========== SNIP =============
mark@cantor:~/links/demos/php/agile$ phpunit ArrayTest
PHPUnit 3.4.5 by Sebastian Bergmann.

..

Time: 0 seconds, Memory: 4.00Mb

OK (2 tests, 2 assertions)
	========== SNIP =============

- Example of output when test goes bad:
	========== SNIP =============
mark@cantor:~/links/demos/php/agile$ phpunit ArrayTest
PHPUnit 3.4.5 by Sebastian Bergmann.

.F

Time: 0 seconds, Memory: 4.00Mb

There was 1 failure:

1) ArrayTest::testArrayContainsAnElement
Failed asserting that <integer:1> matches expected <integer:2>.

/mnt/sdd1/mark/git/branches/demos/php/agile/ArrayTest.php:18

FAILURES!
Tests: 2, Assertions: 2, Failures: 1.
	========== SNIP =============

- you can add the --verbose flag to get some more info...
The difference is not that great:
	========== SNIP =============
mark@cantor:~/links/demos/php/agile$ phpunit --verbose ArrayTest.php
PHPUnit 3.4.5 by Sebastian Bergmann.

ArrayTest
..

Time: 0 seconds, Memory: 4.00Mb

OK (2 tests, 2 assertions)
	========== SNIP =============
mark@cantor:~/links/demos/php/agile$ phpunit ArrayTest.php
PHPUnit 3.4.5 by Sebastian Bergmann.

..

Time: 1 second, Memory: 4.00Mb

OK (2 tests, 2 assertions)
	========== SNIP =============
lets test in case of errors...
mark@cantor:~/links/demos/php/agile$ phpunit ArrayTest.php
PHPUnit 3.4.5 by Sebastian Bergmann.

.F

Time: 0 seconds, Memory: 4.00Mb

There was 1 failure:

1) ArrayTest::testArrayContainsAnElement
Failed asserting that <integer:1> matches expected <integer:2>.

/mnt/sdd1/mark/git/branches/demos/php/agile/ArrayTest.php:18

FAILURES!
Tests: 2, Assertions: 2, Failures: 1.
	========== SNIP =============
mark@cantor:~/links/demos/php/agile$ phpunit --verbose ArrayTest.php
PHPUnit 3.4.5 by Sebastian Bergmann.

ArrayTest
.F

Time: 0 seconds, Memory: 4.00Mb

There was 1 failure:

1) ArrayTest::testArrayContainsAnElement
Failed asserting that <integer:1> matches expected <integer:2>.

/mnt/sdd1/mark/git/branches/demos/php/agile/ArrayTest.php:18

FAILURES!
Tests: 2, Assertions: 2, Failures: 1.
	========== SNIP =============

As you can see the only difference is printing the name of the currently running test...
