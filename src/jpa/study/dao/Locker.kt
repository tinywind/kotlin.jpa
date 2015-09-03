package jpa.study.dao

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

Entity
public class Locker {
    Id GeneratedValue var id: Long? = null
    var name: String? = null

    /* relation key is located at primary table */
    OneToOne(mappedBy = "locker") var member: Member? = null

    /* relation key is located at target table */
    OneToOne(mappedBy = "locker") var key: Key? = null

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        name:$name
        key:$key"""
    }
}