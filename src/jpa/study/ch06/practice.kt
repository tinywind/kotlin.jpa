package  jpa.study.ch06

import jpa.study.dao.*
import java.util.Date
import javax.persistence.EntityManager
import javax.persistence.Persistence

fun createCondition(em: EntityManager) {
    var product1 = Product()
    var product2 = Product()
    var team = Team()
    var member1 = Member()
    var member2 = Member()
    var request = Request()
    var requestXProduct1 = RequestXProduct()
    var requestXProduct2 = RequestXProduct()
    var locker = Locker()
    var key = Key()
    var role1 = Role()
    var role2 = Role()


    product1.title = "good good"
    product1.price = 3000.toDouble()
    product2.title = "bad bad"
    product2.price = 100.toDouble()
    team.name = "GoodTeam"
    member1.firstName = "j"
    member1.lastName = "kk"
    member1.username = member1.getFullName()
    member1.type = Member.MemberType.ADMIN
    member1.setTeam(team)
    member2.username = "aa   aaaa"
    request.setMember(member1)
    request.createdAt = Date()
    requestXProduct1.setRequest(request)
    requestXProduct1.requestId = 1
    requestXProduct1.productId = 1
    //    requestXProduct1.product = product1
    requestXProduct2.setRequest(request)
    requestXProduct2.requestId = 1
    requestXProduct2.productId = 2
    //    requestXProduct2.product = product2
    product1.requestXProducts.add(requestXProduct1)
    product2.requestXProducts.add(requestXProduct2)
    locker.name = "member1's locker"
    member1.locker = locker
    locker.member = member1     // todo!! not work below line. reverse referring
    key.name = "locker's key"
    key.locker = locker
    locker.key = key    // todo!! not work below line. reverse referring
    role1.name = "role1"
    role2.name = "role2"

    role1.addMember(member1)
    role1.addMember(member2)
    member2.addRole(role2)

    em.persist(product1)
    em.persist(product2)
    em.persist(team)
    em.persist(member1)
    em.persist(member2)
    em.persist(request)
    em.persist(requestXProduct1)
    em.persist(requestXProduct2)
    em.persist(locker)
    em.persist(key)
    em.persist(role1)
    em.persist(role2)
}

fun test() {
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

    em.close()
    em = emf.createEntityManager()

    fun printFromRequestXProducts() {
        println(" printFromRequestXProducts -----------------------------------------------------------------------")
        var requestItems = em.createQuery("SELECT m FROM RequestXProduct m", javaClass<RequestXProduct>()).getResultList()
        requestItems?.forEach { println(it) }
        println("-----------------------------------------------------------------------")
    }

    fun printFromProducts() {
        println(" printFromProducts -----------------------------------------------------------------------")
        var products = em.createQuery("SELECT m FROM Product m", javaClass<Product>()).getResultList()
        products?.forEach { println(it) }
        println("-----------------------------------------------------------------------")
    }

    fun printOneToOneRelation() {
        println(" printOneToOneRelation -----------------------------------------------------------------------")
        var lockers = em.createQuery("SELECT m FROM Locker m", javaClass<Locker>()).getResultList()
        lockers?.forEach {
            println(it)
            println(it.member)
        }
        println("-----------------------------------------------------------------------")
    }

    fun printManyToManyRelation() {
        println(" printManyToManyRelation -----------------------------------------------------------------------")
        var roles = em.createQuery("SELECT m FROM Role m", javaClass<Role>()).getResultList()
        roles?.forEach {
            println(it)
            it.members.forEach { println(it) }
        }
        println("-----------------------------------------------------------------------")
    }

    fun printManyToManyRelationReverse() {
        println(" printManyToManyRelationReverse -----------------------------------------------------------------------")
        var members = em.createQuery("SELECT m FROM Member m", javaClass<Member>()).getResultList()
        members?.forEach {
            println(it)
            it.roles.forEach { println(it) }
        }
        println("-----------------------------------------------------------------------")
    }

    printFromRequestXProducts()
    printFromProducts()
    printOneToOneRelation()
    printManyToManyRelation()
    printManyToManyRelationReverse()

    em.close()
    emf.close()
}

fun test2() {
    var emf = Persistence.createEntityManagerFactory("jpabook")
    var em = emf.createEntityManager()

    fun condition() {
        var c1 = Country()
        var c2 = Country()
        var c3 = Country()
        var l1 = Language()
        var l2 = Language()
        var l3 = Language()
        var x1 = CountryXLanguage()
        var x2 = CountryXLanguage()
        var x3 = CountryXLanguage()
        var x4 = CountryXLanguage()
        var x5 = CountryXLanguage()

        c1.name = "korea"
        c2.name = "japan"
        c3.name = "china"
        l1.name = "korean"
        l2.name = "japanese"
        l3.name = "chinese"
        x1.country = c1
        x1.language = l1
        x2.country = c2
        x2.language = l2
        x3.country = c3
        x3.language = l3
        x4.country = c3
        x4.language = l1
        x5.country = c3
        x5.language = l2

        em.persist(c1)
        em.persist(c2)
        em.persist(c3)
        em.persist(l1)
        em.persist(l2)
        em.persist(l3)
        em.persist(x1)
        em.persist(x2)
        em.persist(x3)
        em.persist(x4)
        em.persist(x5)
    }

    var tx = em.getTransaction()
    try {
        tx.begin()
        condition()
        tx.commit()
    } catch(e: Exception) {
        e.printStackTrace()
        if (tx.isActive())
            tx.rollback()
        return
    }

    em.close()
    em = emf.createEntityManager()

    var countryXLanguages = em.createQuery("SELECT m FROM CountryXLanguage m", javaClass<CountryXLanguage>()).getResultList()
    var countries = em.createQuery("SELECT m FROM Country m where m.name=:countryName", javaClass<Country>())
            .setParameter("countryName", "china")
            .getResultList()

    em.close()
    emf.close()
}