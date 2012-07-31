package com.piragua.sakila

import groovy.transform.ToString

@ToString
class Address {

	String address
	String address2
	String district
	String postalCode
	String phone
	Date lastUpdate
	City city

	static hasMany = [customers: Customer]
	static belongsTo = [City]

	static mapping = {
		id column: "address_id"
		version false
	}

	static constraints = {
		address maxSize: 50
		address2 nullable: true, maxSize: 50
		district maxSize: 20
		postalCode nullable: true, maxSize: 10
		phone maxSize: 20
		lastUpdate maxSize: 19
	}
}
