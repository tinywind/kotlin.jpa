package jpa.study.dao

import java.io.Serializable

public class CountryXLanguageId : Serializable {
    var country: Long? = null
    var language: Long? = null

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