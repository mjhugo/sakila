package com.piragua.sakila

class Country {

	String country
	Date lastUpdate

	static hasMany = [cities: City]

	static mapping = {
		id column: "country_id"
		version false
	}

	static constraints = {
		country maxSize: 50
		lastUpdate maxSize: 19
	}

    @Override
    String toString() {
        return country
    }
}
