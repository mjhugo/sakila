package com.piragua.sakila

class Customer {

	String firstName
	String lastName
	String email
	Boolean active
	Date createDate
	Date lastUpdate
	Address address

	static hasMany = [payments: Payment,
	                  rentals: Rental]
	static belongsTo = [Address]

	static mapping = {
		id column: "customer_id"
		version false
	}

	static constraints = {
		firstName maxSize: 45
		lastName maxSize: 45
		email nullable: true, maxSize: 50
		createDate maxSize: 19
		lastUpdate maxSize: 19
	}
}
