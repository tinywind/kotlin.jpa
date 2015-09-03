package jpa.study.dao

import java.util.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/*
* complex foreign key class
*   - foreign keys are not primary key, and not belong primary key
* */

Entity
public class RequestXProduct {
    Id var id: Long? = null
    var orderedAt: Date? = null

    ManyToOne JoinColumn private var request: Request? = null

    public fun setRequest(request: Request) {
        this.request?.requestXProducts?.remove(this)
        this.request = request
        if (!request.requestXProducts.contains(this)) request.requestXProducts.add(this)
    }

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        request:$request
        orderedAt:$orderedAt"""
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
