// here starts the inventory class
function InventoryItem(id,name,price,storage) {
	this.id=id;
	this.name=name;
	this.price=price;
	this.storage=storage;
}
InventoryItem.prototype.toString=function() {
	return ''+this.id+','+this.name+','+this.price+','+this.storage;
}
InventoryItem.prototype.verifyStorage=function(storage) {
	if(this.storage<storage) {
		throw 'havent got enough items';
	}
}
InventoryItem.prototype.changeStorage=function(storage) {
	if(storage<0) {
		this.verifyStorage(storage);
	}
	this.storage+=storage;
}
// here starts the inventory
function Inventory() {
	// this maps id->InvenrotyItem
	this.itemMap={};
}
Inventory.prototype.addProduct=function(item) {
	this.itemMap[item.id]=item;
}
Inventory.prototype.delProductById=function(id) {
	delete this.itemMap[id];
}
Inventory.prototype.getItemById=function(id) {
	return this.itemMap[id];
}
Inventory.prototype.verifyItemInInventory=function(id) {
	if(!(id in this.itemMap)) {
		throw 'no such item with id'+id;
	}
}
Inventory.prototype.verifyEnoughItems=function(id,amount) {
	this.verifyItemInInventory(id);
	var item=this.itemMap[id];
	item.verifyStorage(amount);
}
Inventory.prototype.changeStorage=function(id,storage) {
	this.verifyItemInInventory(id);
	var item=this.itemMap[id];
	item.changeStorage(storage);
}
Inventory.prototype.toString=function() {
	var string_arr=[]
	for(var id in this.itemMap) {
		string_arr.push(this.itemMap[id].toString())
	}
	return string_arr.join('<br/>')
}
// singleton pattern
Inventory.theInstance=new Inventory();
Inventory.getInstance=function() {
	return Inventory.theInstance;
}
