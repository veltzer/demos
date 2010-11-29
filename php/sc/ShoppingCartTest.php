<?php
require_once("ShoppingCart.php");

$sc=new ShoppingCart();
$sc->printState();
$sc->Buy("baseball",4);
$sc->printState();
$sc->Buy("basketball",1);
$sc->printState();
$sc->CancelBuy("baseball",2);
$sc->printState();
$sc->CancelBuy("baseball",2);
$sc->printState();
?>
