package jpa.study.dao

import java.util.ArrayList
import javax.persistence.*

@Entity
public class Team {
    @Id @GeneratedValue var id: Long? = null
    @Column(nullable = false) var name: String? = null

    @OneToMany(mappedBy = "team", targetEntity = Member::class) var members: MutableList<Member> = ArrayList()

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        name:$name"""
    }
}