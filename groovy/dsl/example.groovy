#!/usr/bin/groovy

ExpandoMetaClass.enableGlobally()

class Order {
	def security
	def quantity
	def limitPrice
	def allOrNone
	def value
	def bs
	def buy(su, closure) {
		bs = 'Bought'
		buy_sell(su, closure)
	}
	def sell(su, closure) {
		bs = 'Sold'
		buy_sell(su, closure)
	}
	def getTo() {
		this
	}
	private buy_sell(su, closure) {
		security = su[0]
		quantity = su[1]
		closure()
	}
}

def methodMissing(String name, args) {
	order.metaClass.getMetaProperty(name).setProperty(order, args)
}
def getNewOrder() {
	order = new Order()
}
def valueAs(closure) {
	order.value = closure(order.quantity, order.limitPrice[0])
	order
}
Integer.metaClass.getShares = { -> delegate }
Integer.metaClass.of = { instrument -> [instrument, delegate] }



















orders = []
newOrder.to.buy(100.shares.of('IBM')) {
	limitPrice 300
	allOrNone true
	valueAs {qty, unitPrice -> qty * unitPrice - 500}
}
orders << order
newOrder.to.buy(150.shares.of('GOOG')) {
	limitPrice 300
	allOrNone true
	valueAs {qty, unitPrice -> qty * unitPrice - 500}
}
orders << order
newOrder.to.buy(200.shares.of('MSOFT')) {
	limitPrice 300
	allOrNone true
	valueAs {qty, unitPrice -> qty * unitPrice - 500}
}
orders << order
println orders
