package jpa.study.dao.ch07

import java.util.*
import javax.persistence.*

Entity
DiscriminatorValue("movie")
PrimaryKeyJoinColumn(name = "movie_id")
public class Movie : DateTimeParanoidModel, TypeModel() {
    override var deletedAt: Date? = null
    override var createdAt: Date = Date()
    override var updatedAt: Date? = null
    public var
            actor: String? = null
}