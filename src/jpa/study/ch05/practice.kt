package  jpa.study.ch05

import jpa.study.dao.*
import java.util.Date
import javax.persistence.EntityManager
import javax.persistence.Persistence

fun createCondition(em: EntityManager) {
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
    member.setTeam(team)

    em.persist(member)

    member.type = Member.MemberType.ADMIN
    print(member.firstName)

    var request = Request()
    request.setMember(member)
    request.createdAt = Date()

    em.persist(request)

    var requestXProduct1 = RequestXProduct()
    requestXProduct1.setRequest(request)
//    requestXProduct1.product = product1

    var requestXProduct2 = RequestXProduct()
    requestXProduct2.setRequest(request)
//    requestXProduct2.product = product2

    em.persist(requestXProduct1)
    em.persist(requestXProduct2)

    product1.requestXProducts.add(requestXProduct1)
    product2.requestXProducts.add(requestXProduct2)
}

fun test() {
    var emf = Persistence.createEntityManagerFactory("jpabook")
    var em = emf.createEntityManager()

    fun printFromRequestXProducts() {
        var requestItems: MutableList<RequestXProduct>? = em.createQuery("SELECT m FROM RequestXProduct m", javaClass<RequestXProduct>()).getResultList()

        for (item in requestItems?.iterator()) {
            var request = item.requestId?.let { em.find(javaClass<Request>(), item.requestId)  }
            var product = item.productId?.let { em.find(javaClass<Product>(), item.productId) }
            var member = request?.let { em.find(javaClass<Member>(), request!!.memberId) }

            println(member)
            println(request)
            println(product)
        }
        println("-----------------------------------------------------------------------")
    }

    fun printFromTeam() {
        var teams = em.createQuery("SELECT m FROM Team m", javaClass<Team>()).getResultList()
        for (team in teams) {
            println(team)
            team.members.forEach { member -> println(member) }
        }
        println("-----------------------------------------------------------------------")
    }

    fun printByJqpl() {
        var members = em.createQuery("SELECT m FROM Member m LEFT OUTER JOIN m.team t WHERE t.name = :teamName", javaClass<Member>())
                .setParameter("teamName", "GoodTeam")
                .getResultList()

        members.forEach { m -> println(m) }
        println("-----------------------------------------------------------------------")
    }

    var tx = em.getTransaction()
    try {
        tx.begin()
        createCondition(em)
        tx.commit()
    } catch(e: Exception) {
        e.printStackTrace()
        if (tx.isActive())
            tx.rollback()
        return
    }

    em.close()
    em = emf.createEntityManager()

    printFromRequestXProducts()
    printFromTeam()
    printByJqpl()

    em.close()
    emf.close()
}

fun mission() {
    var emf = Persistence.createEntityManagerFactory("jpabook")
    var em = emf.createEntityManager()

    var tx = em.getTransaction()
    try {
        tx.begin()
        createCondition(em)
        tx.commit()
    } catch(e: Exception) {
        e.printStackTrace()
        if (tx.isActive())
            tx.rollback()
        return
    }

    fun printFromMember() {
        var members = em.createQuery("SELECT m FROM Member m LEFT OUTER JOIN m.team t WHERE t.name = :teamName", javaClass<Member>())
                .setParameter("teamName", "GoodTeam")
                .getResultList()

        members.forEach {
            println(it)
            it.requests.forEach {
                it.requestXProducts.forEach {
                    println(it)
                }
            }
        }

        println("-----------------------------------------------------------------------")
    }

    printFromMember()

    em.close()
    emf.close()
}