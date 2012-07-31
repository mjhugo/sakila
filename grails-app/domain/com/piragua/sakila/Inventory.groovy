package com.piragua.sakila

class Inventory {

	Date lastUpdate
	Film film

	static hasMany = [rentals: Rental]
	static belongsTo = [Film]

	static mapping = {
		id column: "inventory_id"
		version false
	}

	static constraints = {
		lastUpdate maxSize: 19
	}
}
