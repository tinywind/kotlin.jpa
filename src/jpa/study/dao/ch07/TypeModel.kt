package jpa.study.dao.ch07

import javax.persistence.*

Entity
Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TypeModel : BaseModel {
    Id GeneratedValue public var
            id: Long? = null
    public var
            name: String? = null
}