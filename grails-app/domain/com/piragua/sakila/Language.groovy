package com.piragua.sakila

class Language {

	String name
	Date lastUpdate

	static hasMany = [films: Film]

	static mapping = {
		id column: "language_id"
		version false
	}

	static constraints = {
		name maxSize: 20
		lastUpdate maxSize: 19
	}

    @Override
    String toString() {
        return name
    }
}
