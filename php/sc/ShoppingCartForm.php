<?php
require_once("ShoppingCart.php");

session_start();

if(!isset($_SESSION["sc"])) {
	$sc=new ShoppingCart();
	$_SESSION["sc"]=$sc;
} else {
	$sc=$_SESSION["sc"];
}

if(isset($_GET['action'])) {
	$action=$_GET['action'];
	if($action=='buy') {
		$item=$_GET['item'];
		$sc->Buy($item,1);
	}
	if($action=='return') {
		$item=$_GET['item'];
		$sc->CancelBuy($item,1);
	}
	if($action=='clear') {
		$sc->clear();
	}
}
echo "<button><a href='?action=clear'>Clear Shopping cart</a></button>";
$pl=ProductList::getProductList();
echo "<table><thead>";
echo "<th>name</th>";
echo "<th>price</th>";
echo "<th>buy</th>";
echo "<th>return</th>";
echo "</thead>";
$list=$pl->getPrices();
foreach($list as $itemname => $itemprice) {
	echo "<tr>";
	echo "<td>$itemname</td>";
	echo "<td>$itemprice</td>";
	echo "<td><a href='ShoppingCartForm.php?action=buy&item=$itemname'>Buy</a></td>";
	echo "<td><a href='ShoppingCartForm.php?action=return&item=$itemname'>Return</a></td>";
	echo "</tr>";
}
echo "</table>";
echo "<br/>";
$sc->printState();
?>
