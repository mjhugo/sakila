package com.piragua.sakila

class City {

	String city
	Date lastUpdate
	Country country

	static hasMany = [addresses: Address]
	static belongsTo = [Country]

	static mapping = {
		id column: "city_id"
		version false
	}

	static constraints = {
		city maxSize: 50
		lastUpdate maxSize: 19
	}
}
