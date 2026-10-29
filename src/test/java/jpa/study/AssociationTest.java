package jpa.study;

import jpa.study.dao.Member;
import jpa.study.dao.Request;
import jpa.study.dao.RequestXProduct;
import jpa.study.dao.Team;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AssociationTest {
    @Test
    public void updatesBothSidesWhenAssociationsAreReassigned() {
        Team firstTeam = new Team();
        Team secondTeam = new Team();
        Member firstMember = new Member();
        Member secondMember = new Member();
        firstMember.setTeam(firstTeam);
        assertTrue(firstTeam.getMembers().contains(firstMember));
        firstMember.setTeam(secondTeam);
        assertFalse(firstTeam.getMembers().contains(firstMember));
        assertTrue(secondTeam.getMembers().contains(firstMember));
        firstMember.setTeam(null);
        assertFalse(secondTeam.getMembers().contains(firstMember));

        Request firstRequest = new Request();
        Request secondRequest = new Request();
        firstRequest.setMember(firstMember);
        assertTrue(firstMember.getRequests().contains(firstRequest));
        firstRequest.setMember(secondMember);
        assertFalse(firstMember.getRequests().contains(firstRequest));
        assertTrue(secondMember.getRequests().contains(firstRequest));

        RequestXProduct item = new RequestXProduct();
        item.setRequest(firstRequest);
        assertTrue(firstRequest.getRequestXProducts().contains(item));
        item.setRequest(secondRequest);
        assertFalse(firstRequest.getRequestXProducts().contains(item));
        assertTrue(secondRequest.getRequestXProducts().contains(item));
    }

    @Test
    public void preservesTheComputedFullName() {
        Member member = new Member();
        member.setFullName("Ada Lovelace");
        assertEquals("Ada", member.getFirstName());
        assertEquals("Lovelace", member.getLastName());
        assertEquals("Ada Lovelace", member.getFullName());
    }
}
