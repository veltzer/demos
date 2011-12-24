#!/usr/bin/groovy

/*
	A basic class in Groovy

	Notes:
	private,public, protected support is there.
*/

class Product {
	private String name
	private def price
	def vendor

	public Product() {
	}

	Product(name, price, String vendor){
		this.name = name
		this.price = price
		this.vendor = vendor
	}

	public String getName(){
		return name
	}

	void setName(name){
		this.name = name
	}

	public String getPrice(){
		return price
	}

	def setPrice(price = 100.00) {
		this.price = price
	}

	String toString() {
		return "Name = $name, Price = $price, Vendor = $vendor"
	}

	static main(arguments) {
		def p1 = new Product("Mobile", "10000", "Nokia")
		println(p1.toString())

		def p2 = new Product(name: 'Laptop', price: "540000", vendor: "IBM")
		println p2

		def p3 = new Product()
		p3['name'] = "Television"
		p3.'price' = "45454"
		p3.setVendor("Samsung")
		println(p3.toString())

		def p4 = new Product(name: "DVD Player", vendor: "TCL")
		p4.setPrice()
		println(p4.toString())
	}
}
