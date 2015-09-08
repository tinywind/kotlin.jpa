package  jpa.study.ch07

import javax.persistence.EntityManager
import javax.persistence.Persistence

fun createCondition(em: EntityManager) {
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


    em.close()
    emf.close()
}