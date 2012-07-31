package com.piragua.sakila

class Category {
    static enableSolrSearch = true
    static solrAutoIndex = true

    String name
	Date lastUpdate

	static hasMany = [films: Film]

    static belongsTo = [Film]

    static mapping = {
		id column: "category_id"
        films joinTable: [name: 'film_category']
        version false
	}

	static constraints = {
		name maxSize: 25
		lastUpdate maxSize: 19
	}

    @Override
    public String toString() {
        return name
    }
}
