/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import DbConnection.LoginDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StartpgController implements Initializable {

    @FXML
    private Button signinbtn;
    @FXML
    private TextField usernamebox;
    @FXML
    private Text incrrecttxt;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private Text signuptxt;

    Connection conn=null;
    ResultSet res=null;
    PreparedStatement pst=null;
    @FXML
    private TextField emailbox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signinbtnaction(ActionEvent event) throws IOException {

        incrrecttxt.setVisible(false);
 
        conn=LoginDb.ConnectDb();
        String sql;
        sql = "Select * from user_information where User_Name=? and Email=? and Password=?";
        String userName=usernamebox.getText();
        String password=passwordbox.getText();
        String mailAdress=emailbox.getText();
        try{
            
           pst=conn.prepareStatement(sql);
           pst.setString(1,userName );
           pst.setString(2,mailAdress);
           pst.setString(3,password );
           
           res= pst.executeQuery();
           
           if(res.next())
           {
               
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("project1.fxml"));
                Parent root = loader.load();
                
                Scene tableViewScene = new Scene(root);
           
                //access the controller and call a method
                Project1Controller controller = loader.getController();
                controller.showProfileInfo(userName,mailAdress);
                
                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(tableViewScene);
                window.show();            
           }else
               incrrecttxt.setVisible(true);
               //JOptionPane.showMessageDialog(null,"Invalid Username or Password");
               
           
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,e);
            
        }
    
    }

    @FXML
    private void usernameboxactn(ActionEvent event) {
    }

    @FXML
    private void passwordboxact(ActionEvent event) {
    }

    @FXML
    public void signupact(MouseEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
          Stage primaryStage=new Stage();
         Parent root=FXMLLoader.load(getClass(). getResource("signuppg.fxml"));
         Scene src=new Scene(root);
          primaryStage.setTitle("e-library");
          primaryStage.setScene(src);
           primaryStage.show();

        
    }

}
