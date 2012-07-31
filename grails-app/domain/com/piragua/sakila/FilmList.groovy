package com.piragua.sakila

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class FilmList implements Serializable {

	Short fid
	String title
	String description
	String category
	BigDecimal price
	Short length
	String rating
	String actors

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append fid
		builder.append title
		builder.append description
		builder.append category
		builder.append price
		builder.append length
		builder.append rating
		builder.append actors
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append fid, other.fid
		builder.append title, other.title
		builder.append description, other.description
		builder.append category, other.category
		builder.append price, other.price
		builder.append length, other.length
		builder.append rating, other.rating
		builder.append actors, other.actors
		builder.isEquals()
	}

	static mapping = {
		id composite: ["fid", "title", "description", "category", "price", "length", "rating", "actors"]
		version false
	}

	static constraints = {
		fid nullable: true
		title nullable: true
		description nullable: true, maxSize: 65535
		category maxSize: 25
		price nullable: true
		length nullable: true
		rating nullable: true, maxSize: 5
		actors nullable: true, maxSize: 341
	}
}
