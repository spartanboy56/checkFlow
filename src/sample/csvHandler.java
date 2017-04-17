package sample;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Pappy on 4/12/2017.
 */
public class csvHandler {

    /*
    private final ArrayList<String> csvParser(){

        return
    }*/

    //private static Map<String,Boolean> checkValues = new HashMap<String,Boolean>();

    private final ObservableList<Tab> csvLoad(){
        ObservableList<Tab> tabs = FXCollections.observableArrayList();
        List<String[]> preTabValues = new ArrayList<>();
        Boolean isNew;

        String fileLocal = System.getProperty("user.dir") + "\\CheckFlowSave.csv";
        File csvFile = new File(fileLocal);
        if(csvFile.exists()) {
            System.out.println("Reading!");
            try {
                CSVReader reader = new CSVReader(new FileReader("CheckFlowSave.csv"));
                String[] nextLine;
                while((nextLine = reader.readNext()) != null){
                    if (nextLine != null) {

                        preTabValues.add(nextLine);
                    }
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println(Arrays.toString(preTabValues.get(0)));

            List<String> tabIDs = new ArrayList<>();
            //Get tab IDs and
            preTabValues.forEach(string -> {

                tabIDs.add(string[0]);
                CheckFlowController rooter = new CheckFlowController();
                Parent root = rooter.getParent();
                Tab tab = new Tab(string[0],root);
                tab.setId(string[0]);
                Node tabNode = tab.getContent();
                int count = 1;
                while (count < 21){

                    String[] parts = string[count].split(":");
                    for(Node nodeIn:((GridPane) tabNode).getChildren()){
                        String elState = parts[0];
                        String elId = parts[1];
                        String nodeElID = nodeIn.getId();
                        Boolean idMatch = false;
                        Boolean stateMatch = false;
                        if(nodeElID != null) {
                            idMatch = nodeElID.equals(elId);
                            stateMatch = elState.equals("true");
                        }
                        Boolean fireTrue = false;
                        if(idMatch&&stateMatch){
                            fireTrue = true;
                        }
                        if(nodeIn instanceof CheckBox && fireTrue){
                            System.out.println("fire!");
                            ((CheckBox) nodeIn).fire();

                        }
                    }
                    count++;
                }
                tab.setContent(tabNode);
                tabs.add(tab);
            });
        }
        return tabs;
   }
    private final void csvSave(ObservableList<Tab> tabs) {
        if(tabs == null){

        }else {
            try {
                CSVWriter writer = new CSVWriter(new FileWriter("CheckFlowSave.csv"));
                //System.out.println(tabs.size());
                //loop through all tabs in the tabpane
                for (Tab tab : tabs) {
                    Node tabNode = tab.getContent();
                    int elementsScanned = 0;
                    List<String> valuesList = new ArrayList<>();
                    valuesList.add(tab.getId());
                    //Loop through all elements in the GridPane
                    for (Node nodeIn : ((GridPane) tabNode).getChildren()) {
                        //If the element is a checkbox save its ID and value to an array and save it to the csv file
                        if (nodeIn instanceof CheckBox) {
                            Boolean state = ((CheckBox) nodeIn).isSelected();
                            String stateToString = state.toString();
                            valuesList.add(stateToString + ":" + nodeIn.getId());

                        }
                    }
                    String[] values = new String[21];
                    for (String string : valuesList) {
                        values[elementsScanned] = string;
                        elementsScanned++;
                    }
                    writer.writeNext(values);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public final void csvSaver(ObservableList<Tab> tabs) {
        csvSave(tabs);
    }

    public final ObservableList<Tab> csvLoader(){
        return csvLoad();
    }

}
