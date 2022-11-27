package test.architecture.dbaccess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import architecture.dbaccess.DBAccess;

class DBAccessTest {
	private static Connection conn;
	
	@Test
	void test() {
		try {
			conn =  DBAccess.getConnection();
			assertNotNull(conn);
			System.out.println("Connection to db ok!");
		} catch (SQLException | ClassNotFoundException | IOException exc) {
			exc.printStackTrace();
			fail("Cause: " + exc.getMessage());
		}
	}

}
