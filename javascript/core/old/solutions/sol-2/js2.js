var cart = createCart();

	


// Cart factory
function createCart() {
	var cart = {};
	var lineItems = {}
	cart.addItem = function(item) {
			
			if (!(item.getName() in lineItems)) {
				lineItems[item.getName()] = createLineItem(item, 0);
			}
			var currLineItem = lineItems[item.getName()];
			currLineItem.setQuantity(currLineItem.getQuantity() + 1);
		}
		
	cart.getQuantity = function(item) {
			var currLineItem = lineItems[item.getName()];
			if (currLineItem)
				return currLineItem.getQuantity();
		}
	
	
	cart.checkout = function() {
			var total = 0;
			var itemsString = "";
			for (var lineItem in lineItems) {
				total += lineItems[lineItem].calculatePrice();
				itemsString += lineItems[lineItem].getQuantity() + " X " + lineItems[lineItem].getItem().getName() + '<br>';
			}
			
			itemsString += "Total:" + total;
			
			return itemsString;
		}
	
	
	return cart;
}


// LineItem factory
function createLineItem(item, quantity) {
	var lineItem = {};
	addProperty(lineItem, 'Item', item);
	addProperty(lineItem, 'Quantity', quantity);
	
	lineItem.calculatePrice = function() {
			return lineItem.getQuantity() * lineItem.getItem().getPrice();
		}
	
	return lineItem;
}


// Item factory
function createItem(itemName, price) {
	var item = {};
	addProperty(item, 'Name', itemName);
	addProperty(item, 'Price', price);
	
	return item;
}

 


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
