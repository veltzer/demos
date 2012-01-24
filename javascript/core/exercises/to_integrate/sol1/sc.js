
PriceList=function() {
	this.prices={};
}

PriceList.prototype.getPrice=function(item) {
	return this.prices[item];
}

PriceList.prototype.addItem=function(item,price) {
	this.prices[item]=price;
}

PriceList.prototype.removeItem=function(item) {
	delete this.prices[item];
}

PriceList.getTheObject=function() {
	if(!('sing' in PriceList)) {
		// first time we are called
		var s=new PriceList();
		s.addItem("basketball",5);
		s.addItem("baseball",2);
		s.addItem("football",7);
		PriceList.sing=s;
	}
	return PriceList.sing;
}

ShoppingCart=function() {
	this.cart={};
}

ShoppingCart.prototype.addToCart=function(item) {
	if(item in this.cart) {
		this.cart[item]++;
	} else {
		this.cart[item]=1;
	}
}

ShoppingCart.prototype.calcPrice=function() {
	var sum=0;
	for(var item in this.cart) {
		var amount=this.cart[item];
		var curPrice=amount*PriceList.getTheObject().getPrice(item);
		sum+=curPrice;
	}
	return sum;
}

ShoppingCart.getTheObject=function() {
	if(!('sing' in ShoppingCart)) {
		// first time we are called
		var s=new ShoppingCart();
		ShoppingCart.sing=s;
	}
	return ShoppingCart.sing;
}

function toCart(item) {
	var sc=ShoppingCart.getTheObject();
	sc.addToCart(item);
	updatePrice();
}

function updatePrice() {
	var sc=ShoppingCart.getTheObject();
	document.getElementById('total').innerHTML=sc.calcPrice();
}

window.onload=updatePrice;
