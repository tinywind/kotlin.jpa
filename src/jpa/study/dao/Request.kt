package jpa.study.dao

import java.util.ArrayList
import java.util.Date
import javax.persistence.*

Entity
public class Request { // order attribute is exist in postgresql db
    Id GeneratedValue var id: Long? = null
    var memberId: Long? = null
    var createdAt: Date? = null

    ManyToOne JoinColumn(/*name = "member_id", referencedColumnName = "id"*/)
    private var member: Member? = null

    public fun setMember(member: Member) {
        this.member?.requests?.remove(this)
        this.member = member
        if (!member.requests.contains(this)) member.requests.add(this)
    }

    OneToMany(mappedBy = "request", targetEntity = RequestXProduct::class) var requestXProducts: MutableList<RequestXProduct> = ArrayList()

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        createdAt:$createdAt"""
    }
}