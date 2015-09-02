package jpa.study.dao

import javax.persistence.*

Entity
SequenceGenerator(name = "request_x_product_id_seq_generator")
class RequestXProduct {
    Id GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_x_product_id_seq_generator") var id: Long? = null
    Column(nullable = false) var requestId: Long? = null
    Column(nullable = false) var productId: Long? = null

    override fun toString(): String {
        return """[${this.javaClass.getName()}]
         id:$id
         requestId:$requestId
         productId:$productId"""
    }
}