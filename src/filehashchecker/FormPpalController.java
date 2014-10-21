/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehashchecker;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author juan
 */
public class FormPpalController implements Initializable {
    
    @FXML public Label label;
    @FXML public  Label calculatedHash;
    @FXML public  TextField userFile;
    @FXML public  TextField userHash;
    @FXML public  ComboBox hashType;
    @FXML public  ImageView comparisonResultGood;
    @FXML public  ImageView comparisonResultWrong;

        
     @FXML public  void handleButtonAction(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(calculatedHash.getText());
        clipboard.setContent(content);
        
    }
    
        @FXML public void dropedFile(DragEvent event) {
        String resultado;
                event.acceptTransferModes(TransferMode.ANY);
                Dragboard db = event.getDragboard();
              
                userFile.setText(db.getFiles().toString().replace("[", "").replace("]", ""));
                event.consume();
                
          FileHash fileHash = new FileHash();
          try{
              resultado=fileHash.getHashValue(userFile.getText(), hashType.getValue().toString());
              calculatedHash.setText(resultado);
          
          }catch(Exception ex){
          System.out.println(ex.toString());
          } 
    }
        
        @FXML public void itemSelected(ActionEvent event) {
              String resultado;
        comparisonResultGood.setVisible(false);
        comparisonResultWrong.setVisible(false);
              FileHash fileHash = new FileHash();
          try{
              resultado=fileHash.getHashValue(userFile.getText(), hashType.getValue().toString());
              calculatedHash.setText(resultado);
          
          }catch(Exception ex){
          System.out.println(ex.toString());
          } 
        
        }
        
          @FXML public void userHashChanged(ActionEvent event) {
              if(userHash.getText().equals(calculatedHash.getText())){
            comparisonResultGood.setVisible(true);
            comparisonResultWrong.setVisible(false);
              }else {
            comparisonResultGood.setVisible(false);
            comparisonResultWrong.setVisible(true);
              }
             
          }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
        "MD5", "SHA-1", "SHA-256","SHA-512");
        hashType.setItems(options);
        hashType.setValue("MD5");
        comparisonResultGood.setVisible(false);
        comparisonResultWrong.setVisible(false);
            userFile.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                Dragboard db = event.getDragboard();
                /* drag was detected, start drag-and-drop gesture*/
                userFile.setText(db.getFiles().toString().replace("[", "").replace("]", ""));
                event.consume();
            }
            
        });
          userHash.setOnKeyReleased(new EventHandler <KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
            if(userHash.getText().equals(calculatedHash.getText())){
            comparisonResultGood.setVisible(true);
            comparisonResultWrong.setVisible(false);
              }else {
            comparisonResultGood.setVisible(false);
            comparisonResultWrong.setVisible(true);
              }
            }
        });
          
            

    }    
    
    
}
