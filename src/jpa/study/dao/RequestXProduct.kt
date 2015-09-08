package jpa.study.dao

import java.io.Serializable
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
public class RequestXProduct : Serializable {
    Id public var
            requestId: Long? = null
    Id public var
            productId: Long? = null
    public var
            orderedAt: Date? = null
    ManyToOne JoinColumn public var
            request: Request? = null
        set(request: Request?) {
            this.request?.requestXProducts?.remove(this)
            $request = request
            if (request?.requestXProducts?.contains(this) !== true) request?.requestXProducts?.add(this)
        }

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        requestId:$requestId
        productId:$productId
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
