package dk.expense.code;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Reciept {

	BufferedImage img = null;

	void readImage(String path) {

		try {
//			img = ImageIO.read(new File("C:/Users/jespe/Pictures/aceclub.png"));
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showImage() {
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
	
}
