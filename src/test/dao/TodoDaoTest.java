package test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ubuntudo.dao.TodoDao;
import ubuntudo.dao.UserDao;
import ubuntudo.model.UserEntity;

public class TodoDaoTest {
	@Test
	public void insertTodo(){
		TodoDao tDao=new TodoDao();
		int insertedTodoRows = tDao.insertTodo(1L, 0L, "todo desu.", "contents desu.", "20150501", 0, 1L);
		
		assertEquals(1, insertedTodoRows);
	}

	@Test
	public void retrieveUser() {
		UserEntity comparisonUser = new UserEntity(1L, "Mark", "a@a", "a");

		UserDao uDao = new UserDao();
		UserEntity retrievedUser = uDao.retrieveUser("a@a", "a");

		assertEquals(comparisonUser.getUid(), retrievedUser.getUid());
		assertEquals(comparisonUser.getName(), retrievedUser.getName());
		assertEquals(comparisonUser.getEmail(), retrievedUser.getEmail());
		assertEquals(comparisonUser.getPasswd(), retrievedUser.getPasswd());
	}
}
