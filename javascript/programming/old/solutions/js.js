// Cart factory
function Cart() {
	addProperty(this, 'LineItems', {});
	
}

Cart.prototype.addItem = function(item) {
	if (!(item.getName() in this.getLineItems())) {
		this.getLineItems()[item.getName()] = new LineItem(item, 0);
	}
	var currLineItem = this.getLineItems()[item.getName()];
	currLineItem.setQuantity(currLineItem.getQuantity() + 1);
}
		
Cart.prototype.getQuantity = function(item) {
	var currLineItem = this.getLineItems()[item.getName()];
	if (currLineItem)
		return currLineItem.getQuantity();
}

Cart.prototype.getTotal = function() {
	var total = 0;
	for (var lineItem in this.getLineItems()) {
		total += this.getLineItems()[lineItem].calculatePrice();
	}
	return total;
}

Cart.prototype.checkout = function(customer) {
	var itemsString = "";
	for (var lineItem in this.getLineItems()) {
		itemsString += this.getLineItems()[lineItem].getQuantity() + " X " + this.getLineItems()[lineItem].getItem().getName() + '<br>';
	}
	itemsString += "Total for " + customer.toString() + ":" + customer.calculatePrice(this);
			
	return itemsString;
}

// LineItem factory
function LineItem(item, quantity) {
	addProperty(this, 'Item', item);
	addProperty(this, 'Quantity', quantity);
}

LineItem.prototype.calculatePrice = function() {
	return this.getQuantity() * this.getItem().getPrice();
}

// Item factory
function Item(itemName, price) {
	addProperty(this, 'Name', itemName);
	addProperty(this, 'Price', price);
}

//Customer
function Customer(firstName, lastName, age) {
	addProperty(this, 'Lastname', lastName || 'm');
	addProperty(this, 'Firstname', firstName || 'g');
	addProperty(this, 'Age', age || 60);
}

Customer.prototype.calculatePrice = function(cart) {
		var price = cart.getTotal();
		if (this.getAge() >= 65) {
			price = price * 0.75;
		}
		return price;
	}
	
Customer.prototype.toString = function() {
	return this.getFirstname() + " " + this.getLastname() + " (" + this.getAge() + ")";
}

//VIP Customer
function VIPCustomer(firstName, lastName, age) {
	this.superclass_Customer(firstName, lastName, age);	
}

VIPCustomer.prototype = new Customer();
VIPCustomer.prototype.superclass_Customer = Customer;
VIPCustomer.prototype.calculatePrice = function(cart) {
	return cart.getTotal() * 0.5;
}
//VIPCustomer.prototype.constructor = VIPCustomer;

// Utility Method for adding properties to Objects
function addProperty(obj, propName, defaultValue, validator) {
	if (!validator) {
		validator = function() {return true;}
	}	
	var prop = defaultValue;
	obj["get" + propName] = function() {
		return prop;
	}
		
	obj["set" + propName] = function(value) {
		if (!validator(value)) throw "Invalid value";
		prop = value;
	}
	
}
