package jpa.study.dao.ch07

import java.util.ArrayList
import javax.persistence.*


/* 1:1 JoinTable */
Entity
public class Parent : TypeModel() {
    public OneToOne JoinTable(name = "parent_x_child", joinColumns = arrayOf(JoinColumn(name = "parent_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "child_id"))) var
            child: Child? = null
}

/* 1:N JoinTable */
Entity class Parent2 : TypeModel() {
    public OneToMany JoinTable(name = "parent2_x_child", joinColumns = arrayOf(JoinColumn(name = "parent_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "child_id"))) var
            children: MutableList<Child> = ArrayList()
}

/* N:1 JoinTable */
Entity
public class Child : TypeModel() {
    public ManyToOne(optional = false) JoinTable(name = "parent3_x_child", inverseJoinColumns = arrayOf(JoinColumn(name = "parent_id")), joinColumns = arrayOf(JoinColumn(name = "child_id"))) var
            parent3: Parent3? = null
}

Entity class Parent3 : TypeModel() {
    public OneToMany(mappedBy = "parent3") var
            children: MutableList<Child> = ArrayList()
}

/* N:N JoinTable */
Entity
public class Parent4 : TypeModel()  {
    public ManyToMany JoinTable(name = "parent4_x_child", joinColumns = arrayOf(JoinColumn(name = "parent_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "child_id"))) var
            children: MutableList<Child> = ArrayList()
}
