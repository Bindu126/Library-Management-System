/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import DbConnection.LoginDb;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SignuppgController implements Initializable {

    @FXML
    private TextField usernametxtbox;
    @FXML
    private TextField emailtxtbox;
    @FXML
    private TextField passtxtbox;
    @FXML
    private TextField cnfrmpasstxtbox;
    @FXML
    private Button creataccountbtn;

    Connection conn=null;
    ResultSet res=null;
    PreparedStatement pst=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void usernametxtboxact(ActionEvent event) {
    }

    @FXML
    private void emailtxtboxact(ActionEvent event) {
    }


    

    @FXML
    private void passtxtboxact(ActionEvent event) {
    }

    @FXML
    private void cnfrmpasstxtboxact(ActionEvent event) {
    }

    @FXML
    private void creataccountbtnact(ActionEvent event) throws IOException {
       
        conn=LoginDb.ConnectDb();
        String sql="insert into user_information(User_Name,Email,Password) values(?,?,?)";
      
        try{           
           pst=conn.prepareStatement(sql);
           pst.setString(1,usernametxtbox.getText() );
           pst.setString(2,emailtxtbox.getText() );
           pst.setString(3,passtxtbox.getText() );
           pst.execute();
           JOptionPane.showMessageDialog(null,"Saved");
           
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }  
        
        if (event.getSource()==creataccountbtn)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("startpg.fxml"));
            Scene src=new Scene(root);
            primaryStage.setTitle("e-library");
            primaryStage.setScene(src);
            primaryStage.show();
          
        }
    }

    @FXML
    private void backbtnAction(ActionEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
         Stage primaryStage=new Stage();
         Parent root=FXMLLoader.load(getClass().getResource("startpg.fxml"));
          Scene src=new Scene(root);
          primaryStage.setTitle("e-library");
          primaryStage.setScene(src);
           primaryStage.show();
       
        
    }
    
}
