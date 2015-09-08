package jpa.study.dao.ch09

import jpa.study.dao.ch07.TypeModel
import java.io.Serializable
import java.util.Date
import javax.persistence.*

Entity
public class Object09 : TypeModel() {
    @Embedded // droppable
    AttributeOverrides(
            AttributeOverride(name = "city", column = Column(name = "a_city")),
            AttributeOverride(name = "street", column = Column(name = "a_street")),
            AttributeOverride(name = "zipcode", column = Column(name = "a_zipcode"))
    )
    public var value: Address? = null

    AttributeOverrides(
            // [AttributeOverride]'s name is not table column. it is class member
            AttributeOverride(name = "startedAt", column = Column(name = "started_at_a")),
            AttributeOverride(name = "endedAt", column = Column(name = "ended_at_a"))
    )
    public var value1: CustomType09? = null


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}

Embeddable
public class CustomType09 : Serializable { // Serializable for primary key
    public var
            startedAt: Date? = Date()
    public Temporal(TemporalType.TIMESTAMP) Column(name = "ended_at") var
            endedAt: Date? = Date()
            /* prohibit external modifying: because of safety value referencing */
        private set(other: Date?) {
            $endedAt = other
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Address) return false

        other as CustomType09
        if (if (startedAt != null) startedAt != other.startedAt else other.startedAt != null) return false
        if (if (endedAt != null) endedAt != other.endedAt else other.endedAt != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = if (startedAt != null) startedAt!!.hashCode() else 0
        result *= 31 + (if (endedAt != null) endedAt!!.hashCode() else 0)
        return result
    }
}

Embeddable
public class Address {

    public var city: String? = null
    public var street: String? = null
    public var zipcode: String? = null

    public constructor() {
    }

    public constructor(city: String, street: String, zipcode: String) {
        this.city = city
        this.street = street
        this.zipcode = zipcode
    }

    override fun toString(): String {
        return "Address{" + "city='" + city + '\'' + ", street='" + street + '\'' + ", zipcode='" + zipcode + '\'' + '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Address) return false

        if (if (city != null) city != other.city else other.city != null) return false
        if (if (street != null) street != other.street else other.street != null) return false
        if (if (zipcode != null) zipcode != other.zipcode else other.zipcode != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = if (city != null) city!!.hashCode() else 0
        result *= 31 + (if (street != null) street!!.hashCode() else 0)
        result *= 31 + (if (zipcode != null) zipcode!!.hashCode() else 0)
        return result
    }
}
