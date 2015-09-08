package jpa.study.dao.ch07

import javax.persistence.*

Entity
Inheritance(strategy = InheritanceType.JOINED)
DiscriminatorColumn(name = "type")
public abstract class Item {
    public Id GeneratedValue var
            id: Long? = null
    public var
            name: String? = null
    public var
            price: Double? = null
}