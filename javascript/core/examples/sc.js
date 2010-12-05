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

createFunctionWhichWrapsArgs=function(functocall,arg) {
	return function() {
		functocall(arg);
	}
}

createFunctionWhichWrapsMethodCall=function(instance,methodname,arg) {
	return function() {
		instance.instance[methodname](arg);
	}
}

PriceList.prototype.addToTbl=function(nodename) {
	var table=document.getElementById(nodename);
	for(var item in this.prices) {
		var price=this.prices[item];
		var row=document.createElement("tr");
		var cell1=document.createElement("td");
		var cell2=document.createElement("td");
		var text1=document.createTextNode(item);
		var text2=document.createTextNode(price);
		row.onclick=createFunctionWhichWrapsArgs(toCart,item);
		cell1.appendChild(text1);
		cell2.appendChild(text2);
		row.appendChild(cell1);
		row.appendChild(cell2);
		table.appendChild(row);
	}
}

PriceList.getTheObject=function() {
	if(!('sing' in PriceList)) {
		// first time we are called
		PriceList.sing=new PriceList();
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
	updateTotal();
}

function updateTotal() {
	var sc=ShoppingCart.getTheObject();
	document.getElementById('total').innerHTML=sc.calcPrice();
}

function createXMLHttpRequest() {
	try { return new XMLHttpRequest(); } catch(e) {}
	try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) {}
	try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) {}
	throw "XMLHttpRequest not supported";
}
function getPricesViaAjax() {
	var xhReq = createXMLHttpRequest();
	xhReq.open("GET", "snipplet.json", true);
	xhReq.onreadystatechange =function() {
		if (xhReq.readyState != 4) { return; }
		if (xhReq.status!=200) { return; }
		var serverResponse = xhReq.responseText;
		//var elem=document.getElementById("somediv");
		//elem.innerHTML=serverResponse;
		//var obj=eval("( "+serverResponse+" )");
		var obj=json_parse(serverResponse);
		var pl=PriceList.getTheObject();
		for(item in obj) {
			var price=obj[item];
			pl.addItem(item,price);
		}
		pl.addToTbl('tbl');
	}
	xhReq.send(null);
}

function myLoad() {
	updateTotal();
	getPricesViaAjax();
}
window.onload=myLoad;
