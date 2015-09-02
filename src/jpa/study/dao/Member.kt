package  jpa.study.dao

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

Entity
Table(name = "member")
public class Member {
    Id
    public var id: Long? = null
    public var username: String? = null
    public var age: Int? = null

    override fun toString(): String {
        return "[Member] id:$id username:$username age:$age"
    }
}
