package jpa.study.dao.ch08

import jpa.study.dao.ch07.TypeModel
import java.util.ArrayList
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

Entity
public class Parent08Cascade : TypeModel() {
    public OneToMany(mappedBy = "parent", cascade = arrayOf(CascadeType.PERSIST, CascadeType.REMOVE), orphanRemoval = true) var
            children: MutableList<Child08Cascade> = ArrayList()
}

Entity
public class Child08Cascade : TypeModel() {
    public ManyToOne var
            parent: Parent08Cascade? = null
}