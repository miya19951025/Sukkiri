package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	// データベース接続に使用する情報
		private final String JDBC_URL =
				"jdbc:h2:tcp://localhost/~/docoTsubu";
		private final String DB_USER = "sa";
		private final String DB_PASS= "";

		public List<User> findAll() {
			List<User> userList = new ArrayList<>();

			//データベース接続

			try(Connection conn = DriverManager.getConnection(
					JDBC_URL, DB_USER, DB_PASS)) {
				// SELECT文の準備
				String sql =
						"SELECT NAME, PASS, ID FROM USER ORDER BY ID DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SELECTを実行
				ResultSet rs = pStmt.executeQuery();

				// SLECT文の結果をArrayListに格納
				while (rs.next()) {
					String name = rs.getString("NAME");
					String pass = rs.getString("PASS");
					String id = rs.getString("ID");
					User user = new User(name, pass, id);
					userList.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return userList;
		}
		public boolean create(User user) {
			// データベース接続
			try(Connection conn = DriverManager.getConnection(
					JDBC_URL, DB_USER, DB_PASS)) {
				// INSERT文の準備
				String sql = "INSERT INTO MUTTER(NAME,TEXT) VALUES(?,?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				//INSERT文中の「？」に使用する値を設定しSQLを完成
				pStmt.setString(1, user.getName());
				pStmt.setString(2, user.getPass());
				pStmt.setString(3, user.getId());
				//INSERT文を実行(resultには追加された行数が代入される)
				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			 	}	catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
		}
	}

