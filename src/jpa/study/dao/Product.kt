package jpa.study.dao

import javax.persistence.*

Entity
SequenceGenerator(name = "product_id_seq_generator")
class Product {
    Id GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq_generator") var id: Long? = null
    Column(nullable = false) var title: String? = null
    Column(nullable = false) var price: Double? = null

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
         id:$id
         title:$title
         price:$price"""
    }
}