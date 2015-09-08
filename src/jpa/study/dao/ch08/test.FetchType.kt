package jpa.study.dao.ch08

import jpa.study.dao.ch07.Parent
import jpa.study.dao.ch07.Parent2
import jpa.study.dao.ch07.TypeModel
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

Entity
public class Child08 : TypeModel() {
    // if JoinColumn.nullable == false, use join. else left outer join (default: true)
    public ManyToOne(fetch = FetchType.EAGER) JoinColumn(name = "parent_id", nullable = false) var
            parent: Parent? = null

    public ManyToOne(fetch = FetchType.LAZY) JoinColumn(name = "parent2_id") var
            parent2: Parent2? = null
}