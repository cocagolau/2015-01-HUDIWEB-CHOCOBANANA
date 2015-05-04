package support;

public class Qrys {
	// for user registration
	public static String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";
	
	// for user validation
	public static String VALIDATE_USER = "SELECT uid, email FROM user WHERE email = ?";

	// for login
	public static String RETRIEVE_USER = "SELECT uid, name, email, passwd FROM user WHERE email = ? AND passwd = ?";

	// for creation of guild
	public static String INSERT_GUILD = "insert into guild (guild_name, leader_id, status) values (?, ?, '123123')";

	// for creation of party
	public static String INSERT_PARTY = "insert into party (gid, leader_id, party_name, status) values (?, ?, ?, '123123')";

	// for user registration to guild
	public static String INSERT_USER_TO_GUILD = "insert into user_guild_relation (uid, gid, status) values (?, ?, '123123')";

	// for getting last auto increment id
	public static String GET_LAST_ID = "select LAST_INSERT_ID() as last_id";

	// for searching guild list
	public static String RETRIEVE_GUILD_LIST = "select gid, leader_id, guild_name, status FROM guild where guild_name like ?";
	
	// for searching party list
	public static String RETRIEVE_PARTY_LIST = "select pid, gid, leader_id, party_name, status from party where party_name like ?";
	
	// for party update
	public static String UPDATE_PARTY = "update party set leader_id = ?, party_name = ?, status = ? where pid = ?";
	
	// for user registration to party
	public static String INSERT_USER_TO_PARTY = "insert into user_party_relation (uid, pid, status) values (?, ?, '123123')";

	// for party update
	public static String UPDATE_GUILD = "update guild set leader_id = ?, guild_name = ?, status = ? where gid = ?";
	
	// for retrieve party in guild
	public static String RETRIEVE_PARTY_IN_GUILD = "select pid, gid, leader_id, party_name, status from party where gid = ?";

	public static String makeLikeParam(String param) {
		return "%" + param + "%";
	}
}