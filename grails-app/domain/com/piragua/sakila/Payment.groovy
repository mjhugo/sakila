package com.piragua.sakila

class Payment {

	BigDecimal amount
	Date paymentDate
	Date lastUpdate
	Customer customer
	Rental rental

	static belongsTo = [Customer, Rental]

	static mapping = {
		id column: "payment_id"
		version false
	}

	static constraints = {
		paymentDate maxSize: 19
		lastUpdate maxSize: 19
	}
}
