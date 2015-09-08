package jpa.study.dao.ch07

import java.util.Date
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

Entity
DiscriminatorValue("album")
public class Album : DateTimeParanoidModel, TypeModel() {
    override var deletedAt: Date? = null
    override var createdAt: Date = Date()
    override var updatedAt: Date? = null
    public var
            artist: String? = null
}