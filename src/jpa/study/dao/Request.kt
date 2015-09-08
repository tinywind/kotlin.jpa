package jpa.study.dao

import java.util.ArrayList
import java.util.Date
import javax.persistence.*

Entity
public class Request { // order attribute is exist in postgresql db
    Id GeneratedValue public var
            id: Long? = null
    public var
            memberId: Long? = null
    public var
            createdAt: Date? = null
    ManyToOne JoinColumn(/*name = "member_id", referencedColumnName = "id"*/) public var
            member: Member? = null
        set(member: Member?) {
            $member?.requests?.remove(this)
            $member = member
            if (member?.requests?.contains(this) !== true)
                member?.requests?.add(this)
        }
        get() {
            return $member
        }

    OneToMany(mappedBy = "request", targetEntity = RequestXProduct::class) var requestXProducts: MutableList<RequestXProduct> = ArrayList()

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        createdAt:$createdAt"""
    }
}