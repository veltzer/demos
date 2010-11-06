<?php
/*
	The purpose is to show an object that cannot be inherited from
*/

final class A {
}
// next lines will produce compilation error as class C is final
// and cannot be extended
//class B extends A {
//}
?>
