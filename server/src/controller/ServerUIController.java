package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUIController {

    @FXML
    private AnchorPane ClientID;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtMsgSent;

    @FXML
    private TextArea txtServerArea;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message = "";



    public void initialize(){

        new Thread(()->{
            try {

                 serverSocket = new ServerSocket(3001);
                 txtServerArea.appendText("Service Start");
                 socket=serverSocket.accept();
                 txtServerArea.appendText("\nService Start");
                 dataInputStream = new DataInputStream(socket.getInputStream());
                 dataOutputStream= new DataOutputStream(socket.getOutputStream());



                 while (!message.equals("Finish")){
                     message=dataInputStream.readUTF();
                     txtServerArea.appendText("\nClient :"+message);
                     dataOutputStream.flush();

                 }
                dataOutputStream.close();
                dataInputStream.close();

            } catch (Exception e) {
                System.out.println(e);
            }

            }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {

       dataOutputStream.writeUTF(txtMsgSent.getText().trim());
        dataOutputStream.flush();
        txtMsgSent.clear();


    }

    @FXML
    void txtMsgOnAction(ActionEvent event) {

    }



}
