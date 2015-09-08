package jpa.study.dao.ch09

import jpa.study.dao.ch07.TypeModel
import java.util.HashSet
import javax.persistence.*

Entity
public class Member09 : TypeModel() {
    public ElementCollection CollectionTable(name = "foods09", joinColumns = arrayOf(JoinColumn(name = "member_id"))) Column(name = "food_name") var
            favoriteFoods: MutableCollection<String> = HashSet()

    public ElementCollection CollectionTable(name = "address09", joinColumns = arrayOf(JoinColumn(name = "member_id"))) var
            favoriteAddresses: MutableCollection<Address> = HashSet()

}