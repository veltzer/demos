/*jsl:import inventory.js*/
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
};
Cart.prototype.verifyBuyingItem=function(id) {
	if(!(id in this.buyMap)) {
		throw 'didnt buy item '+id;
	}
};
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
	if(this.buyMap[id]===0) {
		delete this.buyMap[id];
	}
	i.changeStorage(id,amount);
};
Cart.prototype.cartPrice=function() {
	var i=Inventory.getInstance();
	var sum=0;
	for(var id in this.buyMap) {
		sum+=i.getItemById(id).price*this.buyMap[id];
	}
	return sum;
};
Cart.prototype.toString=function() {
	var string_arr=[];
	for(var id in this.buyMap) {
		string_arr.push(id+","+this.buyMap[id]);
	}
	return string_arr.join('<br/>')+'<br/>';
};
// singleton pattern
Cart.instance=new Cart();
Cart.getInstance=function() {
	return Cart.instance;
};
