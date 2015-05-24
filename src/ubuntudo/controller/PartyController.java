package ubuntudo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.biz.PartyBiz;
import ubuntudo.model.PartyEntity;

@Controller
@RequestMapping(value = "/party")
public class PartyController {

	@Autowired
	PartyBiz pbiz;

	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpSession session, Model model) {
		System.out.println(session.getAttribute("user"));
		return "personal";
	}

	// creates a party.
	// also add the creator to the party which is just created.
	@RequestMapping(method = RequestMethod.POST)
	public int insertNewPartyController(@RequestParam("gid") long gid, @RequestParam("leaderId") long leaderId, @RequestParam("partyName") String partyName) {
		return pbiz.insertNewPartyBiz(new PartyEntity(gid, leaderId, partyName));
	}
	
	// add a user to a party.
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public int insertUserToPartyController(@RequestParam("partyId") long partyId, @RequestParam("userId") long userId) {
		return pbiz.insertUserToExistingPartyBiz(partyId, userId);
	}

	//retrieves a list of parties containing the parameter in the name of the party.
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<PartyEntity> retrievePartyListSearchController(@RequestParam("partyName") String partyName) {
		return pbiz.retrievePartyListSearchBiz(partyName);
	}

	// update the information of a party.
	@RequestMapping(method = RequestMethod.PUT)
	public int updatePartyController(@RequestParam("pid") long pid, @RequestParam("leaderId") long leaderId, @RequestParam("partyName") String partyName,
			@RequestParam("status") String status) {
		return pbiz.updatePartyBiz(new PartyEntity(pid, leaderId, partyName, status));
	}

	// 나중에 다시 uri 생각해보기
	// retrieves a list of parties that are contained in a guild.
	@RequestMapping(value = "/guild", method = RequestMethod.GET)
	public List<Map<String, Object>> retrievePartyInGuildListController(@RequestParam("uid") long uid, @RequestParam("gid") long gid) {
		return pbiz.retrievePartyInGuildListBiz(uid, gid);
	}
	
	// retrieve a list of all the parties that are included in the every guild of particular user.
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> retrieveMyPartyListController(@RequestParam("uid") long uid) {
		return pbiz.retrievePartyListOfMyGuildsBiz(uid);
	}
}