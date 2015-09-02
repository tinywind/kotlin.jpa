package  jpa.study.ch04

import jpa.study.dao.Member
import jpa.study.dao.Request
import jpa.study.dao.RequestXProduct
import jpa.study.dao.Product
import java.util.Date
import javax.persistence.Persistence

fun test() {
    var emf = Persistence.createEntityManagerFactory("jpabook")

    fun createMember(username: String): Member {
        var em = emf.createEntityManager()
        var tx = em.getTransaction()
        tx.begin()

        var m = Member()

        m.username = username

        em.persist(m)

        m.firstName = "J"
        m.lastName = "KK"

        tx.commit()
        em.close()

        println(m)

        return m;
    }

    fun mergeMember(member: Member) {
        var em = emf.createEntityManager()
        var tx = em.getTransaction()
        tx.begin()

        var mergeMember = em.merge(member)

        println("$member")
        println("$mergeMember")
        println("em contains member? ${em.contains(member)}")
        println("em contains mergeMember? ${em.contains(mergeMember)}")

        createMember("user1")
        createMember("user2")
        createMember("user3")

        em.remove(mergeMember)
        tx.commit()
        em.close()
    }

    var m = try {
        createMember("username5")
    } catch(e: Exception) {
        e.printStackTrace()
        null
    }

    m ?: throw IllegalStateException("m is null")
    m.username = "changed username2"
    mergeMember(m)

    emf.close()
}

fun mission() {
    var emf = Persistence.createEntityManagerFactory("jpabook")
    var em = emf.createEntityManager()

    fun createCondition() {
        var product1 = Product()
        product1.title = "good good"
        product1.price = 3000.toDouble()

        var product2 = Product()
        product2.title = "bad bad"
        product2.price = 100.toDouble()

        var member = Member()
        member.firstName = "j"
        member.lastName = "kk"
        member.username = member.getFullName()

        em.persist(member)
        em.persist(product1)
        em.persist(product2)

        var request = Request()
        request.memberId = member.id
        request.createdAt = Date()

        em.persist(request)

        var requestXProduct1 = RequestXProduct()
        requestXProduct1.requestId = request.id
        requestXProduct1.productId = product1.id

        var requestXProduct2 = RequestXProduct()
        requestXProduct2.requestId = request.id
        requestXProduct2.productId = product2.id

        em.persist(requestXProduct1)
        em.persist(requestXProduct2)
    }

    fun print() {
        var requestItems = em.createQuery("SELECT m FROM RequestXProduct m", javaClass<RequestXProduct>()).getResultList()

        for (item in requestItems) {
            var request = em.find(javaClass<Request>(), item.requestId)
            var product = em.find(javaClass<Product>(), item.productId)
            var member = em.find(javaClass<Member>(), request.memberId)

            println(member)
            println(request)
            println(product)
            println()
        }
    }

    var tx = em.getTransaction()
    try {
        tx.begin()
        createCondition()
        tx.commit()
    } catch(e: Exception) {
        e.printStackTrace()
        if (tx.isActive())
            tx.rollback()
        return
    }

    print()

    em.close()
    emf.close()
}