package ubuntudo.dao;

import java.sql.SQLException;

import ubuntudo.JDBCManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoDao extends JDBCManager {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public int insertTodo(long assigner_id, long pid, String title, String contents, String dueDate, int completed, long last_editer_id) {
		logger.info("---->TodoDao.insertTodo");

		conn = getConnection();
		int insertedRows = 0;
		String sql = "insert into todo (assigner_id, pid, title, contents, duedate, completed, last_editer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, assigner_id);
			pstmt.setLong(2, pid);
			pstmt.setString(3, title);
			pstmt.setString(4, contents);
			pstmt.setString(5, dueDate);
			pstmt.setInt(6, completed);
			pstmt.setLong(7, last_editer_id);
			insertedRows = pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.error("DB insertTodo Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		logger.info("<----TodoDao.insertTodo");
		return insertedRows;
	}
}

// `tid, assigner_id, pid` INT NULL DEFAULT NULL,
// `title` VARCHAR(255) NOT NULL,
// `contents` VARCHAR(511) NULL,
// `duedate` DATETIME NULL,
// `completed` TINYINT NOT NULL,
// `last_editer_id` INT NOT NULL,