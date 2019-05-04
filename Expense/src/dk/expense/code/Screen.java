package dk.expense.code;

import java.awt.image.BufferedImage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Screen extends Form{

	
	public ComboBox<String> myComboBox;
	public String type;
	public String name;
	public Database db;
	public Form form;
	
	public void setScreen(Stage primaryStage) {
		
		form = new Form();
		primaryStage.setTitle("Form");
        Button btn = new Button();
        btn.setText(" Save ");
               
        TextField  box1 = new TextField ();
        box1.setText("Name");
        
        myComboBox = new ComboBox<String>();
        myComboBox.getItems().addAll("Restaurant","Taxi","Train","Plane","Fun");
        myComboBox.setEditable(false);       
        
        myComboBox.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	
            	type = myComboBox.getValue();
            	System.out.println(type);
            }
        });
        
        box1.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            	name = box1.getText();
            	System.out.println(name);
            }
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save");
                
                BufferedImage b = null;

        		form.id = new Id();
        		form.db = new Database();

        		Reciept r = new Reciept();

          		r.readImage();

          		form.db.openDb();
          		form.db.dbInsert(form.id.getId(), name, type, form.imageToBlob(r.getImg()));

        		form.db.selectDb();
        		form.db.closeDb();
            }
        });
 
        GridPane root = new GridPane();
        root.add(btn,2,5);
        root.add(box1,2,1);
        root.add(myComboBox, 2, 2);
        
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show();
	}
	
}

