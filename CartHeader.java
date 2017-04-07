/*
 * File: CartHeader.java
 * Author: Matt Clinard
 * Date: 4/7/2017
 * This program contains methods for all scenes used for the ShoppingCart.java program.
 */
package lab4;
import java.io.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class CartHeader {
  
  public static void readFile(ObservableList<String> data) throws Exception{
    FileReader freader = new FileReader("Bookprices.txt");
    BufferedReader in = new BufferedReader(freader);
    String[] tempRead = new String[100];
    int k = 0;
    int i = 0;
   
    while(in.ready()){
      tempRead[i] = in.readLine();
      i++;
    }
    
    while(tempRead[k] != null){
      data.add(tempRead[k]);
      k++;
    } 
  }
  
 public static void home(Stage primaryStage, MenuBar menuBar, Button shopButton){
   BorderPane homePane = new BorderPane();
   homePane.setTop(menuBar);
   Scene homeScene = new Scene(homePane, 500, 350);
   
   GridPane homeGrid = new GridPane();
   ColumnConstraints column1 = new ColumnConstraints(150,150, Double.MAX_VALUE);
   ColumnConstraints column2 = new ColumnConstraints(150,300, Double.MAX_VALUE);
   column1.setHgrow(Priority.ALWAYS);
   column2.setHgrow(Priority.ALWAYS);
   homeGrid.getColumnConstraints().addAll(column1, column2);
   
   HBox textBox = new HBox();
   Text tfHomeInstructions = new Text("Welcome to Orson Scott Card's Book Store Application!\n"
       + "Here you can find all of the best books written by Mr. Card.\n"
       + "Feel free to browse and checkout when you are ready!\n"
       + "Thanks for your patronage!");
   textBox.getChildren().add(tfHomeInstructions);
   textBox.setAlignment(Pos.CENTER);
   
   homeGrid.add(textBox, 1,0);
   homeGrid.add(shopButton, 0,2);
   homePane.setCenter(homeGrid);
  
    primaryStage.setTitle("Orson Scott Cards' Book Store");
    primaryStage.setScene(homeScene);
    primaryStage.show();
   
 }
 
 public static void shop(MenuBar menuBar, ListView<String> bList, ListView<String> shopList,
     HBox checkoutBox, HBox homeBox, Stage primaryStage){
   BorderPane shopPane = new BorderPane();
        shopPane.setTop(menuBar);
        
        Scene shopScene = new Scene(shopPane, 500, 350);
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints(150,150, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(50);
        ColumnConstraints column3 = new ColumnConstraints(150,150, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2, column3);
        
        //Header for available inventory
        Label bookLabel = new Label ("Available Inventory");
        GridPane.setHalignment(bookLabel, HPos.CENTER);
        gridpane.add(bookLabel, 0, 0);
        
        //Header for user Shopping Cart
        Label userCart = new Label ("Your Shopping Cart");
        gridpane.add(userCart, 2, 0);
        GridPane.setHalignment(userCart, HPos.CENTER);
        
        //Put Inventory and Shopping Cart ListViews into the scenes
        gridpane.add(bList, 0, 1);
        gridpane.add(shopList, 2, 1);
        
        //Add to cart button
        Button sendRightButton = new Button(" > ");
        sendRightButton.setOnAction((ActionEvent event) -> {
            String selection = bList.getSelectionModel().getSelectedItem();
            if (selection != null) {
                bList.getSelectionModel().clearSelection();
                bList.getItems().remove(selection);
                shopList.getItems().add(selection);
            }
        });
        
        //Remove from cart button
        Button sendLeftButton = new Button(" < ");
        sendLeftButton.setOnAction((ActionEvent event) -> {
            String selected = shopList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                shopList.getSelectionModel().clearSelection();
                shopList.getItems().remove(selected);
                bList.getItems().add(selected);
            }
        });
        
             

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(sendRightButton, sendLeftButton);

        gridpane.add(vbox, 1, 1);
        gridpane.add(checkoutBox, 2, 2);
        gridpane.add(homeBox, 0, 2);
        shopPane.setCenter(gridpane);

        GridPane.setVgrow(shopPane, Priority.ALWAYS);
        primaryStage.setTitle("Orson Scott Cards' Book Store - Shop");
        primaryStage.setScene(shopScene);
        primaryStage.show();
        
 }
 
 public static void about(){
   //Set New Stage
        Stage secondary = new Stage();
        
        //Add containers
        Text tfVersion = new Text("Orson Scott Cards' Book Store \n              Version 0.5");
        HBox okBox = new HBox();
        okBox.setAlignment(Pos.CENTER);
        
        //Add stuff to go in containers
        Button btOk = new Button("Ok");
        okBox.getChildren().add(btOk);
        
        //Set borders and button locations
        BorderPane b2Pane = new BorderPane();
        b2Pane.setBottom(okBox);
        
        //Set Scene
        Scene aboutScene = new Scene(b2Pane, 300, 100);
        b2Pane.setCenter(tfVersion);
        
        //Set up stage for viewing
        secondary.setTitle("About");
        secondary.setScene(aboutScene);
        secondary.show();
        
        //If you click the ok button it closes the window
        btOk.setOnAction((me) -> {
            secondary.close();
        });
 }
 
 public static void checkout(MenuBar menuBar, Stage primaryStage, ListView<String> shopList, Button shopButton){
   BorderPane checkoutPane = new BorderPane();
      checkoutPane.setTop(menuBar);
      
      Scene checkoutScene = new Scene(checkoutPane,500, 350);
      GridPane checkoutGrid = new GridPane();
      checkoutGrid.setHgap(10);
      checkoutGrid.setVgap(10);
      ColumnConstraints ccolumn1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
      ColumnConstraints ccolumn2 = new ColumnConstraints(100);
      
      checkoutGrid.add(shopList, 0, 1);
      checkoutGrid.add(shopButton, 0, 2);
      checkoutPane.setCenter(checkoutGrid);
      primaryStage.setTitle("Orson Scott Cards' Book Store - Checkout");
      primaryStage.setScene(checkoutScene);
      primaryStage.show();
 }
}
