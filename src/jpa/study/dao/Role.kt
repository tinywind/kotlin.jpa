package jpa.study.dao

import java.util.ArrayList
import javax.persistence.*

Entity public class Role {
    Id GeneratedValue var id: Long? = null
    var name: String? = null

    ManyToMany
    JoinTable(name = "member_role",
            joinColumns = arrayOf(JoinColumn(name = "member_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "role_id")))
    public var members: MutableList<Member> = ArrayList<Member>()

    public fun addMember(member: Member) {
        if (!this.members.contains(member)) {
            this.members.add(member)
            if (!member.roles.contains(this))
                member.roles.add(this)
        }
    }

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        name:$name"""
    }
}