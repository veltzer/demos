// A generic toString function

myToString=function() {
	var s="";
	for(var key in this) {
		if(typeof(this[key])!='function') {
			s+=key+": "+this[key]+"<br/>";
		}
	}
	return s;
}

// A generic init from hash table function
myInitFromObject=function(o,hash) {
	for(key in hash) {
		o[x]=hash[x];
	}
}

// The Product class

function Product(iid,iname,iprice,iquantity) {
	this.id=iid;
	this.name=iname;
	this.price=iprice;
	this.quantity=iquantity;
}
Product.prototype.getId=function() {
	return this.id;
}
Product.prototype.setId=function(iid) {
	this.id=iid;
}
Product.prototype.getName=function() {
	return this.name;
}
Product.prototype.setName=function(iname) {
	this.name=iname;
}
Product.prototype.getPrice=function() {
	return this.price;
}
Product.prototype.setPrice=function(iprice) {
	this.price=iprice;
}
Product.prototype.getPrice=function() {
	return this.price;
}
Product.prototype.setPrice=function(iprice) {
	this.price=iprice;
}
Product.prototype.getQuantity=function() {
	return this.quantity;
}
Product.prototype.setQuantity=function(iquantity) {
	this.quantity=iquantity;
}
Product.prototype.toString=myToString;
Product.prototype.initFromObject=myInitFromObject;

// The inventory class

function Inventory() {
	this.storeById={};
	this.storeByName={};
	return this;
}
Inventory.prototype.add=function(product) {
	this.storeById[product.getId()]=product;
	this.storeByName[product.getName()]=product;
}
Inventory.prototype.remove=function(product) {
	delete this.storeById[product.getId()];
	delete this.storeByName[product.getName()];
}
Inventory.prototype.getById=function(key) {
	return this.storeById[key];
}
Inventory.prototype.getByName=function(key) {
	return this.storeByName[key];
}
Inventory.prototype.toString=myToString;
// This function loads fake data into the inventory
Inventory.prototype.addFakeData=function(inv) {
	var p1=new Product(457,"basketball",44.99,5);
	var p2=new Product(458,"baseball",23.99,2);
	this.add(p1);
	this.add(p2);
}
// This function fills a given table with the data
Inventory.prototype.fillTable=function(eid) {
	var tab=document.getElementById(eid);
	for(id in this.storeById) {
		var prod=this.storeById[id];
		var row=document.createElement('tr');
		var cell1=document.createElement('td');
		var cell2=document.createElement('td');
		var cell3=document.createElement('td');
		var cell4=document.createElement('td');
		var cell5=document.createElement('td');
		var cell6=document.createElement('td');
		var e_id=document.createTextNode(prod.getId());
		var e_name=document.createTextNode(prod.getName());
		var e_price=document.createTextNode(prod.getPrice());
		var e_quantity=document.createTextNode(prod.getQuantity());
		var e_add=document.createElement('button');
		var e_sub=document.createElement('button');
		var e_add_t=document.createTextNode('+');
		var e_sub_t=document.createTextNode('-');
		e_add.appendChild(e_add_t);
		e_sub.appendChild(e_sub_t);
		cell1.appendChild(e_id);
		cell2.appendChild(e_name);
		cell3.appendChild(e_price);
		cell4.appendChild(e_quantity);
		cell5.appendChild(e_add);
		cell6.appendChild(e_sub);
		row.appendChild(cell1);
		row.appendChild(cell2);
		row.appendChild(cell3);
		row.appendChild(cell4);
		row.appendChild(cell5);
		row.appendChild(cell6);
		tab.appendChild(row);
	}
}
// This is a singleton pattern to return the current inventory
Inventory.getInventory=function() {
	Inventory.inv=new Inventory();
	Inventory.inv.addFakeData();
	Inventory.getInventory=function() {
		return Inventory.inv;
	}
	return Inventory.inv;
}

// The Shopping cart class
function ShoppingCart() {
	this.cartById={};
}
// This method adds a product to the cart
// if this is the first time we add the product we just set
// the quantity to 1. If not then we increase the quantity by 1.
ShoppingCart.prototype.add=function(pid) {
	if(pid in this.cartById) {
		this.cartById[pid]++;
	} else {
		this.cartById[pid]=1;
	}
}
// This method removes the product from the cart.
// If this is the last instance of the product then we delete the
// product from the hash map altogether using the "delete" builtin.
ShoppingCart.prototype.sub=function(pid) {
	this.cartById[pid]--;
	if(this.cartById[pid]==0) {
		delete this.cartById[pid];
	}
}
// Calculate the price of the shopping cart 
ShoppingCart.prototype.getPrice=function() {
	var inv=Inventory.getInventory();
	var sum=0;
	for(id in this.cartById) {
		var prod=inv.getById(id);
		var num=this.cartById[id];
		sum+=prod.getPrice()*num;
	}
	return sum;
}
// Calculate the number of items in the shopping cart 
ShoppingCart.prototype.getNumItems=function() {
	var sum=0;
	for(id in this.cartById) {
		var num=this.cartById[id];
		sum+=num;
	}
	return sum;
}
// Lets make it so the shopping cart has a toString also...
ShoppingCart.prototype.toString=myToString;
// Lets make the shopping cart be able to show itself.
ShoppingCart.prototype.showYourself=function(eid) {
	var buf=new Array();
	buf.push('<table border="1"><tr><th>id</th><th>quantity</th></tr>');
	for(x in this.cartById) {
		buf.push('<tr><td>'+x+'</td><td>'+this.cartById[x]+'</td></tr>');
	}
	buf.push('</table>');
	buf.push('<b>number of items: '+this.getNumItems()+'</b>');
	buf.push('<br/>');
	buf.push('<b>total price: '+this.getPrice()+'</b>');
	var e=document.getElementById(eid);
	e.innerHTML=buf.join('');
}
// This is a singleton pattern to return the current shopping cart
ShoppingCart.getCart=function() {
	if(!('cart' in ShoppingCart)) {
		var cart=new ShoppingCart();
		// some fake data
		cart.add(457);
		cart.add(457);
		cart.add(458);
		ShoppingCart.cart=cart;
	}
	return ShoppingCart.cart;
}

// The init code

function MyInit() {
	var inv=Inventory.getInventory().fillTable('tbody');
	ShoppingCart.getCart().showYourself('mydiv');
}
window.onload=MyInit;
