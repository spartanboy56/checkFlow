package sample;

import com.opencsv.CSVWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;


public class CheckFlowController {
    @FXML private String UI;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem newItem;
    @FXML private Menu file;
    @FXML private TabPane tabPane = new TabPane();
    public void initialize(){

        String fileLocal = System.getProperty("user.dir") + "\\CheckFlowSave.csv";
        File csvFile = new File(fileLocal);
        if(csvFile.exists()){
            csvHandler loader = new csvHandler();
            ObservableList<Tab> load = loader.csvLoader();
            tabPane.getTabs().addAll(load);

        }
    }
    public Parent getParent(){
        Parent returnParent = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CheckListUI.fxml"));
            //System.out.println(RMAnumber);
            root.setId("Checklist");
            returnParent = root;
        }catch(IOException e){
            e.printStackTrace();
        }
        return returnParent;
    }

    @FXML
    private void addTab(ActionEvent event){
        Stage newRMAStage = new Stage();
        newRMAStage.setTitle("Enter RMA number");
        GridPane newRMAPane = new GridPane();
        TextField newRMA = new TextField();
        newRMA.setMaxWidth(100);
        newRMA.setPromptText("RMA number");
        Button createRMA = new Button("Create");
        createRMA.setMinWidth(50);
        createRMA.setFocusTraversable(true);
        newRMAPane.add(newRMA,0,0);
        newRMAPane.add(createRMA,0,1);
        Scene newRMAScene = new Scene(newRMAPane,100,50);
        newRMAStage.setScene(newRMAScene);
        newRMAStage.show();
        createRMA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String RMAnumber = newRMA.getText();
                newRMAStage.close();
                try {
                    int numTabs = tabPane.getTabs().size();
                    Parent root = FXMLLoader.load(getClass().getResource("CheckListUI.fxml"));
                    //System.out.println(RMAnumber);
                    root.setId("Checklist");
                    Tab tab = new Tab(RMAnumber, root);
                    tab.setId(RMAnumber);
                    //System.out.println(tab.getId());
                    tabPane.getTabs().add(tab);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void listTabs(ActionEvent event){
        System.out.println(tabPane.getTabs().size());
    }
    @FXML
    private void copyTab(ActionEvent event){
        }
    public void saveOnExit(){
        System.out.println(tabPane.getTabs().isEmpty());
        if(!this.tabPane.getTabs().isEmpty()) {
            csvHandler save = new csvHandler();
            save.csvSaver(this.tabPane.getTabs());
        }else{
            System.out.println("Attempting delete");
            String fileLocal = System.getProperty("user.dir") + "\\CheckFlowSave.csv";
            System.out.println(fileLocal);
            File file = new File(fileLocal);
            if(file.delete()){
                System.out.println("Deleted!");
            }
        }
    }
}
