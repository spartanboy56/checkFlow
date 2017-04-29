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
import java.net.URL;
import java.util.Scanner;
import java.util.prefs.Preferences;



public class Main extends Application {

private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        File flirGUI = new File("C:\\Users\\Pappy\\Documents\\GitHub\\checkFlow\\src\\sample\\FlirMainGUI.fxml");
        boolean exists = flirGUI.exists();
        String usedUI;
        if (exists){
            usedUI="FlirMainGUI.fxml";
        }else{
            usedUI="MainGUI.fxml";
        }
        URL loca = getClass().getResource(usedUI);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(loca);
        Parent root = (Parent) fxmlLoader.load(loca.openStream());
        primaryStage.setTitle("Check Flow");
        primaryStage.setScene(new Scene(root, 500, 500));

        primaryStage.show();


    }



    @Override
    public void stop(){
        ((CheckFlowController) fxmlLoader.getController()).saveOnExit();

    }


    public static void main(String[] args) {


        launch(args);


    }
}
