package jpa.study.dao.ch07

import java.util.Date
import javax.persistence.Entity
import javax.persistence.OneToOne

Entity
public class Visitor : DateTimeParanoidModel, TypeModel() {
    override var deletedAt: Date? = null
    override var createdAt: Date = Date()
    override var updatedAt: Date? = null
    public OneToOne(mappedBy = "visitor") var
            seller: Seller? = null
}