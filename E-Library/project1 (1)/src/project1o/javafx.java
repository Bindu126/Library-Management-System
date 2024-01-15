/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class javafx extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        
      Parent root=FXMLLoader.load(getClass(). getResource("startpg.fxml"));
        Scene src=new Scene(root);
        primaryStage.setTitle("e-library");
         primaryStage.setScene(src);
          primaryStage.show();
    }
     public static void main(String args[]){
        launch(args);
    }   
}