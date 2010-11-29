<?php
require_once("Utils.php");
require_once("ProductList.php");

class ShoppingCart {
	// array of bought items
	private $items;
	// a constructor for the shopping cart
	public function __construct() {
		$this->items=array();
	}
	// clear the shopping cart..
	public function clear() {
		$this->items=array();
	}
	// defining a destructor (currently we do nothing on destruction)
	public function __destruct() {
	}
	public function Buy($itemname,$itemquantity) {
		$pl=ProductList::getProductList();
		if(!($pl->isProduct($itemname))) {
			my_error("sorry, I do not sell [$itemname]");
		}
		if($itemquantity<1) {
			my_error("sorry, you cannot buy [$itemquantity] of [$itemname]");
		}
		if(!isset($this->items[$itemname])) {
			$this->items[$itemname]=0;
		}
		$this->items[$itemname]+=$itemquantity;
	}
	public function CancelBuy($itemname,$itemquantity) {
		$pl=ProductList::getProductList();
		if(!($pl->isProduct($itemname))) {
			my_error("sorry, I do not sell [$itemname]");
		}
		if(!isset($this->items[$itemname])) {
			my_error("sorry, you cannot return items you didnt buy");
		}
		if($itemquantity<1) {
			my_error("you cannot return a negative number of items");
		}
		if($this->items[$itemname]<$itemquantity) {
			my_error("sorry, you cannot return more items than you bought");
		}
		$this->items[$itemname]-=$itemquantity;
		if($this->items[$itemname]==0) {
			unset($this->items[$itemname]);
		}

	}
	public function TotalPrice() {
		$pl=ProductList::getProductList();
		$sum=0;
		foreach($this->items as $itemname => $itemquantity) {
			$itemprice=$pl->getPrice($itemname);
			$sum+=$itemquantity*$itemprice;
		}
		return $sum;
	}	
	public function printState() {
		$pl=ProductList::getProductList();
		echo "<table><thead>";
		echo "<th>name</th>";
		echo "<th>quantity</th>";
		echo "<th>price</th>";
		echo "</thead>";
		foreach($this->items as $itemname => $itemquantity) {
			$itemprice=$pl->getPrice($itemname);
			echo "<tr>";
			echo "<td>$itemname</td>";
			echo "<td>$itemquantity</td>";
			echo "<td>$itemprice</td>";
			echo "</tr>";
		}
		echo "</table><br/>";
		$totalprice=$this->TotalPrice();
		echo "totalprice is $totalprice<br/>";
	}
	public function Charge() {
	}
}
?>
