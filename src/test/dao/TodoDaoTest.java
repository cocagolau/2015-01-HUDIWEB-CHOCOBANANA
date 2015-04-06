package test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ubuntudo.dao.TodoDao;

public class TodoDaoTest {
	@Test
	public void retrieveLatestTid() {
		TodoDao tDao = new TodoDao();
		long latestTid = tDao.retrieveLatestTid();

		assertEquals(11l, latestTid);
	}

	@Test
	public void insertTodo() {
		TodoDao tDao = new TodoDao();
		int insertedTodoRows = tDao.insertTodo(1L, 0L, "todo desu.", "contents desu.", "20150501", 0, 1L);

		assertEquals(1, insertedTodoRows);
	}

	@Test
	public void insertTodoUserRelation() {
		TodoDao tDao = new TodoDao();
		int insertedTodoUserRelationRows = tDao.insertTodoUserRelation(11, 1, "0");

		assertEquals(1, insertedTodoUserRelationRows);
	}
}
