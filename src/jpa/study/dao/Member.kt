package  jpa.study.dao

import java.util.ArrayList
import java.util.Date
import javax.persistence.*

Entity
SequenceGenerator(name = "member_id_seq_generator", initialValue = 1, allocationSize = 1)
//TableGenerator(name = "seq_generator", table = "my_seq", pkColumnValue = "seq", allocationSize = 2)
Table(name = "member",
        uniqueConstraints = arrayOf(
                UniqueConstraint(name = "member_username_age_unique", columnNames = arrayOf("username", "age"))))
public class Member {
    Id GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_seq_generator")
    public var id: Long? = null

    Column(nullable = false, length = 20) public var username: String? = null

    Column(nullable = true, unique = false)
    // GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_generator")
    // SequenceGenerator(name = "member_age_seq")
    GeneratedValue public var age: Int? = null

    // Enumerated(EnumType.STRING)
    public var type: MemberType? = null

    // Column(name = "created_at")
    // Temporal(TemporalType.TIMESTAMP)
    public var createdAt: Date? = null

    Lob public var description: String? = null

    Transient public var firstName: String? = null

    Transient public var lastName: String? = null

    ManyToOne JoinColumn(name = "team_id") private var team: Team? = null

    public fun setTeam(team: Team) {
        this.team?.members?.remove(this)
        this.team = team
        //        !!! Warning : skipped below line, reversely referencing is not work
        if (!team.members.contains(this) ) team.members.add(this)
    }

    OneToMany(mappedBy = "member", targetEntity = Request::class) var requests: MutableList<Request> = ArrayList()

    OneToOne JoinColumn(name = "locker_id") var locker: Locker? = null

    ManyToMany(mappedBy = "members") var roles: MutableList<Role> = ArrayList<Role>()

    public fun addRole(role: Role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role)
            if (!role.members.contains(this))
                role.members.add(this)
        }
    }

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
        id:$id username:$username
        age:$age
        type:$type
        created:$createdAt
        fullName:${getFullName()}
        description:$description
        locker:${locker}
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

    enum class MemberType {
        ADMIN, USER
    }
}