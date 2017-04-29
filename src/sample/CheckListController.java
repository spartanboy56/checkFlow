package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Pappy on 4/19/2017.
 */
public class CheckListController {
    @FXML private Label cameraModel;
    @FXML private Label cameraSoftware;
    @FXML private Label cameraConfig;
    @FXML private Label cameraPartNumber;

    @FXML
    private void launchBackup(ActionEvent event){
        int serialNumber = Integer.parseInt("55900555");
        String server = "127.0.0.1";
        String user = "chris";
        String pass = "Gigiddy1";
        ArrayList<Integer> reverseList = new ArrayList<Integer>();
        int serialNumberTemp = serialNumber;
        do {
            reverseList.add(serialNumberTemp % 10);
            //System.out.println(reverseList);
            serialNumberTemp = serialNumberTemp / 10;
            //System.out.println(serialNumber);
        }while (serialNumberTemp > 0);
        String firstThree = Integer.toString(reverseList.get(reverseList.size() - 1)) + Integer.toString(reverseList.get(reverseList.size() - 2)) + Integer.toString(reverseList.get(reverseList.size() - 3));

        String cameraType;
        switch (Integer.parseInt(firstThree)){
            case 559:   cameraType = "T6xx";
                        break;
            default:    cameraType = null;
        }

        String backupLocation = "E:\\Thermography\\Service\\Cameras\\Balthazar II\\Data\\" + cameraType + "\\" + serialNumber;
        int port = 21;
        FTPClient ftp = new FTPClient();
        try{
            ftp.connect(server);
            ftp.login(user,pass);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println(backupLocation);
            System.out.println("Creating back location....");

            String remoteServer = "FlashFS\\system\\";


        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (ftp.isConnected()) {
                    System.out.println("Disconnecting");
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
