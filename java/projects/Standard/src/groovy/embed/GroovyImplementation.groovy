#!/usr/bin/groovy

/*
	A few notes about this example....

	- If you add more code ** except ** the next Tester class then the compilation
	will return the outer class instead of the 'Tester' class.
*/

package groovy.embed;

import groovy.embed.ExampleInterface;

public class Tester implements ExampleInterface {
	public int add(int a,int b) {
		return a+b;
	}
}
