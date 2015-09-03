package jpa.study.dao

import java.io.Serializable
import javax.persistence.*

/*
* complex key class
*   - foreign keys are primary key
* */

Entity
//IdClass(CountryXLanguageId::class)
public class CountryXLanguage : Serializable {

    Id ManyToOne JoinColumn public var
            country: Country? = null

    Id ManyToOne JoinColumn public var
            language: Language? = null

    var dummy: String? = null
}