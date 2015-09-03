package jpa.study.dao

import java.util.ArrayList
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

Entity
public class Country {

    Id GeneratedValue public var
            id: Long? = null

    public var
            name: String? = null

    OneToMany(mappedBy = "country") public var
            countryXLanguages: MutableList<CountryXLanguage> = ArrayList<CountryXLanguage>()


}