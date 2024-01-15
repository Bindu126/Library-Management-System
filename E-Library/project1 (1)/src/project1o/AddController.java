/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import DbConnection.BookListDb;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddController implements Initializable {

    @FXML
    private TextField bookNameTxtBox;
    @FXML
    private TextField authrNameTxtBox;
    @FXML
    private TextField catgryTxtBox;
    @FXML
    private TextField fileLocTextBox;
    
    //btn
    @FXML
    private Button browseBtn;
    @FXML
    private Button cancleBtn;
    @FXML
    private Button saveBtn;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    BookList book = null ;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void textFieldAct(ActionEvent event) {
        
    }

    @FXML
    private void buttonAction(ActionEvent event) throws IOException {
        
        if (event.getSource()==browseBtn)
        {
            FileChooser fc=new FileChooser();
            fc.setInitialDirectory(new File("C:\\Users\\USER\\Documents\\project1 (1)\\project1\\src\\Book list"));
            File selectedfile= fc.showOpenDialog(null);
            fc.getExtensionFilters().addAll( new ExtensionFilter("PDF Files","*.pdf"));
            
            if (selectedfile!= null)
            {            
                fileLocTextBox.setText(selectedfile.getAbsolutePath());
                bookNameTxtBox.setText(selectedfile.getName());
            }
            else{
                System.out.println("The file is not valid..!!");
            }
            
        }
        else if (event.getSource()==cancleBtn)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass(). getResource("project1.fxml"));
            Scene src=new Scene(root);
            primaryStage.setTitle("e-library");
            primaryStage.setScene(src);
            primaryStage.show();
            
        }
        else if(event.getSource()==saveBtn)
        {
            connection = BookListDb.getConnect();
        
            String bookName = bookNameTxtBox.getText();
            String authorName = authrNameTxtBox.getText();       
            String category = catgryTxtBox.getText();
            String filePath = fileLocTextBox.getText();
            
            if (bookName.isEmpty() || authorName.isEmpty() || category.isEmpty() || filePath.isEmpty()) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please, Fill All DATA.");
                alert.showAndWait();

            } 
            else
            {
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage=new Stage();
                Parent root=FXMLLoader.load(getClass(). getResource("project1.fxml"));
                Scene src=new Scene(root);
                primaryStage.setTitle("e-library");
                primaryStage.setScene(src);
                primaryStage.show();
            
                insert();
                clean();

        }        
    }
}
    
    private void clean()
    {
        bookNameTxtBox.setText(null);
        authrNameTxtBox.setText(null);
        catgryTxtBox.setText(null);
        fileLocTextBox.setText(null);
        
    }
    
    private void insert()
    {
        connection = BookListDb.getConnect();
        query = "INSERT INTO `book information`( `BookName`, `AuthorName`, `Category`, `FilePath`) VALUES (?,?,?,?)";
          try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, bookNameTxtBox.getText());
            preparedStatement.setString(2, authrNameTxtBox.getText());
            preparedStatement.setString(3, catgryTxtBox.getText());
            preparedStatement.setString(4, fileLocTextBox.getText());
            preparedStatement.execute();
            
            JOptionPane.showMessageDialog(null,"Saved");
            System.out.println("Data saved..!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
    

