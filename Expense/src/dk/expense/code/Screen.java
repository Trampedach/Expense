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
        
        Button btn2 = new Button();
        btn2.setText(" Get all ");
        
               
        TextField  box1 = new TextField ();
        box1.setText("Name");
        
        myComboBox = new ComboBox<String>();
        myComboBox.getItems().addAll("Restaurant","Taxi","Train","Plane","Fun");
        myComboBox.setEditable(false);
        myComboBox.getSelectionModel().selectFirst();
        
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
        		db = new Database();

        		Reciept r = new Reciept();

          		r.readImage();

          		db.openDb();
          		db.dbInsert(form.id.getId(), name, type, form.imageToBlob(r.getImg()));

        		db.closeDb();
            }
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Select all");
                
                BufferedImage b = null;

        		form.id = new Id();
        		form.db = new Database();

        		Reciept r = new Reciept();

          		form.db.openDb();
        		form.db.selectDb();
        		form.db.closeDb();
            }
        });
 
        GridPane root = new GridPane();
        root.add(btn,1,3);
        root.add(box1,1,2);
        root.add(myComboBox, 2, 2);
        root.add(btn2,2,3);
        
        primaryStage.setScene(new Scene(root, 250, 100));
        primaryStage.show();
	}
	
}

