package  jpa.study.ch03

import jpa.study.dao.Member
import javax.persistence.Persistence

fun test() {
    var emf = Persistence.createEntityManagerFactory("jpabook")

    fun createMember(id: Long, username: String): Member {
        var em = emf.createEntityManager()
        var tx = em.getTransaction()
        tx.begin()

        var m = Member()

//        m.id = id
        m.username = username

        em.persist(m)
        tx.commit()
        em.close()
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

        em.remove(mergeMember)
        tx.commit()
        em.close()
    }

    var m = try {
        createMember(5L, "username5")
    } catch(e: Exception) {
        e.printStackTrace()
        null
    }

    m ?: throw IllegalStateException("m is null")
    m.username = "changed username2"
    mergeMember(m)
}