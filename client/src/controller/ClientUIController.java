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

public class ClientUIController {

    @FXML
    private AnchorPane ClientID;

    @FXML
    private Button btnSend;

    @FXML
    private TextArea txtClentArea;

    @FXML
    private TextField txtMessageSent;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message ="";


    public void initialize(){

        new Thread(()->{
            try {

                socket = new Socket("localhost",3001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream= new DataOutputStream(socket.getOutputStream());


                while (!message.equals("Finish")){
                    message=dataInputStream.readUTF();
                    txtClentArea.appendText("\nServer :"+message);
                    txtMessageSent.clear();



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
        dataOutputStream.writeUTF(txtMessageSent.getText().trim());
        dataOutputStream.flush();

           }

    @FXML
    void txtMessageSent(ActionEvent event) {

    }





}
