/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import DbConnection.BookListDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Project1Controller implements Initializable {

    //menuBr Btn
    @FXML
    private Button homeBtn;
    @FXML
    private Button bookListBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button aboutUsBtn;
    @FXML
    private Button logOutBtn;
    
    //page
    @FXML
    private GridPane homePg;
    @FXML
    private GridPane bookListPg;
    @FXML
    private GridPane profilePg;
    @FXML
    private GridPane aboutUsPg;
    
    //Home Pg
    @FXML
    private TextField searchBoxHom;
    @FXML
    private Button searchBtnHomePg;
    
    //Book list Pg
    @FXML
    private Button addBookBtn;
    @FXML
    private TextField searchBoxBkList;
    @FXML
    private Button searchBtnBkList;
    @FXML
    private TableView<BookList> booksTableView;
    @FXML
    private TableColumn<BookList,String> bookNameCol;
    @FXML
    private TableColumn<BookList,String> authorNameCol;
    @FXML
    private TableColumn<BookList,String> categoryCol;
  
    //Pofile Pg
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordlabel;
    @FXML
    private Label profileName;
  
    public boolean Checker;
    
//observalble list to store data
    private final ObservableList<BookList> booksList = FXCollections.observableArrayList();
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    BookList book = null ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookLoadData();
        searchData();
    }  
    
    public void showProfileInfo(String userName,String email)
    {
        profileName.setText(userName);
        emailLabel.setText(email);
        //passwordLabel.setText(password);       
        
     }

    @FXML
    private void menuBtnAct(ActionEvent event) throws IOException {
       
        System.out.println("this method run succesfully");
        refreshTable();
        
        if (event.getSource()==homeBtn)
        {     
            homePg.toFront();
            System.out.print("home");
            
        }
        else if (bookListBtn==event.getSource())
        {
            bookListPg.toFront();
            System.out.print("booklist");
        }
        else if (event.getSource()== profileBtn)
        {
            profilePg.toFront();
            System.out.print("profile");
            
        }
        else if (event.getSource()==aboutUsBtn)
        {
            aboutUsPg.toFront();
            System.out.print("aboutus");
            
        }
        else if (event.getSource()==logOutBtn)
        {
           ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass(). getResource("startpg.fxml"));
            Scene src=new Scene(root);
            primaryStage.setTitle("e-library");
            primaryStage.setScene(src);
            primaryStage.show();
            System.out.print("log out");
        }
        
    }

    @FXML
    private void btnAct(ActionEvent event) throws IOException {
       
        //refreshTable();
       
        if (event.getSource()==addBookBtn)
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
          
            Stage primaryStage=new Stage();
            Parent root=FXMLLoader.load(getClass(). getResource("add.fxml"));
            Scene src=new Scene(root);
            primaryStage.setTitle("e-library");
            primaryStage.setScene(src);
            primaryStage.show();
        }
        else if(event.getSource()==searchBtnHomePg)
        {
            if(searchBoxHom.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please, Eneter Text into the SearchBox...!!");
                alert.showAndWait();
                
            }
        }
        else if (event.getSource()==searchBtnBkList)
        {
            if(searchBoxBkList.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please, Eneter Text into the SearchBox...!!");
                alert.showAndWait();
                
            }
            else if(Checker==false)
            {
                JOptionPane.showMessageDialog(null,"There is no match found...");
            }
            else{
                System.out.println("Open search result...!!");
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("select.fxml"));
                Parent tableViewParent = loader.load();
                
                Scene tableViewScene = new Scene(tableViewParent);
                
                //access the controller and call a method
                SelectController controller= loader.getController();
                controller.showBookInfo(booksTableView.getSelectionModel().getSelectedItem());
                
                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(tableViewScene);
                window.show();            
                
            }                                    
        }
    }
    
    private void refreshTable()
    {
        
        try {
booksList.clear();            
            query = "SELECT * FROM `book information`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
              
                booksList.add(new BookList(
              
                        resultSet.getString("BookName"),
                        resultSet.getString("AuthorName"),
                        resultSet.getString("Category"),
                        resultSet.getString("FilePath")));
                        
               //booksTableView.setItems(booksList);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Project1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void bookLoadData()
    {
    connection = BookListDb.getConnect();
    refreshTable();
    
    bookNameCol.setCellValueFactory(new PropertyValueFactory<>("BookName"));       
    authorNameCol.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));        
    categoryCol.setCellValueFactory(new PropertyValueFactory<>("Category"));
    
    booksTableView.setItems(booksList);
  }
  
    public void searchData()
    { // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<BookList> filteredData = new FilteredList<>(booksList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchBoxBkList.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(bookList -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (bookList.getBookName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					Checker=true;
                                        return true; // Filter matches  Bookname.
				} else if (bookList.getAuthorName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					Checker=true;
                                        return true; // Filter matches Author name.
				}
				else if (bookList.getCategory().toLowerCase().indexOf(lowerCaseFilter)!=-1){
				     Checker=true;
                                        return true;// Filter matches  Category.
                                }
				else{
                                        Checker=false;
                                	 return false; // Does not match.
                                        
                                        }
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<BookList> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(booksTableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		booksTableView.setItems(sortedData);               
  }
}
