package  jpa.study.ch08

import jpa.study.dao.Member
import jpa.study.dao.Team
import javax.persistence.EntityManager
import javax.persistence.Persistence

fun createCondition(em: EntityManager) {
    var aTeam = Team()
    aTeam.name = "a team";

    var aMember = Member()
    aMember.username = "aaa bb"
}

fun explore(em: EntityManager) {
    // proxy object
    var member = em.getReference(javaClass<Member>(), 1);
    // no effect
    member.id
    // init proxy object
    member.username
    // check initialized proxy
    em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member)
    // initialized proxy's class name
    member.javaClass.getName()

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