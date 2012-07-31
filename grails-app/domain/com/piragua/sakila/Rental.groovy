package com.piragua.sakila

class Rental {

	Date rentalDate
	Date returnDate
	Date lastUpdate
	Customer customer
	Inventory inventory

	static hasMany = [payments: Payment]
	static belongsTo = [Customer, Inventory]

	static mapping = {
		id column: "rental_id"
		version false
	}

	static constraints = {
		rentalDate maxSize: 19
		returnDate nullable: true, maxSize: 19
		lastUpdate maxSize: 19
	}
}
