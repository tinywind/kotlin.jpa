package jpa.study.dao.ch07

import javax.persistence.*

Entity
public class Seller {
    public Id var
            id: Long? = null
    public var
            shopname: String? = null
    public MapsId OneToOne JoinColumn(name = "visitor_id") var
            visitor: Visitor? = null
}