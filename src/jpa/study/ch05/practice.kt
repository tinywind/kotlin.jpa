package  jpa.study.ch05

import jpa.study.dao.*
import java.util.Date
import javax.persistence.Persistence

fun test() {
    var emf = Persistence.createEntityManagerFactory("jpabook")
    var em = emf.createEntityManager()

    fun createCondition() {
        var product1 = Product()
        product1.title = "good good"
        product1.price = 3000.toDouble()

        var product2 = Product()
        product2.title = "bad bad"
        product2.price = 100.toDouble()

        em.persist(product1)
        em.persist(product2)

        var team = Team()
        team.name = "GoodTeam"
        em.persist(team)

        var member = Member()
        member.firstName = "j"
        member.lastName = "kk"
        member.username = member.getFullName()
        member.setTeam(team)

        em.persist(member)

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

    fun printFromRequestXProducts() {
        var requestItems: MutableList<RequestXProduct>? = em.createQuery("SELECT m FROM RequestXProduct m", javaClass<RequestXProduct>()).getResultList()

        for (item in requestItems?.iterator()) {

            var request = em.find(javaClass<Request>(), item.requestId)
            var product = em.find(javaClass<Product>(), item.productId)
            var member = em.find(javaClass<Member>(), request.memberId)

            println(member)
            println(request)
            println(product)
            println("-----------------------------------------------------------------------")
        }
    }

    fun printFromTeam() {
        var teams = em.createQuery("SELECT m FROM Team m", javaClass<Team>()).getResultList()
        for (team in teams) {
            println(team)
            team.members?.forEach { member -> println(member) }
        }
        println("-----------------------------------------------------------------------")
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

    printFromRequestXProducts()
    printFromTeam()

    em.close()
    emf.close()
}