/*jsl:import inventory.js*/
// here comes the cart...
function Cart() {
	this.tbid=undefined;
	this.totalid=undefined;
	// key: item id, value: amount to buy
	this.buyMap={};
	// key: item id, value: element showing the entire row 
	this.domRowMap={};
	// key: item id, value: element showing the amount bought
	this.domAmountMap={};
}
Cart.prototype.setTbid=function(tbid) {
	this.tbid=tbid;
};
Cart.prototype.setTotalid=function(totalid) {
	this.totalid=totalid;
	this.updateTotal();
};
Cart.prototype.buyItemById=function(id,amount) {
	var i=Inventory.getInstance();
	i.verifyEnoughItems(id,amount);
	if(id in this.buyMap) {
		// already have item in cart
		this.buyMap[id]+=amount;
	} else {
		// first time buying this item
		this.buyMap[id]=amount;
		this.createRow(id);
	}
	this.domAmountMap[id].nodeValue=this.buyMap[id];
	i.changeStorage(id,-amount);
	this.updateTotal();
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
	this.domAmountMap[id].nodeValue=this.buyMap[id];
	if(this.buyMap[id]===0) {
		this.domRowMap[id].parentNode.removeChild(this.domRowMap[id]);
		delete this.buyMap[id];
		delete this.domRowMap[id];
		delete this.domAmountMap[id];
	}
	i.changeStorage(id,amount);
	this.updateTotal();
};
Cart.prototype.cartPrice=function() {
	var i=Inventory.getInstance();
	var sum=0;
	for(var id in this.buyMap) {
		sum+=i.getItemById(id).price*this.buyMap[id];
	}
	return sum;
};
Cart.prototype.createRow=function(id) {
	var row=document.createElement('tr');
	var cell1=document.createElement('td');
	var cell2=document.createElement('td');
	var cell3=document.createElement('button');
	cell3.onclick=(function(iid) {
		return function() {
			Cart.getInstance().sellItemById(iid,1);
		};
	})(id);
	var text1=document.createTextNode(id);
	var text2=document.createTextNode(this.buyMap[id]);
	var text3=document.createTextNode('-');
	cell1.appendChild(text1);
	cell2.appendChild(text2);
	cell3.appendChild(text3);
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	this.domRowMap[id]=row;
	this.domAmountMap[id]=text2;
	var table=document.getElementById(this.tbid);
	table.appendChild(row);
};
Cart.prototype.updateTotal=function() {
	var span=document.getElementById(this.totalid);
	span.innerHTML=this.cartPrice();
};
// singleton pattern
Cart.instance=new Cart();
Cart.getInstance=function() {
	return Cart.instance;
};
