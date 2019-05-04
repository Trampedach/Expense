package dk.expense.code;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.application.Application;
import javafx.stage.Stage;

public class Form  extends Application{

	Id id;
	ArrayList<Reciept> recieptList;

	public static void main(String[] args) {

		launch(args);
	}

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

	public void showImage(BufferedImage img) {

		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
	}

}
