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
    @FXML private Tab RMA;




    Main mainTabPane = new Main();
    private static ObservableList<Tab> tabData = FXCollections.observableArrayList();
    private static Map saveData;




    public void initialize(){

        String fileLocal = System.getProperty("user.dir") + "\\CheckFlowSave.csv";
        File csvFile = new File(fileLocal);
        if(csvFile.exists()){
            csvHandler loader = new csvHandler();
            ObservableList<Tab> load = loader.csvLoader();
            tabPane.getTabs().addAll(load);
            mainTabPane.setMainTabPane(load);
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
    public void exitApplication(ActionEvent event) {
        Platform.exit();
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
                    setTabData(tab);


                    mainTabPane.setMainTabPane(tab);
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
        for(Tab tab : this.tabData){
                if(tab.isSelected() == true){
                    int index = this.tabData.indexOf(tab);
                    Tab tabNew = new Tab(tab.getId() + "(1)",tab.getContent());
                    this.tabData.add(index + 1,tabNew);
                    tabPane.getTabs().add(tabNew);
                }
    }}


    public void setTabData(Tab tab) {

        tabData.add(tab);
        System.out.println(tab.getId());
        //this.tabData.forEach(tabID -> tabID.getContent());

    }

    public ObservableList<Tab> getTabData(){
        return tabPane.getTabs();
    }

    public void SetUI(String UI){
        this.UI = UI;
    }

    public String GetUI(){
        return this.UI;
    }

}
