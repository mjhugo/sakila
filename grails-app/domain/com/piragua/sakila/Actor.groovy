package com.piragua.sakila

import groovy.transform.ToString
import org.apache.commons.lang.WordUtils

class Actor {
    static enableSolrSearch = true
    static solrAutoIndex = true

    String firstName
	String lastName
	Date lastUpdate

	static hasMany = [films: Film]

    static belongsTo = [Film]

	static mapping = {
		id column: "actor_id"
		version false
        films joinTable: [name: 'film_actor']
    }

	static constraints = {
		firstName maxSize: 45
		lastName maxSize: 45
		lastUpdate maxSize: 19
	}

    @Override
    String toString() {
        return WordUtils.capitalizeFully("$firstName $lastName")
    }
}
