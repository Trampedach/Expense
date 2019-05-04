package dk.expense.code;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	Connection con;

	public void openDb() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://",
					"", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDb() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbInsert(String id, String name, String type, ByteArrayInputStream pic) {

		try {
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jesbase", "root", "password1234");

			// create a sql date object so we can use it in our INSERT statement
			// the mysql insert statement
			String query = " insert into testtabel (id, name, type, picture)" + " values (?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setBlob(4, pic);

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
		
		public void selectDb() {

			// start database
			BufferedImage pic = null;
			Blob blob = null;

			try {
//				Connection con = DriverManager.getConnection("jdbc:mysql://mysql88.unoeuro.com:3306/trampedach_dk_db_testbase", "trampedach_dk", "B4JD8FSB");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select id, name, type, picture from testtabel");
				while (rs.next()) {
					blob = rs.getBlob(4);
//					if (blob != null)
//						showImage(pic = blobToImage(blob));
					System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

