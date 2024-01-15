/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SelectController implements Initializable {

    
    @FXML
    private Button openPdfBtn;
    @FXML
    private Button cancleBtn;
    @FXML
    private Label bookNameLabel;
    @FXML
    private Label authorNameLabel;
    @FXML
    private Label categoryLabel;
    
    
    private  BookList selectedBook;
    public String filePath;
  
     public void showBookInfo(BookList person)
    {
        selectedBook = person;
        bookNameLabel.setText(selectedBook.getBookName());
        authorNameLabel.setText(selectedBook.getAuthorName());
        categoryLabel.setText(selectedBook.getCategory());
        filePath=selectedBook.getFilePath();
        
     }
    /**
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void bookInfoBtnAct(ActionEvent event) throws IOException {
      
        if(event.getSource()==cancleBtn)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("project1.fxml"));
            Scene src=new Scene(root);
            primaryStage.setTitle("e-library");
            primaryStage.setScene(src);
            primaryStage.show();
               
        }
        else if(event.getSource()==openPdfBtn)
        {
            System.out.println(filePath);
        
            try{
                Desktop.getDesktop().open(new java.io.File(filePath));
            }
            catch (IOException e){
            }
        }
    }
    
}
