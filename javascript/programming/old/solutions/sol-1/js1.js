var cart = {};
	
	function addToCart(prod, price) {
		var currItem = cart[prod];
		if (!(prod in cart)) {
			cart[prod] = {price:price, quantity:0};
			currItem = cart[prod];
		}
		
		currItem.quantity += 1;
		
		document.getElementById(prod + "_Q").innerHTML = "X" + currItem.quantity;
	}
	
	
	function checkout() {
		var total = 0;
		var itemsString = "";
		for (var item in cart) {
			total += (cart[item].quantity * cart[item].price);
			if (cart[item].quantity > 0)
				itemsString += cart[item].quantity + " X " + item + '<br>';
		}
		
		itemsString += "Total:" + total;
		
		document.getElementById("summary").innerHTML = itemsString;
		//alert ("Your are buying : " + itemsString);
		//alert ("Your Total is: " + total);
	} 
