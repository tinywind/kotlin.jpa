package my.demo

import javax.persistence.EntityManager
import javax.persistence.Persistence

fun main(args: Array<String>) {
    fun logic(em: EntityManager) {
        val member = Member()

        member.id = 1L
        member.username = "username1"
        member.age = 11

        em.persist(member)
        em.remove(member)

        member.age = 20

        var findMember = em.find(javaClass<Member>(), 1L)
        println(findMember)

        var members = em.createQuery("SELECT m FROM Member m", javaClass<Member>()).getResultList();

        em.remove(member)
    }

    var emf = Persistence.createEntityManagerFactory("jpabook")
    var em = emf.createEntityManager()
    var tx = em.getTransaction()

    try {
        tx.begin()
        logic(em)
        tx.commit()
    } catch (e: Exception) {
        e.printStackTrace()

        tx.rollback()

    } finally {
        em.close()
    }

    emf.close()

}

fun foo2() {
    fun gcd(x: Int, y: Int): Int {
        var a = x;
        var b = y;

        while (b != 0) {
            var temp = a % b;
            a = b;
            b = temp;
        }
        return Math.abs(a);
    }

    loop@ for (i in 1..10) {
        loop2@ for (j in 1..10) {
            if (gcd(i, j) == 1) {
                println("$i , $j's GCD is 1")
                //                break@loop
                continue@loop
            }
        }
    }

    println(foo())
}

fun foo() {
    var ints = Array(5, { i -> ((i) * (i)) })

    ints.forEach(fun(value: Int) {
        if (value == 0) return@foo
        print(value)
    })
}