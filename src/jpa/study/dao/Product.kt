package jpa.study.dao

import java.util.ArrayList
import javax.persistence.*

Entity
public class Product {
    Id GeneratedValue var id: Long? = null
    Column(nullable = false) var title: String? = null
    Column(nullable = false) var price: Double? = null

    /* 1:N one way access [deprecated]
     *   => [recommend] N:1 1:N bi-access */
    OneToMany(targetEntity = RequestXProduct::class)
    JoinColumn(name = "product_id_managed_by_product_entity")
    var requestXProducts: MutableList<RequestXProduct> = ArrayList()

    override fun toString(): String {
        var str = """[${this.javaClass.getName()}]
        id:$id
        title:$title
        price:$price
        """
        this.requestXProducts.forEachIndexed { i, requestXProduct -> str += """requestXProducts[$i]:${requestXProducts[i]}""" }
        return str;
    }
}