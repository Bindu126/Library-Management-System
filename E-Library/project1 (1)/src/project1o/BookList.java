/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1o;

import javafx.scene.image.Image;

/**
 *
 * @author user
 */
public class BookList {
    private String bookName,authorName,category,filePath; 
/*

    public BookList(String bookName, String authorName, String category, String filePath) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
        this.filePath = filePath;
  
    }*/

     public BookList(String bookName, String authorName, String category, String filePath) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
        this.filePath = filePath;
        
    
     }
  
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

  
    
     
}
