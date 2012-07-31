package com.piragua.sakila

import org.apache.commons.lang.WordUtils
import org.grails.solr.Solr

class Film {
    static enableSolrSearch = true
    static solrAutoIndex = true

    @Solr(asText=true)
    String title
    @Solr(asText=true)
    String description
	Integer releaseYear
	Byte rentalDuration
	BigDecimal rentalRate
	Short length
	BigDecimal replacementCost
	String rating

    @Solr(field="specialFeatures")
    String specialFeatures
	Date lastUpdated
	Language language

	static hasMany = [actors: Actor,
	                  categories: Category]

	static mapping = {
		id column: "film_id"
		version false
        actors joinTable: [name: 'film_actor']
        categories joinTable: [name: 'film_category']
        lastUpdated column: 'last_update'
	}

	static constraints = {
		description nullable: true, maxSize: 65535
		releaseYear nullable: true
		length nullable: true
		rating nullable: true, maxSize: 5
		specialFeatures nullable: true, maxSize: 54
		lastUpdated maxSize: 19
	}



    def indexSolrSpecialFeatures(doc){
        if(specialFeatures) {
            def fieldName =
                this.solrFieldName("specialFeatures")
            doc.addField(fieldName,
                    specialFeatures.split(','))
        }
    }

    @Override
    String toString() {
        return WordUtils.capitalizeFully(title)
    }
}
