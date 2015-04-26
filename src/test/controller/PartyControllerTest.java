package test.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.Party.PartyController;
import ubuntudo.model.PartyEntity;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class PartyControllerTest {

	@Autowired
	private PartyController partyController;

	@Test
	public void insertPartyControllerTest() {
		long gid = 3l;
		long leaderId = 13l;
		String partyName = "Mark Party13";

		assertEquals(1, partyController.insertPartyController(gid, leaderId, partyName));
	}

	@Test
	public void retrievePartyListSearchTest() {
		String partyName = "13";
		List<PartyEntity> partyList = partyController.retrievePartyListSearchController(partyName);
		assertNotNull(partyList);

		Gson gson = new Gson();
		String partyListJson = gson.toJson(partyList);
		System.out.println(partyListJson);
	}

	@Test
	public void updatePartyControllerTest() {
		long pid = 3l;
		long leaderId = 105l;
		String partyName = "Mark Party EDITED";
		String status = "830408";

		assertEquals(1, partyController.updatePartyController(pid, leaderId, partyName, status));
	}
}