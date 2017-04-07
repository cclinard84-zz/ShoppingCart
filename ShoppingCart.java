/*
 * File: ShoppingCart.java
 * Author: Matt Clinard
 * Date: 4/6/2017
 * This program
 */
package lab4;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShoppingCart extends Application {
  
  
  @Override
  public void start(Stage primaryStage)throws Exception{
    
    //////////////////////////////////////////////////////////////////////
    //Creation of Observable Lists and List Views
    //////////////////////////////////////////////////////////////////////
    ObservableList<String> data = FXCollections.observableArrayList();
    ListView<String> bList = new ListView<String>();
    
    ObservableList<String> user = FXCollections.observableArrayList();
    ListView<String> shopList = new ListView<String>();
    
    /////////////////////////////////////
    //Time to read the file
    /////////////////////////////////////
    CartHeader.readFile(data);
    
    /////////////////////////////////////////////////////////////////
    //Creation of menu bar and subsequent buttons.
    /////////////////////////////////////////////////////////////////
    MenuBar menuBar = new MenuBar();
    Pane pane = new Pane();
    pane.getChildren().add(menuBar);
    
    //Creation of menu buttons
    Menu menuFile = new Menu("File");
    Menu menuHelp = new Menu("Help");
    MenuItem menuExit = new MenuItem("Exit");
    MenuItem About = new MenuItem("About");
    
    //Add buttons to corresponding Menus
    menuFile.getItems().addAll(menuExit);
    menuHelp.getItems().addAll(About);
    menuBar.getMenus().addAll(menuFile, menuHelp);
    
    
    /////////////////////////////////////////////////////////////////
    //ListView box Creation
    /////////////////////////////////////////////////////////////////
    
    //Book Inventory List
    VBox listView = new VBox();
    Label label = new Label();
    listView.getChildren().addAll(bList,label);
    label.setFont(Font.font("Verdana", 20));
    
    //Populate bList listview
    bList.setItems(data);
    
    //Set size of List View window
    bList.setMaxWidth(250);
    bList.setPrefHeight(232);
    
    //Empty Shopping Cart List
    VBox shopView = new VBox();
    Label label2 = new Label();
    shopView.getChildren().addAll(shopList, label2);
    label2.setFont(Font.font("Verdana", 20));
    
    shopList.setMaxWidth(250);
    shopList.setItems(user);
    shopList.setPrefHeight(232);
    
    
    /////////////////////////////////////////////////////////////////
    //Creation of buttons to use with different scenes.
    /////////////////////////////////////////////////////////////////
    
    //Creation of Shop button
    HBox shopBox = new HBox();
    shopBox.setSpacing(0);
    Button shopButton = new Button("Shop");
    shopBox.getChildren().add(shopButton);
    
    //Creation of Checkout Button
    HBox checkoutBox = new HBox();
    checkoutBox.setAlignment(Pos.BASELINE_RIGHT);
    Button checkoutButton = new Button("Checkout");
    checkoutBox.getChildren().add(checkoutButton);
    
    //Creation of Home Button
    HBox homeBox = new HBox();
    Button homeButton = new Button("Home");
    homeBox.getChildren().add(homeButton);

      
    ////////////////////////////////////////////////////////////////
    //Sets the main window
    ////////////////////////////////////////////////////////////////
    CartHeader.home(primaryStage, menuBar, shopButton);
    
    
    ///////////////////////////////////////////////////////////////
    //Event Handlers to take action when you hit a button.
    ///////////////////////////////////////////////////////////////
    
    //Shop Button Action
    shopButton.setOnAction((ActionEvent e) -> {
       CartHeader.shop(menuBar, bList, shopList, checkoutBox, homeBox, primaryStage);
    });
    
    //Menu -> exit Button Action
    menuExit.setOnAction((e) -> {
        System.exit(0);
    });
    
    //About Button Action
    About.setOnAction((e) -> {
        CartHeader.about();
    });
    
    //Home Button Action
    homeButton.setOnAction((e) -> {
      CartHeader.home(primaryStage, menuBar, shopButton);
    });
    
    //Checkout Button Action
    checkoutButton.setOnAction((e)->{
     CartHeader.checkout(menuBar, primaryStage, shopList, shopButton);
    });
  }
  public static void main(String[] args) throws Exception{
    launch(args);
  }
    
}
