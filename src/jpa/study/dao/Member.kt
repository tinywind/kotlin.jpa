package  jpa.study.dao

import java.util.Date
import javax.persistence.*

Entity
SequenceGenerator(name = "member_id_seq_generator", initialValue = 1, allocationSize = 1)
//TableGenerator(name = "seq_generator", table = "my_seq", pkColumnValue = "seq", allocationSize = 2)
Table(name = "member",
        uniqueConstraints = arrayOf(
                UniqueConstraint(name = "member_username_age_unique", columnNames = arrayOf("username", "age"))))
class Member {
    Id GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_seq_generator")
    public var id: Long? = null

    Column(nullable = false, length = 20) public var username: String? = null

    Column(nullable = true, unique = false)
    // GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_generator")
    // SequenceGenerator(name = "member_age_seq")
    GeneratedValue public var age: Int? = null

    // Enumerated(EnumType.STRING)
    public var role: RoleType? = null

    // Column(name = "created_at")
    // Temporal(TemporalType.TIMESTAMP)
    public var createdAt: Date? = null

    Lob public var description: String? = null

    Transient public var firstName: String? = null

    Transient public var lastName: String? = null

    ManyToOne JoinColumn(name = "team_id") public var team: Team? = null

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id
        username:$username
        age:$age
        role:$role
        created:$createdAt
        description:$description
        fullName:${getFullName()}
        team:${team}"""
    }

    Access(AccessType.PROPERTY)
    public fun getFullName(): String = "$firstName $lastName"

    public fun setFullName(fullName: String?) {
        if (fullName != null) {
            val split = fullName.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (split.size() >= 1)
                firstName = split[0]
            if (split.size() >= 2)
                lastName = split[1]
        }
    }

    enum class RoleType {
        ADMIN, USER
    }
}

public fun Member.setTeam(team: Team) {
    this.team?.members?.remove(this)
    this.team = team
    //    !!! Warning : skipped below line, reversely referencing is not work
    team.members.add(this)
}