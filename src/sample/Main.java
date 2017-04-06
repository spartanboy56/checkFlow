package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
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

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

        } else {
            prefs.remove("filePath");

        }
    }

    public static void main(String[] args) {


        launch(args);


    }
}
