package dk.expense.code;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.application.Application;
import javafx.stage.Stage;

public class Form  extends Application{

	Id id;
	ArrayList<Reciept> recieptList;
	Connection con;
	Database db;

	public static void main(String[] args) {

		launch(args);
	}

	// slut database

	@Override
    public void start(Stage primaryStage) {
    	
		Screen screen = new Screen();
		screen.setScreen(primaryStage);
		
		
    }
	
	
	@Override
	public String toString() {

		String text;

		text = id.getId();

		return text;
	}

	public ByteArrayInputStream imageToBlob(BufferedImage pic) {

		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			ImageIO.write(pic, "png", baos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (Exception e) {
			}
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		return bais;
	}

	public BufferedImage blobToImage(Blob b) {

		BufferedImage pic = null;

//		ResultSet rs = stmt.executeQuery(<Your Query SQL>);  
		java.sql.Blob blob = b;
		InputStream in = null;
		try {
			in = blob.getBinaryStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pic = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pic;
	}

	public void openDb() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://", "", "");
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
//			Connection con = DriverManager.getConnection("jdbc:mysql://mysql88.unoeuro.com:3306/trampedach_dk_db_testbase", "trampedach_dk", "B4JD8FSB");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select id, name, type, picture from testtabel");
			while (rs.next()) {
				blob = rs.getBlob(4);
				if (blob != null)
					showImage(pic = blobToImage(blob));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void showImage(BufferedImage img) {

		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
	}

}
