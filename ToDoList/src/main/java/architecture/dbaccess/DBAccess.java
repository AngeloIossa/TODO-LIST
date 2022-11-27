package architecture.dbaccess;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import architecture.dao.DAOException;

public class DBAccess {
	private static Connection conn;
	
	public static synchronized Connection getConnection() throws ClassNotFoundException, IOException, DAOException {
		try {
			// Take current thread for synchronization
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			// Read properties from config.properties, connection to OracleDB
			InputStream input = classLoader.getResourceAsStream("properties/config.properties");
			Properties fileProperties = new Properties();
			fileProperties.load(input);
			
			Class.forName(fileProperties.getProperty("jdbcDriver")); // Get driver class
			// Get connection
			conn = DriverManager.getConnection(
					fileProperties.getProperty("jdbcURL"),
					fileProperties.getProperty("username"),
					fileProperties.getProperty("password"));
			conn.setAutoCommit(false); // No autocommit
			return conn;		
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	public static void closeConnection() throws DAOException {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
}
