package model;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection   {
	String JDBC_URL;
	String DB_USER;
	String DB_PASS;


	private static GetConnection theInstance;
	private GetConnection() throws SQLException {
		try(Reader fr = new FileReader("C:\\Users\\owner\\Downloads\\pleiades\\workspace\\docoTsubu\\WebContent\\WEB-INF\\properties.properties");) {
		Properties p = new Properties();
		p.load(fr);
		JDBC_URL = p.getProperty("JFBC_URL");
		DB_USER = p.getProperty("DB_USER");
		DB_PASS = p.getProperty("DB_PASS");
		Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS);
		} catch (Exception e) {
			System.out.println("エラーが発生しました");
		}
	}
	public static GetConnection getInstance() throws SQLException {
		if(theInstance == null) {
			theInstance = new GetConnection();
		}
		return theInstance;
	}
}
