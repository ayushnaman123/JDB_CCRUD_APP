package in.ineuron.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private JdbcUtil() {

	}

	static {

		try {
			// Step1 Load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getJdbcConnection() throws SQLException, IOException {

		HikariConfig config = new HikariConfig("src/in/ineuron/properties/application.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
			return dataSource.getConnection();
		
	}

}
