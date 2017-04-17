package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.event.WindowAdapter;
import java.io.File;
import java.util.Scanner;
import java.util.prefs.Preferences;



public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        File flirGUI = new File("C:\\Users\\Pappy\\Documents\\GitHub\\checkFlow\\src\\sample\\FlirMainGUI.fxml");
        boolean exists = flirGUI.exists();
        CheckFlowController GUI = new CheckFlowController();
        System.out.println(exists);
        if (exists){
            GUI.SetUI("FlirMainGUI.fxml");
        }else{
            GUI.SetUI("MainGUI.fxml");
        }
        String usedUI = GUI.GetUI();
        Parent root = FXMLLoader.load(getClass().getResource(usedUI));
        primaryStage.setTitle("Check Flow");
        primaryStage.setScene(new Scene(root, 500, 500));

        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println(tabPane.isEmpty());
        if(!tabPane.isEmpty()) {
            csvHandler save = new csvHandler();
            save.csvSaver(tabPane);
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

    private static ObservableList<Tab> tabPane = FXCollections.observableArrayList();

    public void setMainTabPane(Tab tab){
        tabPane.add(tab);
    }
    public void setMainTabPane(ObservableList<Tab> tab){
        tabPane.addAll(tab);
    }


    public static void main(String[] args) {


        launch(args);


    }
}
