// cart object
function Cart() {
	// key: item id, value: amount to buy
	this.buyMap={};
}
Cart.prototype.buyItemById=function(id,amount) {
	var i=Inventory.getInstance();
	i.verifyEnoughItems(id,amount);
	if(id in this.buyMap) {
		// already have item in cart
		this.buyMap[id]+=amount;
	} else {
		// first time buying this item
		this.buyMap[id]=amount;
	}
	i.changeStorage(id,-amount);
}
Cart.prototype.verifyBuyingItem=function(id) {
	if(!(id in this.buyMap)) {
		throw 'didnt buy item '+id;
	}
}
Cart.prototype.sellItemById=function(id,amount) {
	var i=Inventory.getInstance();
	i.verifyItemInInventory(id);
	this.verifyBuyingItem(id);
	var amount_in_cart=this.buyMap[id];
	if(amount_in_cart<amount) {
		throw 'too many items sold '+amount;
	}
	this.buyMap[id]-=amount;
	// if we returned the last items...
	if(this.buyMap[id]==0) {
		delete this.buyMap[id];
	}
	i.changeStorage(id,amount);
}
Cart.prototype.cartPrice=function() {
	var i=Inventory.getInstance();
	var sum=0;
	for(id in this.buyMap) {
		sum+=i.getItemById(id).price*this.buyMap[id];
	}
	return sum;
}
// singleton pattern
Cart.instance=new Cart();
Cart.getInstance=function() {
	return Cart.instance;
}
