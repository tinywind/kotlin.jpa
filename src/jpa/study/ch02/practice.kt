package  jpa.study.ch02

import jpa.study.dao.Member
import javax.persistence.EntityManager
import javax.persistence.Persistence

fun test() {
    fun logic(em: EntityManager) {
        val member = Member()

        member.id = 1L
        member.username = "username1"
        member.age = 11

        em.persist(member)

        var findMember = em.find(javaClass<Member>(), 1L)
        println(findMember)
        findMember.age = 20

        var members = em.createQuery("SELECT m FROM Member m", javaClass<Member>()).getResultList()

        em.detach(member)
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