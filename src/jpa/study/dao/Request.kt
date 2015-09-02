package jpa.study.dao

import java.util.Date
import javax.persistence.*

Entity
class Request { // order attribute is exist in postgresql db
    Id GeneratedValue var id: Long? = null
    Column(nullable = false) var memberId: Long? = null
    var createdAt: Date? = null

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
         id:$id memberId:$memberId
         createdAt:$createdAt"""
    }
}