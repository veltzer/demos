<?php

class ProductList {
	// single handler
	static private $handle=NULL;
	// array of prices
	private $prices;
	// a constructor for the shopping cart
	private function __construct() {
		$this->prices=array();
		$this->prices["basketball"]=1.99;
		$this->prices["baseball"]=0.79;
		$this->prices["football"]=2.89;
		$this->prices["bike"]=150;
		$this->prices["ford sedan"]=17000;
	}
	public function isProduct($productname) {
		return isset($this->prices[$productname]);
	}
	public function getPrice($productname) {
		return $this->prices[$productname];
	}
	public function getPrices() {
		return $this->prices;
	}
	static public function getProductList() {
		if(self::$handle==NULL) {
			self::$handle=new ProductList();
		}
		return self::$handle;
	}
}
?>
