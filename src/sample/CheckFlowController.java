package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;


public class CheckFlowController {
    @FXML
    private String UI;

    @FXML private MenuBar menuBar;
    @FXML private MenuItem newItem;
    @FXML private Menu file;

    @FXML private TabPane tabPane;
    @FXML private Tab RMA;

    /*
    public void initialize(){
        tabPane.getTabs().add(new Tab("Tab"));
    }*/

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
                    Tab tab = new Tab(RMAnumber);
                    Parent root = FXMLLoader.load(getClass().getResource("CheckListUI.fxml"));
                    tab.setContent(root);
                    tabPane.getTabs().add(tab);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                }
        });
    }

    public void SetUI(String UI){
        this.UI = UI;
    }

    public String GetUI(){
        return this.UI;
    }

}
