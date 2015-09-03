package jpa.study.dao

import javax.persistence.*

Entity
public class Key {
    Id GeneratedValue var id: Long? = null
    var name: String? = null

    OneToOne JoinColumn(name = "locker_id") var locker: Locker? = null

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        name:$name"""
    }
}